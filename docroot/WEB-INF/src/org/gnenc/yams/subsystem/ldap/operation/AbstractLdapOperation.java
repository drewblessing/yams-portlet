package org.gnenc.yams.subsystem.ldap.operation;

import org.gnenc.yams.model.SubSystem;
import org.gnenc.yams.operation.Operation;
public abstract class AbstractLdapOperation implements Operation {

	@Override
	public SubSystem getSubsystemType() {
		return SubSystem.LDAP;
	}

}