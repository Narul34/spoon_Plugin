package org.plugbi.pentaho.steps.sourceformexe;

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

public class SourceFormExeStepDialog extends BaseStepDialog implements StepDialogInterface {


	private static Class<?> PKG = SourceFormExeStepMeta.class;

	private SourceFormExeStepMeta meta;
	
	private Combo wBlockName;
	
	private Text wBlockFormula;
	
	private  int middle, margin;
	
	private ModifyListener lsMod;

	public SourceFormExeStepDialog(Shell parent, Object in, TransMeta transMeta, String sname) {
		super(parent, (BaseStepMeta) in, transMeta, sname);
		meta = (SourceFormExeStepMeta) in;
	}

	public String open() {
		
		Shell parent = getParent();
		Display display = parent.getDisplay();

		shell = new Shell(parent, SWT.DIALOG_TRIM |SWT.RESIZE | SWT.MIN | SWT.MAX);
		props.setLook(shell);
		setShellImage(shell, meta);
		
		changed = meta.hasChanged();
		
		lsMod = new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				meta.setChanged();
			}
		};
		
		middle = props.getMiddlePct();
		margin = Const.MARGIN;
	
		FormLayout formLayout = new FormLayout();
		formLayout.marginWidth = Const.FORM_MARGIN;
		formLayout.marginHeight = Const.FORM_MARGIN;

		shell.setLayout(formLayout);
		shell.setText(BaseMessages.getString(PKG, "SourceFormExe.Shell.Title")); 
		
		init();

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
		wBlockName.setText(meta.getBlockName());
	}

	private void cancel() {
		stepname = null;
		meta.setChanged(changed);
		dispose();
	}
	
	private void ok() {
		stepname = wStepname.getText();
		dispose();
	}
	
	public void init() {
		
		stepNameLine();
		
		instanciateField();
		
		initLabel("SourceFormExe.BlockName.Label", wStepname);
		initField(wBlockName, wStepname, null);
		initLabel("SourceFormExe.BlockFormula.Label" , wBlockName);
		initField(wBlockFormula, wBlockName, 60);
		
		initButton();

	}
	
	public void instanciateField() {
		wBlockName = new Combo(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		wBlockFormula = new Text(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
	}

	private void stepNameLine() {
		wlStepname = new Label(shell, SWT.RIGHT);
		wlStepname.setText(BaseMessages.getString(PKG, "System.Label.StepName"));
		props.setLook(wlStepname);
		fdlStepname = new FormData();
		fdlStepname.left = new FormAttachment(0, 0);
		fdlStepname.right = new FormAttachment(middle - middle/2 + middle/4, -margin);
		fdlStepname.top = new FormAttachment(0, margin);
		wlStepname.setLayoutData(fdlStepname);

		wStepname = new Text(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		wStepname.setText(stepname);
		props.setLook(wStepname);
		wStepname.addModifyListener(lsMod);
		fdStepname = new FormData();
		fdStepname.left = new FormAttachment(middle - middle/2 + middle/4, 0);
		fdStepname.right = new FormAttachment(80, 0);
		fdStepname.top = new FormAttachment(0, margin);
		wStepname.setLayoutData(fdStepname);
		
		lsDef = new SelectionAdapter() {
			public void widgetDefaultSelected(SelectionEvent e) {ok();}
		};
		wStepname.addSelectionListener(lsDef);

	}
	
	public void initLabel(String label, Control lastComponent) {

		Label wlValName = new Label(shell, SWT.RIGHT);
		wlValName.setText(BaseMessages.getString(PKG, label));
		props.setLook(wlValName);
		FormData fdlValName = new FormData();
		fdlValName.left = new FormAttachment(0, 0);
		fdlValName.right = new FormAttachment(middle - middle/2 + middle/4, -margin);
		fdlValName.top = new FormAttachment(lastComponent, margin);
		wlValName.setLayoutData(fdlValName);
	}

	public void initField(Control textField, Control lastComponent, Integer height) {
		props.setLook(textField);
		FormData fdValName = new FormData();
		fdValName.left = new FormAttachment(middle - middle/2 + middle/4, 0);
		fdValName.right = new FormAttachment(80, 0);
		fdValName.top = new FormAttachment(lastComponent, margin);
		if(height != null){
			fdValName.bottom = new FormAttachment(height, 0);
		}
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
