package org.gnenc.yams.subsystem.ldap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.SearchControls;

import org.springframework.ldap.odm.annotations.Attribute;

public class LdapHelper {
	private LdapHelper() {}
	
	public static final SearchControls SEARCH_CONTROL_ALL_SUBTREE_SCOPE = new SearchControls(
			SearchControls.SUBTREE_SCOPE, 0, 0, null, true, false);
	
	public static final String DEFAULT_BASE_DN = "dc=testing,dc=esu10,dc=org";
	
	public static final String LDAP_DATE_FORMAT = "yyyyMMddHHmmss";
	
	public static final List<String> getAttributes(Class<?> clazz) {
		final List<String> attributes = new ArrayList<String>();
		
		for(final Field field : clazz.getDeclaredFields()) {
			// use the field's name by default. If the attribute annotation has a name attribute, use that instead.
			Attribute attribute = field.getAnnotation(Attribute.class);
	        if (attribute != null) {
	        	if(attribute.name() != null && !attribute.name().isEmpty()) {
	        		attributes.add(attribute.name());
	        	} else {
	        		attributes.add(field.getName());
	        	}
	        }
		}
		
		return attributes;
	}
}
