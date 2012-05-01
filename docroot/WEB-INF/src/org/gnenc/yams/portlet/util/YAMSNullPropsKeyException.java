package org.gnenc.yams.portlet.util;

import com.liferay.portal.kernel.exception.PortalException;

public class YAMSNullPropsKeyException extends PortalException implements PropsValidatorException {

	private static final long serialVersionUID = 4503606575065492583L;

	public YAMSNullPropsKeyException() {
		super();
	}
	
	public YAMSNullPropsKeyException(String msg) {
		super(msg);
	}

}
