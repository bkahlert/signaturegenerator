package com.bkahlert.devel.signaturegenerator.app.ui.viewer;

import java.util.List;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.widgets.Composite;

import com.bkahlert.devel.rcp.selectionUtils.ArrayUtils;
import com.bkahlert.devel.rcp.selectionUtils.SelectionUtils;
import com.bkahlert.devel.signaturegenerator.model.IConfig;

public class ConfigViewer extends ListViewer {

	private ConfigContentProvider cp = new ConfigContentProvider();

	public ConfigViewer(Composite parent, int style) {
		super(parent, style);
		this.setLabelProvider(new ConfigLabelProvider());
		this.setContentProvider(cp);
	}

	public List<IConfig> getConfigs() {
		return ArrayUtils.getAdaptableObjects(cp.getElements(this.getInput()),
				IConfig.class);
	}

	public List<IConfig> getSelectedConfigs() {
		return SelectionUtils.getAdaptableObjects(this.getSelection(),
				IConfig.class);
	}
}
