package org.gnenc.yams.subsystem.ldap.operation;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.gnenc.yams.model.PasswordPolicy;
import org.gnenc.yams.operation.account.GetPasswordPolicy;
import org.gnenc.yams.subsystem.ldap.LdapHelper;
import org.gnenc.yams.subsystem.ldap.model.LdapAccount;
import org.gnenc.yams.subsystem.ldap.model.LdapPasswordPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.odm.core.OdmManager;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Jeshurun Daniel
 *
 */
@Service("ldapGetPasswordPolicy")
public class LdapGetPasswordPolicy extends AbstractLdapOperation implements GetPasswordPolicy {
	
	@Autowired
	private OdmManager manager;
	
	private Map<String, LdapPasswordPolicy> definedPasswordPolicies;
	
	@PostConstruct
	@SuppressWarnings("unused")
	private void init() {
		definedPasswordPolicies = Collections.synchronizedMap(new HashMap<String, LdapPasswordPolicy>());
		for(final LdapPasswordPolicy ppolicy : 
			manager.findAll(LdapPasswordPolicy.class, DistinguishedName.EMPTY_PATH,
					LdapHelper.SEARCH_CONTROL_ALL_SUBTREE_SCOPE)) {
			definedPasswordPolicies.put(ppolicy.getDn().toString() + "," + LdapHelper.DEFAULT_BASE_DN, ppolicy);
		}
	}

	@Override
	public void getPasswordPolicy(String accountId, PasswordPolicy policy) {
		final List<LdapAccount> ldapSearchResult = manager.search(LdapAccount.class,
				DistinguishedName.EMPTY_PATH, "uid=" + accountId,
				LdapHelper.SEARCH_CONTROL_ALL_SUBTREE_SCOPE);
		if(ldapSearchResult != null && !ldapSearchResult.isEmpty()) {
			final LdapAccount ldapAccount = ldapSearchResult.get(0);
			final LdapPasswordPolicy accountPwdPolicy = getPasswordPolicy(ldapAccount.getPwdPolicy());
			
			policy.setPwdExpireWarning(accountPwdPolicy.getPwdExpireWarning());
			policy.setPwdInHistory(accountPwdPolicy.getPwdInHistory());
			policy.setPwdMaxAge(accountPwdPolicy.getPwdMaxAge());
			policy.setPwdMaxFailure(accountPwdPolicy.getPwdMaxFailure());
			policy.setPwdMinLength(accountPwdPolicy.getPwdMinLength());
		}
		
	}
	
	public LdapPasswordPolicy getPasswordPolicy(String pwdPolicyDn) {
		return definedPasswordPolicies.get(pwdPolicyDn);
	}
	
}
