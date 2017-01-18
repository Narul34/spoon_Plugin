package org.test.pentaho.steps.datacenter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.pentaho.di.core.Const;
import org.pentaho.di.i18n.BaseMessages;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDialogInterface;
import org.pentaho.di.ui.trans.step.BaseStepDialog;

/**
 * This class is part of the demo step plug-in implementation. It demonstrates
 * the basics of developing a plug-in step for PDI.
 * 
 * The demo step adds a new string field to the row stream and sets its value to
 * "Hello World!". The user may select the name of the new field.
 * 
 * This class is the implementation of StepDialogInterface. Classes implementing
 * this interface need to:
 * 
 * - build and open a SWT dialog displaying the step's settings (stored in the
 * step's meta object) - write back any changes the user makes to the step's
 * meta object - report whether the user changed any settings when confirming
 * the dialog
 * 
 */
public class DataCenterStepDialog extends BaseStepDialog implements
		StepDialogInterface {

	private static Class<?> PKG = DataCenterStepMeta.class; // for i18n purposes

	private DataCenterStepMeta meta;

	private Combo wFilterNameField, wDepositModeField, wBlockNameField;

	private Text wDirNameField, wFileRegexField;

	private int margin, middle;

	private ModifyListener lsMod;

	public DataCenterStepDialog(Shell parent, Object in, TransMeta transMeta,
			String sname) {
		super(parent, (BaseStepMeta) in, transMeta, sname);
		meta = (DataCenterStepMeta) in;
	}

	public String open() {

		Shell parent = getParent();
		Display display = parent.getDisplay();

		shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MIN
				| SWT.MAX);
		props.setLook(shell);
		setShellImage(shell, meta);

		changed = meta.hasChanged();

		lsMod = new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				meta.setChanged();
			}
		};

		// ------------------------------------------------------- //
		// SWT code for building the actual settings dialog //
		// ------------------------------------------------------- //
		FormLayout formLayout = new FormLayout();
		formLayout.marginWidth = Const.FORM_MARGIN;
		formLayout.marginHeight = Const.FORM_MARGIN;

		shell.setLayout(formLayout);
		shell.setText(BaseMessages.getString(PKG, "DataCenter.Shell.Title"));

		middle = props.getMiddlePct();
		margin = Const.MARGIN;

		init();

		lsDef = new SelectionAdapter() {
			public void widgetDefaultSelected(SelectionEvent e) {
				ok();
			}
		};
		wStepname.addSelectionListener(lsDef);
		wFilterNameField.addSelectionListener(lsDef);
		wDepositModeField.addSelectionListener(lsDef);
		wBlockNameField.addSelectionListener(lsDef);
		wDirNameField.addSelectionListener(lsDef);
		wFileRegexField.addSelectionListener(lsDef);

		shell.addShellListener(new ShellAdapter() {
			public void shellClosed(ShellEvent e) {
				cancel();
			}
		});

		setSize();

		populateDialog();

		meta.setChanged(changed);

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		return stepname;
	}

	/**
	 * This helper method puts the step configuration stored in the meta object
	 * and puts it into the dialog controls.
	 */
	private void populateDialog() {
		wStepname.selectAll();
		wFilterNameField.setText(meta.getFilterNameField());
	}

	/**
	 * Called when the user cancels the dialog.
	 */
	private void cancel() {
		stepname = null;
		meta.setChanged(changed);
		dispose();
	}

	/**
	 * Called when the user confirms the dialog
	 */
	private void ok() {
		stepname = wStepname.getText();
		meta.setFilterNameField(wFilterNameField.getText());
		meta.setDepositModeField(wDepositModeField.getText());
		meta.setDirNameField(wDirNameField.getText());
		meta.setBlockNameField(wBlockNameField.getText());
		meta.setFileRegexField(wFileRegexField.getText());
		dispose();
	}

	public void init() {

		stepNameLine();

		instanciateField();

		initLabel("DataCenter.FilterNameField.Label", wStepname);
		initField(wFilterNameField, wStepname);
		initLabel("DataCenter.DepositModeField.Label", wFilterNameField);
		initField(wDepositModeField, wFilterNameField);
		initLabel("DataCenter.DirNameField.Label", wDepositModeField);
		initField(wDirNameField, wDepositModeField);
		initLabel("DataCenter.BlockNameField.Label", wDirNameField);
		initField(wBlockNameField, wDirNameField);
		initLabel("DataCenter.FileRegexField.Label", wBlockNameField);
		initField(wFileRegexField, wBlockNameField);

		initButton();

	}

	private void stepNameLine() {
		wlStepname = new Label(shell, SWT.RIGHT);
		wlStepname.setText(BaseMessages.getString(PKG, "System.Label.StepName"));
		props.setLook(wlStepname);
		fdlStepname = new FormData();
		fdlStepname.left = new FormAttachment(0, 0);
		fdlStepname.right = new FormAttachment(middle, -margin);
		fdlStepname.top = new FormAttachment(0, margin);
		wlStepname.setLayoutData(fdlStepname);

		wStepname = new Text(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		wStepname.setText(stepname);
		props.setLook(wStepname);
		wStepname.addModifyListener(lsMod);
		fdStepname = new FormData();
		fdStepname.left = new FormAttachment(middle, 0);
		fdStepname.right = new FormAttachment(100, 0);
		fdStepname.top = new FormAttachment(0, margin);
		wStepname.setLayoutData(fdStepname);

	}

	public void instanciateField() {
		wFilterNameField = new Combo(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		wDepositModeField = new Combo(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		wDepositModeField.add(BaseMessages.getString(PKG,
				"DataCenter.DepositModeField.One"));
		wDepositModeField.add(BaseMessages.getString(PKG,
				"DataCenter.DepositModeField.Two"));
		wDepositModeField.add(BaseMessages.getString(PKG,
				"DataCenter.DepositModeField.Three"));
		wDirNameField = new Text(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		wBlockNameField = new Combo(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		wBlockNameField.add(BaseMessages.getString(PKG,
				"DataCenter.BlockNameField.Default"));
		wFileRegexField = new Text(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
	}

	public void initLabel(String label, Control lastComponent) {

		Label wlValName = new Label(shell, SWT.RIGHT);
		wlValName.setText(BaseMessages.getString(PKG, label));
		props.setLook(wlValName);
		FormData fdlValName = new FormData();
		fdlValName.left = new FormAttachment(0, 0);
		fdlValName.right = new FormAttachment(middle, -margin);
		fdlValName.top = new FormAttachment(lastComponent, margin);
		wlValName.setLayoutData(fdlValName);
	}

	public void initField(Control textField, Control lastComponent) {
		props.setLook(textField);
		FormData fdValName = new FormData();
		fdValName.left = new FormAttachment(middle, 0);
		fdValName.right = new FormAttachment(100, 0);
		fdValName.top = new FormAttachment(lastComponent, margin);
		textField.setLayoutData(fdValName);
	}

	public void initButton() {

		wOK = new Button(shell, SWT.PUSH);
		wOK.setText(BaseMessages.getString(PKG, "System.Button.OK"));
		wCancel = new Button(shell, SWT.PUSH);
		wCancel.setText(BaseMessages.getString(PKG, "System.Button.Cancel"));

		BaseStepDialog.positionBottomButtons(shell,
				new Button[] { wOK, wCancel }, margin, wFileRegexField);

		lsOK = new Listener() {
			public void handleEvent(Event e) {
				ok();
			}
		};
		lsCancel = new Listener() {
			public void handleEvent(Event e) {
				cancel();
			}
		};

		wOK.addListener(SWT.Selection, lsOK);
		wCancel.addListener(SWT.Selection, lsCancel);
	}

}
