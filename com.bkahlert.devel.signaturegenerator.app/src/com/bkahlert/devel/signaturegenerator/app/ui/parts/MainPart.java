package com.bkahlert.devel.signaturegenerator.app.ui.parts;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.bkahlert.devel.signaturegenerator.SignatureGenerator;
import com.bkahlert.devel.signaturegenerator.app.Preferences;
import com.bkahlert.devel.signaturegenerator.app.ui.viewer.ConfigViewer;
import com.bkahlert.devel.signaturegenerator.app.ui.viewer.EntityViewer;
import com.bkahlert.devel.signaturegenerator.model.IConfig;

public class MainPart {

	@Inject
	private Shell shell;
	private Text srcDirText;

	private Preferences preferences = new Preferences();

	private ConfigViewer configViewer;
	private EntityViewer entityViewer;

	public MainPart() {
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(final Composite parent) {
		parent.setLayout(new FormLayout());

		Group grpTemplateDirectory = new Group(parent, SWT.NONE);
		grpTemplateDirectory.setText("Template Directory");
		grpTemplateDirectory.setLayout(new GridLayout(2, false));
		FormData fd_grpTemplateDirectory = new FormData();
		fd_grpTemplateDirectory.top = new FormAttachment(0, 10);
		fd_grpTemplateDirectory.left = new FormAttachment(0, 10);
		fd_grpTemplateDirectory.right = new FormAttachment(100, -10);
		grpTemplateDirectory.setLayoutData(fd_grpTemplateDirectory);

		srcDirText = new Text(grpTemplateDirectory, SWT.BORDER);
		srcDirText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		srcDirText.setText(preferences.getLastDestDir() != null ? preferences
				.getLastSrcDir().getAbsolutePath() : "");

		Button btnNewButton = new Button(grpTemplateDirectory, SWT.NONE);
		btnNewButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog directoryDialog = new DirectoryDialog(shell);
				directoryDialog.setText("Choose Signature Template Directory");
				directoryDialog
						.setMessage("Please choose the directory where signature templates can be found:");
				directoryDialog.setFilterPath(srcDirText.getText());
				String path = directoryDialog.open();
				if (path == null)
					return;

				srcDirText.setText(path);
				srcDirText.setSelection(path.length());
			}
		});
		btnNewButton.setText("Choose Template Directory");

		Group grpTemplates = new Group(parent, SWT.NONE);
		grpTemplates.setText("Templates");
		grpTemplates.setLayout(new GridLayout(3, false));
		FormData fd_grpTemplates = new FormData();
		fd_grpTemplates.top = new FormAttachment(0, 81);
		fd_grpTemplates.left = new FormAttachment(0, 10);
		fd_grpTemplates.right = new FormAttachment(100, -10);
		grpTemplates.setLayoutData(fd_grpTemplates);

		configViewer = new ConfigViewer(grpTemplates, SWT.BORDER | SWT.V_SCROLL);
		org.eclipse.swt.widgets.List configList = configViewer.getList();
		configList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));
		configViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						entityViewer.setInput(configViewer.getSelectedConfigs());
					}
				});

		Label lblNewLabel = new Label(grpTemplates, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Lucida Grande", 14,
				SWT.NORMAL));
		lblNewLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				true, 1, 1));
		lblNewLabel.setText("»");

		final Group grpActions = new Group(parent, SWT.NONE);
		fd_grpTemplates.bottom = new FormAttachment(grpActions, -6);

		grpActions.setText("Actions");
		grpActions.setLayout(new GridLayout(2, false));
		FormData fd_grpActions = new FormData();
		fd_grpActions.bottom = new FormAttachment(100, -10);
		fd_grpActions.right = new FormAttachment(100, -10);
		fd_grpActions.left = new FormAttachment(0, 10);
		grpActions.setLayoutData(fd_grpActions);

		final Button btnGenerateSelectedSignatures = new Button(grpActions,
				SWT.NONE);
		btnGenerateSelectedSignatures.setEnabled(false);
		btnGenerateSelectedSignatures
				.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						// TODO
					}
				});
		btnGenerateSelectedSignatures.setLayoutData(new GridData(SWT.RIGHT,
				SWT.FILL, true, true, 1, 1));
		btnGenerateSelectedSignatures.setText("Generate Selected Signatures");

		Button btnGenerateSignatures = new Button(grpActions, SWT.NONE);
		btnGenerateSignatures.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, true, 1, 1));
		btnGenerateSignatures.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog directoryDialog = new DirectoryDialog(shell);
				directoryDialog.setText("Choose Signature Directory");
				directoryDialog
						.setMessage("Please choose the directory where the generated signatures shall be saved to:");
				directoryDialog
						.setFilterPath(preferences.getLastDestDir() != null ? preferences
								.getLastDestDir().getAbsolutePath() : null);

				String path = directoryDialog.open();
				if (path == null)
					return;

				File destDir = new File(path);
				if (!destDir.isDirectory())
					return;

				preferences.setLastDestDir(destDir);

				int success = 0;
				for (IConfig config : configViewer.getConfigs()) {
					SignatureGenerator signatureGenerator = new SignatureGenerator(
							new File(new File(srcDirText.getText()),
									"templates"), config);
					success += signatureGenerator.createSignatures(destDir);
				}
				MessageDialog.openInformation(
						parent.getShell(),
						"Signatures Created",
						success + " signature" + ((success == 1) ? "" : "s")
								+ " were successfully created in "
								+ destDir.getAbsolutePath());
			}
		});
		btnGenerateSignatures.setText("Generate Signatures");
		srcDirText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				File srcDir = new File(srcDirText.getText());
				configViewer.setInput(srcDir);
				preferences.setLastSrcDir(srcDir);
			}
		});

		entityViewer = new EntityViewer(grpTemplates, SWT.BORDER | SWT.V_SCROLL);
		org.eclipse.swt.widgets.List list = entityViewer.getList();
		list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		entityViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						int num = entityViewer.getSelectedEntities().size();
						btnGenerateSelectedSignatures.setText("Generate " + num
								+ " Selected" + " Signature"
								+ ((num == 1) ? "" : "s"));
						grpActions.layout();
					}
				});

	}

	@PreDestroy
	public void dispose() {
	}
}
