package com.bkahlert.devel.signaturegenerator.app.ui.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.ResourceManager;

public class AboutDialog extends Dialog {

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public AboutDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.CLOSE | SWT.TITLE);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gl_container = GridLayoutFactory.fillDefaults()
				.spacing(0, 0).numColumns(4).create();
		container.setLayout(gl_container);

		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true,
				1, 1));
		lblNewLabel
				.setImage(ResourceManager.getPluginImage(
						"com.bkahlert.devel.signaturegenerator.app",
						"icons/about.png"));

		Label label = new Label(container, SWT.SEPARATOR | SWT.VERTICAL);
		label.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));
		new Label(container, SWT.NONE);

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(GridLayoutFactory.fillDefaults().margins(5, 10)
				.spacing(0, 0).numColumns(1).create());
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true,
				1, 1));

		Label lblSignatureGenerator = new Label(composite, SWT.WRAP);
		lblSignatureGenerator.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 1, 1));
		lblSignatureGenerator
				.setText("Signature Generator\n\nVersion: 1.0.1\n© Copyright Björn Kahlert, Eclipse contributors and others 2000, 2012.  All rights reserved.\nVisit http://devel.bkahlert.com/\n\nThis product includes software developed by the\nApache Software Foundation http://apache.org/");

		Label label_1 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false,
				4, 1));

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(713, 373);
	}

}
