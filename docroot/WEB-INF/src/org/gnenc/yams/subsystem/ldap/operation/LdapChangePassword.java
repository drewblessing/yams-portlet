package org.gnenc.yams.subsystem.ldap.operation;

import java.util.List;

import org.apache.log4j.Logger;
import org.gnenc.yams.model.Account;
import org.gnenc.yams.operation.account.ChangePassword;
import org.gnenc.yams.operation.account.ResetPassword;
import org.gnenc.yams.service.internal.PasswordManager;
import org.gnenc.yams.subsystem.ldap.LdapAccountHelper;
import org.gnenc.yams.subsystem.ldap.LdapHelper;
import org.gnenc.yams.subsystem.ldap.model.LdapAccount;
import org.gnenc.yams.subsystem.ldap.model.LdapPasswordPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.odm.core.OdmManager;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Jeshurun Daniel
 *
 */
@Service("ldapChangePassword")
public class LdapChangePassword extends AbstractLdapOperation implements ChangePassword, ResetPassword {
	
	private static final Logger logger = Logger.getLogger(LdapChangePassword.class);
	
	@Autowired
	private OdmManager manager;
	
	@Autowired
	private PasswordManager pwdManager;
	
//	@Autowired
//	private MessageService messages;
	
	@Autowired
	private LdapTemplate template;
	
	@Autowired
	private LdapGetPasswordPolicy pwdPolicyService;
	
	@Override
	public void validateChangePassword(final Account account, final String oldPassword,
			final String newPassword, final List<String> validationErrors) {
		
		if(!template.authenticate("", "(uid=" + account.getUid() + ")", oldPassword)) {
//			validationErrors.add(messages.getMessage(13));
			return;
		}
		
		if(oldPassword.equals(newPassword)) {
//			validationErrors.add(messages.getMessage(20));
			return;
		}
		
		final List<LdapAccount> ldapSearchResult = manager.search(LdapAccount.class,
				DistinguishedName.EMPTY_PATH, "uid=" + account.getUid(),
				LdapHelper.SEARCH_CONTROL_ALL_SUBTREE_SCOPE);
		if(ldapSearchResult != null && !ldapSearchResult.isEmpty()) {
			final LdapAccount ldap = ldapSearchResult.get(0);
			final LdapPasswordPolicy accountPwdPolicy = pwdPolicyService.getPasswordPolicy(ldap.getPwdPolicy());
			
			final int pwdInHistory = accountPwdPolicy.getPwdInHistory();
			for(int i=0; i < ldap.getPwdHistory().size() && i < pwdInHistory; i++) {
				final String pwdHistory = ldap.getPwdHistory().get(i);
				if(pwdManager.compareWithSsha(newPassword, pwdHistory.substring(pwdHistory.length() - 38, pwdHistory.length()))) {
//					validationErrors.add(messages.getParsedMessage(19, new String[] {String.valueOf(pwdInHistory)}));
					break;
				}
			}
			
			pwdManager.validatePassword(newPassword, validationErrors);
		}
		
	}

	@Override
	public void changePassword(final Account account, final String newPassword) {
		
		final LdapAccount ldap = manager.read(LdapAccount.class, LdapAccountHelper.computeDn(account));
		
		if(ldap != null) {
			ldap.setUserPassword(pwdManager.encryptSSHA1(newPassword));
			manager.update(ldap);
			
			logger.info("Updated password for LDAP Account: " + account.getUid());
		}

	}

}
