package com.bkahlert.devel.signaturegenerator;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.apache.log4j.Logger;

import com.bkahlert.devel.signaturegenerator.model.ConfigFactory;
import com.bkahlert.devel.signaturegenerator.model.IConfig;

public class App {

	private static final Logger LOGGER = Logger.getLogger(App.class);

	public static void main(String[] args) {
		if (args.length == 0) {
			String cmd = "cmd";
			try {
				cmd = new File(App.class.getProtectionDomain().getCodeSource()
						.getLocation().toURI()).getName();
			} catch (URISyntaxException e) {
			}
			LOGGER.error("USAGE: ./"
					+ cmd
					+ " config_directory [entity_name[ entity_name [...]]\nEXAMPLE: ./"
					+ cmd + " /server/share/config user1 user2");
			return;
		}

		String[] entities = Arrays.copyOfRange(args, 1, args.length);

		File configDirectory = new File(args[0]);
		if (!configDirectory.isDirectory()) {
			LOGGER.error(args[0] + " is no directory!");
		}

		File templateDirectory = new File(configDirectory, "templates");
		if (!templateDirectory.isDirectory()) {
			LOGGER.error(templateDirectory + " is no directory!");
		}

		File outputDirectory = new File(System.getProperty("user.dir"));
		if (!outputDirectory.exists())
			outputDirectory.mkdirs();

		for (File configFile : SignatureGenerator
				.getConfigFiles(configDirectory)) {
			try {
				LOGGER.info("Creating signatures for " + configFile.getName()
						+ "...");
				long start = System.currentTimeMillis();
				IConfig config = ConfigFactory.readFromFile(configFile);
				SignatureGenerator signatureGenerator = new SignatureGenerator(
						templateDirectory, config);
				signatureGenerator.createSignatures(outputDirectory, entities);
				LOGGER.info("Successfully created signatures for "
						+ configFile.getName() + " within "
						+ (System.currentTimeMillis() - start) + "ms.");
			} catch (Exception e) {
				LOGGER.error("Error creating signatures for " + configFile, e);
			}
		}
	}
}
