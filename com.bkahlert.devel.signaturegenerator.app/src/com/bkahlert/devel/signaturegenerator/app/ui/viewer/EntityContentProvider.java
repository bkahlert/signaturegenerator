package com.bkahlert.devel.signaturegenerator.app.ui.viewer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.bkahlert.devel.signaturegenerator.model.IConfig;
import com.bkahlert.devel.signaturegenerator.model.IEntity;

public class EntityContentProvider implements IStructuredContentProvider {

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	public void dispose() {
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof List) {
			List<IEntity> entities = new ArrayList<IEntity>();
			for (Object object : (List<?>) inputElement) {
				if (object instanceof IConfig) {
					IConfig config = (IConfig) object;
					entities.addAll(config.getEntities());
				}
			}
			return entities.toArray();
		}
		return new Object[0];
	}
}