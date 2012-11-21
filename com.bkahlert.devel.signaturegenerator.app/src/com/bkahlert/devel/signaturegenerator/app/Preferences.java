package com.bkahlert.devel.signaturegenerator.app;

import java.io.File;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

public class Preferences {
	IEclipsePreferences preferences = ConfigurationScope.INSTANCE
			.getNode("com.bkahlert.devel.signaturegenerator.app");

	public void setLastSrcDir(File srcDir) {
		this.preferences.put("lastSrcDir", srcDir.getAbsolutePath());
	}

	public File getLastSrcDir() {
		String lastSrcDir = this.preferences.get("lastSrcDir", null);
		return (lastSrcDir != null) ? new File(lastSrcDir) : null;
	}

	public void setLastDestDir(File destDir) {
		this.preferences.put("lastDestDir", destDir.getAbsolutePath());
	}

	public File getLastDestDir() {
		String lastDestDir = this.preferences.get("lastDestDir", null);
		return (lastDestDir != null) ? new File(lastDestDir) : null;
	}
}
