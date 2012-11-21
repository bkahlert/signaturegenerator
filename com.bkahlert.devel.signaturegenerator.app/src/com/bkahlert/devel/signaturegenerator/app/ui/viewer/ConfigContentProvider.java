package com.bkahlert.devel.signaturegenerator.app.ui.viewer;

import java.io.File;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.bkahlert.devel.signaturegenerator.SignatureGenerator;
import com.bkahlert.devel.signaturegenerator.app.ui.model.ConfigList;
import com.bkahlert.devel.signaturegenerator.model.ConfigException;
import com.bkahlert.devel.signaturegenerator.model.ConfigFactory;
import com.bkahlert.devel.signaturegenerator.model.IConfig;

public class ConfigContentProvider implements IStructuredContentProvider {

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	public void dispose() {
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof File) {
			File templateDirectory = (File) inputElement;
			File[] configFiles = SignatureGenerator
					.getConfigFiles(templateDirectory);
			ConfigList configs = new ConfigList();
			for (File configFile : configFiles) {
				try {
					IConfig x = ConfigFactory.readFromFile(configFile);
					configs.add(x);
				} catch (ConfigException e1) {
					e1.printStackTrace();
				}
			}
			return configs.toArray(new IConfig[0]);
		}
		return new Object[0];
	}
}