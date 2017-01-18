package org.test.pentaho.steps.externalagent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
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
import org.eclipse.swt.widgets.Composite;
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
 * This class is part of the demo step plug-in implementation.
 * It demonstrates the basics of developing a plug-in step for PDI. 
 * 
 * The demo step adds a new string field to the row stream and sets its
 * value to "Hello World!". The user may select the name of the new field.
 *   
 * This class is the implementation of StepDialogInterface.
 * Classes implementing this interface need to:
 * 
 * - build and open a SWT dialog displaying the step's settings (stored in the step's meta object)
 * - write back any changes the user makes to the step's meta object
 * - report whether the user changed any settings when confirming the dialog 
 * 
 */
public class ExternalAgentStepDialog extends BaseStepDialog implements StepDialogInterface {

	
	private static Class<?> PKG = ExternalAgentStepMeta.class; 
	private ExternalAgentStepMeta meta;
	private CTabFolder tf;
	private CTabItem tab1, tab2;	
	private Composite tabPage1, tabPage2;
	private Combo wAgentNameField;	
	private Text  wOptionField, wVMOptionField;	
	private Text wClassPath, wMainClass, wInstallDir, wProcessName, wPbiLibPath, wLabel;	
	private int margin, middle;	
	private ModifyListener lsMod;

	public ExternalAgentStepDialog(Shell parent, Object in, TransMeta transMeta, String sname) {
		super(parent, (BaseStepMeta) in, transMeta, sname);
		meta = (ExternalAgentStepMeta) in;
	}

	public String open() {

		Shell parent = getParent();
		Display display = parent.getDisplay();

		shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MIN | SWT.MAX);
		props.setLook(shell);
		setShellImage(shell, meta);
		
		changed = meta.hasChanged();
		
		lsMod = new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				meta.setChanged();
			}
		};
		
		FormLayout formLayout = new FormLayout();
		formLayout.marginWidth = Const.FORM_MARGIN;
		formLayout.marginHeight = Const.FORM_MARGIN;

		shell.setLayout(formLayout);
		shell.setText(BaseMessages.getString(PKG, "ExternalAgent.Shell.Title")); 

		middle = props.getMiddlePct();
		margin = Const.MARGIN;

		init();
		
		      
		lsDef = new SelectionAdapter() {
			public void widgetDefaultSelected(SelectionEvent e) {ok();}
		};
		wStepname.addSelectionListener(lsDef);

		shell.addShellListener(new ShellAdapter() {
			public void shellClosed(ShellEvent e) {cancel();}
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
	
	private void populateDialog() {
		wStepname.selectAll();
	}

	private void cancel() {
		stepname = null;
		meta.setChanged(changed);
		dispose();
	}
	
	private void ok() {
		stepname = wStepname.getText(); 
		meta.setAgentNameField(wAgentNameField.getText());
		dispose();
	}
	
	public void init() {
		
		stepNameLine();
		
		initTab();

		instanciateField();

		initLine();

		initButton();
		
		tab1.setControl(tabPage1);
		tab2.setControl(tabPage2);
		tf.setSelection(tab1);
		
	    FormData fdTabFolder = new FormData();
	    fdTabFolder.left = new FormAttachment(0, 0);
	    fdTabFolder.top = new FormAttachment(this.wStepname, 4);
	    fdTabFolder.right = new FormAttachment(100, 0);
	    fdTabFolder.bottom = new FormAttachment(this.wOK, -4);
	    tf.setLayoutData(fdTabFolder);

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
	
	public void initTab(){
		
		tf = new CTabFolder(shell, 2048);
		props.setLook(tf, 5);
		tab1 = new CTabItem(tf, SWT.NONE);
		tab1.setText(BaseMessages.getString(PKG, "ExternalAgent.TabOne.Label")); 
		tab2 = new CTabItem(tf, SWT.NONE);
		tab2.setText(BaseMessages.getString(PKG, "ExternalAgent.TabTwo.Label"));

	    FormLayout mainOptionsLayout = new FormLayout();
	    mainOptionsLayout.marginWidth = 3;
	    mainOptionsLayout.marginHeight = 3;
		
		tabPage1 = new Composite(tf, SWT.NONE);
		props.setLook(tabPage1);
		tabPage2 = new Composite(tf, SWT.NONE);
		props.setLook(tabPage2);
		tabPage1.setLayout(mainOptionsLayout);
		tabPage2.setLayout(mainOptionsLayout);
		
	}
	
	public void instanciateField() {
		wAgentNameField = new Combo(tabPage1, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		wOptionField = new Text(tabPage1, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		wVMOptionField = new Text(tabPage1, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		
		wClassPath = new Text(tabPage2, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		wMainClass = new Text(tabPage2, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		wInstallDir = new Text(tabPage2, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		wProcessName = new Text(tabPage2, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		wPbiLibPath = new Text(tabPage2, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		wLabel = new Text(tabPage2, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		
	}
	
	public void initLine(){
		
		initLabelTab(tabPage1, "ExternalAgent.AgentNameField.Label", wStepname);
		initFieldTab(wAgentNameField, wStepname);
		initLabelTab(tabPage1,"ExternalAgent.Option.Label", wAgentNameField);
		initFieldTab(wOptionField, wAgentNameField);
		initLabelTab(tabPage1,"ExternalAgent.VMOption.Label", wOptionField);
		initFieldTab(wVMOptionField, wOptionField);
		initLabelTab(tabPage2,"ExternalAgent.ClassPath.Label", wStepname);
		initFieldTab(wClassPath, wStepname);
		initLabelTab(tabPage2,"ExternalAgent.MainClass.Label", wClassPath);
		initFieldTab(wMainClass, wClassPath);
		initLabelTab(tabPage2,"ExternalAgent.InstallDir.Label", wMainClass);
		initFieldTab(wInstallDir, wMainClass);	
		initLabelTab(tabPage2,"ExternalAgent.ProcessName.Label", wInstallDir);
		initFieldTab(wProcessName, wInstallDir);
		initLabelTab(tabPage2,"ExternalAgent.PbiLibPath.Label", wProcessName);
		initFieldTab(wPbiLibPath, wProcessName);
		initLabelTab(tabPage2,"ExternalAgent.Label.Label", wPbiLibPath);
		initFieldTab(wLabel, wPbiLibPath);
		
	}

	public void initLabelTab(Composite composite, String label, Control lastComponent) {

		Label wlValName = new Label(composite, SWT.RIGHT);
		wlValName.setText(BaseMessages.getString(PKG, label));
		props.setLook(wlValName);
		FormData fdlValName = new FormData();
		fdlValName.left = new FormAttachment(0, 0);
		fdlValName.right = new FormAttachment(middle, -margin);
		fdlValName.top = new FormAttachment(lastComponent, margin);
		wlValName.setLayoutData(fdlValName);
	}

	public void initFieldTab(Control textField, Control lastComponent) {
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
				new Button[] { wOK, wCancel }, margin, null);

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
