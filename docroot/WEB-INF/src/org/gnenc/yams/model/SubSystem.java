package org.gnenc.yams.model;

import java.util.Arrays;
import java.util.List;

public enum SubSystem {
		LDAP;
		
		public static List<SubSystem> ALL_SUBSYSTEMS = Arrays.asList(SubSystem.values());

}
