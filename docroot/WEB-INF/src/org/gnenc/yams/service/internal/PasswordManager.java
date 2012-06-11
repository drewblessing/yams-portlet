package org.gnenc.yams.service.internal;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.Validator;


@Service("passwordManager")
public class PasswordManager {
	
	@Value("#{ ldapProperties['ldap.minPasswordLength'] }")
	private int minPasswordLength;
	
	@Value("#{ ldapProperties['ldap.blowfish.secretkey'] }")
	private String secretKey;
	
//	@Autowired
//	private MessageService messages;
	
	private static final char[] ALLOWED_CHARS = new char[54];
	
	private static final String LOWER_CASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
	
	private static final String UPPER_CASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	private static final String NUMERIC_CHARS = "1234567890";
	
	// RANDOM is thread-safe.
	private final Random RANDOM = new Random();

	static {
	  for (int idx = 0; idx < 8; ++idx)
		  ALLOWED_CHARS[idx] = (char) ('2' + idx);
	  int idx = 0;
	  for (int count = 0; count < 26; ++count) {
		  if(count != 8 && count !=11 && count != 14) { // ignore i,l and o
			  ALLOWED_CHARS[idx+8] = (char) ('a' + count);
			  ALLOWED_CHARS[idx+31] = (char) ('A' + count);
			  ++idx;
		  }
	  }
	}

	
	private static final Logger logger = Logger.getLogger(PasswordManager.class);
	
	private static ThreadLocal<MessageDigest> md = new ThreadLocal<MessageDigest>() {
		@Override
		public MessageDigest get() {
			try {
				MessageDigest digest = MessageDigest.getInstance("SHA");
				digest.reset();
				return digest;
            } catch (Exception  e) {
            	logger.error("An error occurred when computing the message digest.", e);
            	return null;
            }
		}
	};
	
	public String encryptBlowfish(String to_encrypt) {
		try {
			SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return byteToBase64(cipher.doFinal(to_encrypt.getBytes()));
		} catch (Exception e) {
			logger.error("Error encrypting Blowfish password.", e);
			return null;
		}
	}

	public String decryptBlowfish(String to_decrypt) {
		try {
			SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "Blowfish");
			// ECB mode with PKCS5Padding is the default with JCE and need not 
			// therefore be explicitly specified,
			// but I'm adding it in for the sake of clarity
			Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decrypted = null;
			try{
				decrypted = cipher.doFinal(base64ToByte(to_decrypt));
			} catch (BadPaddingException be) {
				/**
				 * @see http://code.activestate.com/recipes/496763-a-simple-pycrypto-blowfish-encryption-script/
				 * from where Ricky seems to have ripped off his code.
				 */
				// You can thank Rick and Python's general lack of standards for this
				cipher = Cipher.getInstance("Blowfish/ECB/NoPadding");
				cipher.init(Cipher.DECRYPT_MODE, key);
				decrypted = cipher.doFinal(base64ToByte(to_decrypt));
				String result = new String(decrypted);
				// In python's impl, the unicode of the last padded character % 8 
				// will tell you how many blocks of padding have been added
				int depaddingSize = ((int) result.charAt(result.length() - 1)) % 8;
				// in case the input string was 8 bytes, an additional 8 bytes of padding is
				// added anyway, just so the last character isn't mis-interpreted
				depaddingSize = depaddingSize == 0 ? depaddingSize = 8 : result.length() - depaddingSize;
				return result.substring(0, depaddingSize);
			}		
			return new String(decrypted);
		
		} catch (Exception e) {
			logger.error("Error decrypting Blowfish password.", e);
			return "";
		}
	}

	public String encryptSSHA1(String password) {
		return computeAndEncode(4,password.getBytes());
	}
	
    public boolean verifySSHA1(String  encodedPassword, String plaintextPassword)
    {
        byte[] hash = new byte[20];
        byte[] salt = decode(encodedPassword, hash);
        return verify(salt, hash, plaintextPassword.getBytes());
    }
    
    public String generateNewPassword(String uid) {
    	
    	boolean unique = true;
    	String password = "";
    	do {
    		unique = true;
    		final char[] buf = new char[minPasswordLength]; 
    		
    		for(int count=0; count<minPasswordLength; count++) {
    			buf[count] = ALLOWED_CHARS[RANDOM.nextInt(ALLOWED_CHARS.length)];
    		}
    		
    		password = new String(buf);
    		
    		// Apply some rules here to make sure the password does not contain
    		// any abusive words, etc
    		
    		unique = unique && !password.contains(uid);
    		
    		boolean atleastOneNumber = false;
    		for(int idx = 0; idx < 8; idx++) {
    			if(password.indexOf(ALLOWED_CHARS[idx]) != -1) {
    				atleastOneNumber = true;
    				break;
    			}
    		}
    		unique = unique && atleastOneNumber;
    		
//    		unique = unique && !warehouseService.isFilteredWord(password);
    		
    	} while (!unique);
    	return password;
    }
    
    public boolean compareWithSsha(final String plain, final String ssha) {
		SecureRandom rng = new SecureRandom();
		byte[] salt = new byte[4];
		rng.nextBytes(salt);

		byte[] hash = compute(salt, plain.getBytes());
		
		return verify(decode(ssha, hash), hash, plain.getBytes());
    }
    
    public void validatePassword(final String password, final List<String> validationErrors) {
    	if(Validator.isNull(password)) {
//    		validationErrors.add(messages.getMessage(18));
    		return;
    	}
    	
    	if(password.length() < minPasswordLength) {
//    		validationErrors.add(messages.getParsedMessage(14, new String[] {String.valueOf(minPasswordLength)}));
    	}
    	
    	boolean lowerCase = false;
    	boolean upperCase = false;
    	boolean numeric = false;
    	
    	for(int i=0; i<password.length(); i++) {
    		char c = password.charAt(i);
    		if(LOWER_CASE_CHARS.indexOf(c) != -1) {
    			lowerCase = true;
    		}
    		
    		if(UPPER_CASE_CHARS.indexOf(c) != -1) {
    			upperCase = true;
    		}
    		
    		if(NUMERIC_CHARS.indexOf(c) != -1) {
    			numeric = true;
    		}
    	}
    	
    	if(!lowerCase) {
//    		validationErrors.add(messages.getMessage(16));
    	}
    	
    	if(!upperCase) {
//    		validationErrors.add(messages.getMessage(15));
    	}
    	
    	if(!numeric) {
//    		validationErrors.add(messages.getMessage(17));
    	}
    }

	private String computeAndEncode(int saltBytes, byte[] password){
		SecureRandom rng = new SecureRandom();
		byte[] salt = new byte[saltBytes];
		rng.nextBytes(salt);

		byte[] hash = compute(salt, password);
		return encode(salt, hash);
	}
	
	private String  encode(byte[] salt, byte[] hash) {
         assert (hash.length==20);
         byte[] res = new byte[20+salt.length];
         System.arraycopy(hash, 0, res, 0, 20);
        System.arraycopy(salt, 0, res, 20, salt.length);
 
        BASE64Encoder encoder = new BASE64Encoder();
        String  encoded = encoder.encode(res);

         String  out = "{SSHA}" + encoded;
        return out;
    }
	
	 /**
      * Compute a salted SHA hash.
      *
      * @param salt Salt bytes.
      * @param password Password bytes.
      * @return Byte array of length 20 bytes containing hash result.
      * @throws IASSecurityExeption Thrown if there is an error.
      *
      */
     private byte[] compute(byte[] salt, byte[] password)
     {
         byte[] buff = new byte[password.length + salt.length];
         System.arraycopy(password, 0, buff, 0, password.length);
         System.arraycopy(salt, 0, buff, password.length, salt.length);
 
         MessageDigest digest = md.get();
         assert(digest != null);
         byte[] hash = digest.digest(buff);
 
         assert (hash.length==20); // SHA output is 160 bits
 
         return hash;
     }
     
     private byte[] decode(String  encoded, byte[] hashResult)
     {
         assert (hashResult.length==20);
         if (!encoded.startsWith("{SSHA}")) {
        	 throw new IllegalArgumentException("Bad SSHA Format");
         }
 
         String  ssha = encoded.substring("{SSHA}".length());
         
         BASE64Decoder decoder = new BASE64Decoder();
         byte[] result = null;
       
         try {
             result = decoder.decodeBuffer(ssha);
         } catch (IOException e) {
             System.err.println("An error occoured when decoding the password");
         }
         assert (result.length > 20);
         
         byte[] salt = new byte[result.length - 20];
 
         System.arraycopy(result, 0, hashResult, 0, 20);
         System.arraycopy(result, 20, salt, 0, result.length-20);
 
         return salt;
     }
     
     private boolean verify(byte[] salt, byte[] hash, byte[] password)
     {
         byte[] newHash = compute(salt, password);
         return Arrays.equals(hash, newHash);
     }
     
     /**
      * From a base 64 representation, returns the corresponding byte[] 
      * @param data String The base64 representation
      * @return byte[]
      * @throws IOException
      */
     private byte[] base64ToByte(String data) throws IOException {
         return Base64.decode(data);
     }

     /**
      * From a byte[] returns a base 64 representation
      * @param data byte[]
      * @return String
      * @throws IOException
      */
     private String byteToBase64(byte[] data){
         return Base64.encode(data);
     }

}
