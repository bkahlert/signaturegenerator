package com.bkahlert.devel.signaturegenerator.app.ui.viewer;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.bkahlert.devel.signaturegenerator.model.IEntity;

public class EntityLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof IEntity) {
			IEntity entity = (IEntity) element;
			return entity.getName();
		}
		return "ERROR";
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof IEntity) {
			return null;
		}
		return null;
	}

}
