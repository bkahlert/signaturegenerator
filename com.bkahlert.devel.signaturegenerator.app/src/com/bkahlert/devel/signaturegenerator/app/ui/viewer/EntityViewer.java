package com.bkahlert.devel.signaturegenerator.app.ui.viewer;

import java.util.List;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.widgets.Composite;

import com.bkahlert.devel.rcp.selectionUtils.ArrayUtils;
import com.bkahlert.devel.rcp.selectionUtils.SelectionUtils;
import com.bkahlert.devel.signaturegenerator.model.IEntity;

public class EntityViewer extends ListViewer {

	private EntityContentProvider cp = new EntityContentProvider();

	public EntityViewer(Composite parent, int style) {
		super(parent, style);
		this.setLabelProvider(new EntityLabelProvider());
		this.setContentProvider(cp);
	}

	public List<IEntity> getEntities() {
		return ArrayUtils.getAdaptableObjects(cp.getElements(this.getInput()),
				IEntity.class);
	}

	public List<IEntity> getSelectedEntities() {
		return SelectionUtils.getAdaptableObjects(this.getSelection(),
				IEntity.class);
	}
}
