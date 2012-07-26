package org.gnenc.yams.subsystem.ldap.operation;

import java.util.List;
import java.util.logging.Logger;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.operation.account.ChangePassword;
import org.gnenc.yams.operation.account.ResetPassword;
import org.gnenc.yams.service.internal.PasswordManager;
import org.gnenc.yams.subsystem.ldap.LdapAccountHelper;
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
	
//	private static final Logger logger = Logger.getLogger(LdapModifyPassword.class);
	
	@Autowired
	private OdmManager manager;
	
	@Autowired
	private PasswordManager pwdManager;
	
//	@Autowired
//	private MessageService messages;
	
	@Autowired
	private LdapTemplate template;
	
//	@Autowired
//	private LdapGetPasswordPolicy pwdPolicyService;
	
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
		
		validateChangePassword(account, newPassword, validationErrors);
	}
	
	public void validateChangePassword(final Account account, final String newPassword,
			final List<String> validationErrors) {
		
		pwdManager.validatePassword(newPassword, validationErrors);	
	}

	@Override
	public void changePassword(final Account account, final String newPassword) {
		
		final LdapAccount ldap = manager.read(LdapAccount.class, LdapAccountHelper.computeDn(account));
		
		if(ldap != null) {
			ldap.setUserPassword(pwdManager.encryptSha1(newPassword));
			manager.update(ldap);
			
//			logger.info("Updated password for LDAP Account: " + account.getUid());
		}

	}

}
