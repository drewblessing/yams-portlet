package org.gnenc.yams.portlet.util;

import javax.portlet.RenderRequest;
import com.liferay.portal.kernel.servlet.SessionErrors;

import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

public class PropsValidator {
	
	public static void validateAllProps(RenderRequest request) throws YAMSNullPropsKeyException, 
			YAMSMalformedUrlException {
		validateRequiredProps(request);
		validateUrlProps(request);
		
		PropsValidator.previouslyValidatedSuccessfully = true;
	}

	private static void validateUrlProps(RenderRequest request) throws YAMSMalformedUrlException {
		for (String key : urlPropsKeys) {
			if (Validator.isUrl(PropsUtil.get(key))) 
				SessionErrors.add(request,YAMSMalformedUrlException.class.getName());
//				throw new YAMSMalformedUrlException(
//						"Malformed URL for YAMS property key: " + key
//				);
		
		}
		
	}

	private static void validateRequiredProps(RenderRequest request) throws YAMSNullPropsKeyException {
		for (String key : requiredPropsKeys) {
			if (Validator.isNull(PropsUtil.get(key))) 
				SessionErrors.add(request,YAMSNullPropsKeyException.class.getName());
//				throw new YAMSNullPropsKeyException(
//						"Null YAMS property key: " + key
//				);
		
		}
		
	}
	
	public static boolean previouslyValidatedSuccessfully;
	
	private static String[] requiredPropsKeys = {
			PropsKeys.YAMS_LDAP_BASE_PROVIDER_URL, PropsKeys.YAMS_LDAP_BASE_DN,
			PropsKeys.YAMS_LDAP_SECURITY_PRINCIPAL, PropsKeys.YAMS_LDAP_SECURITY_CREDENTIALS
	};
	
	private static String[] urlPropsKeys = {
			PropsKeys.YAMS_LDAP_BASE_PROVIDER_URL
	};
	
}
