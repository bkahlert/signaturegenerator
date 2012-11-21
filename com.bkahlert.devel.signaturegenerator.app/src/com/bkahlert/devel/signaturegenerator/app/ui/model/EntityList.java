package com.bkahlert.devel.signaturegenerator.app.ui.model;

import java.util.ArrayList;
import java.util.List;

import com.bkahlert.devel.signaturegenerator.model.IEntity;

public class EntityList extends ArrayList<IEntity> implements List<IEntity> {
	private static final long serialVersionUID = 1L;

	public List<String> getNames() {
		List<String> names = new ArrayList<String>();
		for (IEntity entity : this) {
			names.add(entity.getName());
		}
		return names;
	}
}
