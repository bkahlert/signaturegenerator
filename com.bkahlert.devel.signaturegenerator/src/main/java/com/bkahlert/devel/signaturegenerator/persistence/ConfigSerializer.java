package com.bkahlert.devel.signaturegenerator.persistence;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.bkahlert.devel.signaturegenerator.model.ConfigException;
import com.bkahlert.devel.signaturegenerator.model.IConfig;
import com.bkahlert.devel.signaturegenerator.model.impl.Config;
import com.bkahlert.devel.signaturegenerator.model.impl.Entity;
import com.bkahlert.devel.signaturegenerator.model.impl.Replacement;
import com.bkahlert.devel.signaturegenerator.model.impl.Template;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class ConfigSerializer {
	private XStream xstream;

	public ConfigSerializer(ClassLoader classLoader) {
		xstream = new XStream(new StaxDriver());
		xstream.setClassLoader(classLoader);
		xstream.registerConverter(new ReplacementConverter());
		xstream.alias("signatureGenerator", Config.class);
		xstream.addImplicitCollection(Config.class, "entities");
		xstream.alias("entity", Entity.class);
		xstream.useAttributeFor(Entity.class, "name");
		xstream.addImplicitCollection(Entity.class, "templates");
		xstream.alias("template", Template.class);
		xstream.addImplicitCollection(Template.class, "replacements");
		xstream.useAttributeFor(Template.class, "name");
		xstream.alias("replace", Replacement.class);
		xstream.useAttributeFor(Replacement.class, "name");
	}

	public IConfig read(File file) throws ConfigException {
		try {
			return (IConfig) xstream.fromXML(FileUtils.readFileToString(file,
					"UTF-8"));
		} catch (Exception e) {
			throw new ConfigException("Could not read " + file, e);
		}
	}
}
