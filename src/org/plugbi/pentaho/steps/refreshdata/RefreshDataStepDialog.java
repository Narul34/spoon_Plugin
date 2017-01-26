package org.plugbi.pentaho.steps.refreshdata;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.pentaho.di.core.Const;
import org.pentaho.di.i18n.BaseMessages;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDialogInterface;
import org.pentaho.di.ui.trans.step.BaseStepDialog;

public class RefreshDataStepDialog extends BaseStepDialog implements StepDialogInterface {


	private static Class<?> PKG = RefreshDataStepMeta.class;

	private RefreshDataStepMeta meta;
	
	private  int middle, margin;
	
	private ModifyListener lsMod;
	
	private Composite gField;
	
	private Composite command, compoSource, compoCalc;
	
	private Table sourceTable, calcTable;
	
	private TableColumn columnSrc, columnCalc;
	
	/*
	 * TODO
	 * Manage list of file
	 */	
	//private TableItem[] firstList, secondList;
	
	private TableColumnLayout tableColumnLayout;
	
	private Button wLeftArrow, wDoubleLeftArrow, wRightArrow, wDoubleRightArrow;

	public RefreshDataStepDialog(Shell parent, Object in, TransMeta transMeta, String sname) {
		super(parent, (BaseStepMeta) in, transMeta, sname);
		meta = (RefreshDataStepMeta) in;
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
		
		middle = props.getMiddlePct();
		margin = Const.MARGIN;
	
		FormLayout formLayout = new FormLayout();
		formLayout.marginWidth = Const.FORM_MARGIN;
		formLayout.marginHeight = Const.FORM_MARGIN;

		shell.setLayout(formLayout);
		shell.setText(BaseMessages.getString(PKG, "RefreshData.Shell.Title")); 
		
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
		
		initGroup();
		initTable(sourceTable, columnSrc, "RefreshData.SourceList.Title", 5);
		initCommand();
		initTable(calcTable, columnCalc, "RefreshData.CalcList.Title", 0);
		
		initButton();

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
	
	public void instanciateField() {
		
		gField = new Composite(shell, SWT.NONE);
		
		compoSource = new Composite(gField, SWT.NONE);
		sourceTable = new Table(compoSource, SWT.BORDER);
		
		command = new Composite(gField, SWT.NONE);
		
		compoCalc = new Composite(gField, SWT.NONE);
		calcTable = new Table(compoCalc, SWT.BORDER);
		
		tableColumnLayout = new TableColumnLayout();
		compoSource.setLayout(tableColumnLayout);
		compoCalc.setLayout(tableColumnLayout);
		
	}
	
	public void initGroup(){
		props.setLook(gField);
		gField.setLayout(new FillLayout());
		FormData gld = new FormData();
		gld.left = new FormAttachment(0, 0);
		gld.right = new FormAttachment(100, 0);
		gld.top = new FormAttachment(wStepname, margin*2);
		gld.bottom = new FormAttachment(80);
		gField.setLayoutData(gld);
		
	}

	public void initTable(Table table, TableColumn column, String string, int entryNumber) {
		
		table.setHeaderVisible(true);
		props.setLook(table);
		
		column = new TableColumn(table, SWT.CENTER);
		column.setText(BaseMessages.getString(PKG, string));
		
		/*
		 * TODO
		 * Manage list of files
		 * 
		 */
		
		for (int i = 0 ; i < entryNumber; i++){
		TableItem ti = new TableItem(table,SWT.NONE);
		ti.setText(new String("valeur " + (i+1)));
		}
		
		tableColumnLayout.setColumnData(column, new ColumnWeightData(100));
	}
	
	public void initCommand(){
		GridLayout gl = new GridLayout();
		GridData gd = new GridData(SWT.CENTER, SWT.CENTER, true, true);
		gl.numColumns = 1;
		gl.verticalSpacing = 5;
		command.setLayout(gl);
		props.setLook(command);
		
		wRightArrow = new Button(command, SWT.PUSH);
		wRightArrow.setText(">");
		wRightArrow.setLayoutData(gd);
		wRightArrow.setToolTipText("Supprimer");
		wRightArrow.addListener(SWT.Selection, new Listener(){

			@Override
			public void handleEvent(Event e) {
		        TableItem[] selection = sourceTable.getSelection();
		        for(int i = 0 ; i < selection.length; i++){
		        TableItem ti = new TableItem(calcTable, SWT.NONE);
				ti.setText(selection[i].getText());
		        }
		        sourceTable.remove(sourceTable.getSelectionIndices());
		        sourceTable.pack();
		        calcTable.pack();
			}
			
		});
		
		wDoubleRightArrow = new Button(command, SWT.PUSH);
		wDoubleRightArrow.setText(">>");
		wDoubleRightArrow.setLayoutData(gd);
		wDoubleRightArrow.setToolTipText("Supprimer");
		wDoubleRightArrow.addListener(SWT.Selection, new Listener(){

			@Override
			public void handleEvent(Event e) {
		        TableItem[] selection = sourceTable.getItems();
		        for(int i = 0 ; i < selection.length; i++){
		        TableItem ti = new TableItem(calcTable, SWT.NONE);
				ti.setText(selection[i].getText());
		        }
		        sourceTable.removeAll();
		        sourceTable.pack();
		        calcTable.pack();
			}
		});
		
		wLeftArrow = new Button(command, SWT.PUSH);
		wLeftArrow.setText("<");
		wLeftArrow.setLayoutData(gd);
		wLeftArrow.setToolTipText("Supprimer");
		wLeftArrow.addListener(SWT.Selection, new Listener(){

			@Override
			public void handleEvent(Event e) {
		        TableItem[] selection = calcTable.getSelection();
		        for(int i = 0 ; i < selection.length; i++){
		        TableItem ti = new TableItem(sourceTable, SWT.NONE);
				ti.setText(selection[i].getText());
		        }
		        calcTable.remove(calcTable.getSelectionIndices());
		        sourceTable.pack();
		        calcTable.pack();
			}
		});
		
		wDoubleLeftArrow = new Button(command, SWT.PUSH);
		wDoubleLeftArrow.setText("<<");
		wDoubleLeftArrow.setLayoutData(gd);
		wDoubleLeftArrow.setToolTipText("Supprimer tout");
		wDoubleLeftArrow.addListener(SWT.Selection, new Listener(){

			@Override
			public void handleEvent(Event e) {
		        TableItem[] selection = calcTable.getItems();
		        for(int i = 0 ; i < selection.length; i++){
		        TableItem ti = new TableItem(sourceTable, SWT.NONE);
				ti.setText(selection[i].getText());
		        }
		        calcTable.removeAll();
		        sourceTable.pack();
		        calcTable.pack();
			}
		});
		
		
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
