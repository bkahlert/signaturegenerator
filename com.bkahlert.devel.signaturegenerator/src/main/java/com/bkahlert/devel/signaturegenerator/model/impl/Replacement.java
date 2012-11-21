package com.bkahlert.devel.signaturegenerator.model.impl;

import com.bkahlert.devel.signaturegenerator.model.IReplacement;

public class Replacement implements IReplacement {

	private String name;
	private String value;

	public Replacement(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public String getValue() {
		return this.value;
	}

}
