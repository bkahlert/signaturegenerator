package com.bkahlert.devel.signaturegenerator.app.ui.viewer;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.bkahlert.devel.signaturegenerator.model.IConfig;

public class ConfigLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof IConfig) {
			IConfig config = (IConfig) element;
			return config.toString();
		}
		return "ERROR";
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof IConfig) {
			return null;
		}
		return null;
	}

}
