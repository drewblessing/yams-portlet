package org.gnenc.yams.model;

import java.io.Serializable;

/**
 * 
 * @author Jeshurun Daniel
 *
 */
public final class PasswordPolicy implements Serializable {
	
	private static final long serialVersionUID = 1436224656501528819L;

	private Integer pwdExpireWarning;
	
	private Integer pwdInHistory;
	
	private Integer pwdMaxAge;
	
	private Integer pwdMaxFailure; 
	
	private Integer pwdMinLength;
	
	

	public Integer getPwdExpireWarning() {
		return pwdExpireWarning;
	}

	public void setPwdExpireWarning(Integer pwdExpireWarning) {
		this.pwdExpireWarning = pwdExpireWarning;
	}

	public Integer getPwdInHistory() {
		return pwdInHistory;
	}

	public void setPwdInHistory(Integer pwdInHistory) {
		this.pwdInHistory = pwdInHistory;
	}

	public Integer getPwdMaxAge() {
		return pwdMaxAge;
	}

	public void setPwdMaxAge(Integer pwdMaxAge) {
		this.pwdMaxAge = pwdMaxAge;
	}

	public Integer getPwdMaxFailure() {
		return pwdMaxFailure;
	}

	public void setPwdMaxFailure(Integer pwdMaxFailure) {
		this.pwdMaxFailure = pwdMaxFailure;
	}

	public Integer getPwdMinLength() {
		return pwdMinLength;
	}

	public void setPwdMinLength(Integer pwdMinLength) {
		this.pwdMinLength = pwdMinLength;
	}
	
}

