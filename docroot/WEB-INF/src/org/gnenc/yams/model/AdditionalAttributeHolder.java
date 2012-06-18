package org.gnenc.yams.model;

/**
 * Based on the Additional Attribute Holder originally written
 * by Jeshurun Daniel.
 *
 * @author Drew A. Blessing
 *
 */
public class AdditionalAttributeHolder {

	public AdditionalAttributeHolder() {}

	public AdditionalAttributeHolder(final String key, final String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	private String key;
	private String value;

}