package com.bkahlert.devel.signaturegenerator.model;

import java.io.File;

import com.bkahlert.devel.signaturegenerator.persistence.ConfigSerializer;

public class ConfigFactory {
	public static IConfig readFromFile(File configFile) throws ConfigException {
		return new ConfigSerializer(ConfigSerializer.class.getClassLoader())
				.read(configFile);
	}
}
