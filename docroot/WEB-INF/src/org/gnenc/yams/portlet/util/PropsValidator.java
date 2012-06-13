package org.gnenc.yams.portlet.util;

import javax.portlet.RenderRequest;
import com.liferay.portal.kernel.servlet.SessionErrors;

import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * Property value validation
 * 
 * @author Drew A. Blessing
 *
 */
public class PropsValidator {
	
	/**
	 * Validates property values using all available methods
	 * 
	 * @param request RenderRequest object
	 * @throws YAMSNullPropsKeyException
	 * @throws YAMSMalformedUrlException
	 */
	public static void validateAllProps(RenderRequest request) throws YAMSNullPropsKeyException, 
			YAMSMalformedUrlException {
		validateRequiredProps(request);
		validateUrlProps(request);
		
		PropsValidator.validatedAllSuccessfully = true;
	}

	/**
	 * Validates any properties that should be in URL format
	 * 
	 * @param request RenderRequest object
	 * @throws YAMSMalformedUrlException
	 */
	private static void validateUrlProps(RenderRequest request) throws YAMSMalformedUrlException {
		for (String key : urlPropsKeys) {
			if (!Validator.isUrl(PropsUtil.get(key))) 
				SessionErrors.add(request,YAMSMalformedUrlException.class.getName());
				throw new YAMSMalformedUrlException(
						"Malformed URL for YAMS property key: " + key
				);
		
		}
		
	}

	/**
	 * Validates all required properties
	 * 
	 * @param request RenderRequest object
	 * @throws YAMSNullPropsKeyException
	 */
	private static void validateRequiredProps(RenderRequest request) throws YAMSNullPropsKeyException {
		for (String key : requiredPropsKeys) {
			if (Validator.isNull(PropsUtil.get(key))) 
				SessionErrors.add(request,YAMSNullPropsKeyException.class.getName());
				throw new YAMSNullPropsKeyException(
						"Null YAMS property key: " + key
				);
		
		}
		
	}
	
	public static boolean validatedAllSuccessfully;
	
	private static String[] requiredPropsKeys = {
			PropsKeys.LDAP_PROVIDER_URL, PropsKeys.LDAP_BASE_DN,
	};
	
	private static String[] urlPropsKeys = {
			PropsKeys.LDAP_PROVIDER_URL
	};
	
}
