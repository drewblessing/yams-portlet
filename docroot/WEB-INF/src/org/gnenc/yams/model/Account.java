/**
 *  Copyright (c) 2012-2013 Educational Service Unit 10. 
 *
 *  This file is part of the YAMS portlet.
 *  
 *  YAMS portlet is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  YAMS portlet is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with the YAMS portlet.  If not, see <http://www.gnu.org/licenses/>.
 **/
package org.gnenc.yams.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * YAMS Account model based on the original Account model
 * created by Jeshurun Daniel. Class represents common attributes
 * that can be found in any account subsystem.
 * 
 * @author Drew A. Blessing
 *
 */
public class Account {
	
	public static final Comparator<Account> ACCOUNT_COMPARATOR_ASC = new Comparator<Account>() {
		
		@Override
		public int compare(Account a1, Account a2) {
			if(a1 == a2) {
				return 0;
			}
			if(a1 == null && a2 == null) {
				return 0;
			}
			if(a1 == null) {
				return -1;
			}
			if(a2 == null) {
				return 1;
			}
			
			// Compare against first cn value only
			return a1.getCn().get(0).compareTo(a2.getCn().get(0));
		}
	};
	
	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) return true;
	    if ( !(obj instanceof Account) ) return false;
	    Account account =  (Account) obj;
	    return this.getUid().equals(account.getUid());
	}
	
	@Override
	public int hashCode() {
		return this.getUid().hashCode();
	}
	
	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}
	
	public String getAttribute(String key) {
		if (attributes == null)
			return null;
		
		for(AdditionalAttributeHolder a : attributes) {
			if(a.getKey().equals(key)) {
				return a.getValue();
			}
		}
		return null;
	}
	
	public List<String> getAttributesKeySet() {
		if (attributes == null)
			return Collections.emptyList();
		
		final List<String> attrs = new ArrayList<String>();
		for(AdditionalAttributeHolder a : attributes) {
			attrs.add(a.getKey());
		}
		
		return attrs;
	}

	public void setAttribute(String key, String value) {
		if (attributes == null)
			attributes = new ArrayList<AdditionalAttributeHolder>();
		attributes.add(new AdditionalAttributeHolder(key, value));
	}
	
	public List<String> getCn() {
		if(cn == null)
			cn = Collections.synchronizedList(new ArrayList<String>());
		return cn;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Integer getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(Integer employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	
	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	
	public List<String> getMail() {
		if(mail == null)
			mail = Collections.synchronizedList(new ArrayList<String>());
		return mail;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<String> getSecurityAnswer() {
		if(securityAnswer == null)
			securityAnswer = Collections.synchronizedList(new ArrayList<String>());
		return securityAnswer;
	}
	
	public List<String> getSecurityQuestion() {
		if(securityQuestion == null)
			securityQuestion = Collections.synchronizedList(new ArrayList<String>());
		return securityQuestion;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}
	
	public Set<SubSystem> getSubsystems() {
		if(subsystems == null)
			 subsystems = Collections.synchronizedSet(new HashSet<SubSystem>());
		return subsystems;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	private AccountStatus accountStatus = AccountStatus.ACTIVE;
	private List<AdditionalAttributeHolder> attributes; // Attrs that only apply to particular subsystem
	private List<String> cn;
	private String description = "";
	private String displayName = "";
	private Integer employeeNumber = 0; //????
	private String givenName = "";
	private List<String> mail;
	private String password = "";
	private List<String> securityAnswer;
	private List<String> securityQuestion;
	private String sn = "";
	private Set<SubSystem> subsystems;
	private String uid = "";
	
}
