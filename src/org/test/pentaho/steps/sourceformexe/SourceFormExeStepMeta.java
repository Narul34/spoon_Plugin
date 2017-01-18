package org.test.pentaho.steps.sourceformexe;

import java.util.List;

import org.eclipse.swt.widgets.Shell;
import org.pentaho.di.core.CheckResult;
import org.pentaho.di.core.CheckResultInterface;
import org.pentaho.di.core.annotations.Step;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleStepException;
import org.pentaho.di.core.exception.KettleValueException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.core.row.ValueMeta;
import org.pentaho.di.core.row.ValueMetaInterface;
import org.pentaho.di.core.variables.VariableSpace;
import org.pentaho.di.core.xml.XMLHandler;
import org.pentaho.di.i18n.BaseMessages;
import org.pentaho.di.repository.ObjectId;
import org.pentaho.di.repository.Repository;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDataInterface;
import org.pentaho.di.trans.step.StepDialogInterface;
import org.pentaho.di.trans.step.StepInterface;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.step.StepMetaInterface;
import org.pentaho.metastore.api.IMetaStore;
import org.w3c.dom.Node;

@SuppressWarnings("deprecation")
@Step(	
		id = "SourceFormExeStep",
		image = "org/test/pentaho/steps/resources/Data-Network.svg",
		i18nPackageName="org.test.pentaho.steps.sourceformexe",
		name="SourceFormExeStep.Name",
		description = "SourceFormExeStep.TooltipDesc",
		categoryDescription="SourceFormExeStep.Test.Category"
)
public class SourceFormExeStepMeta extends BaseStepMeta implements StepMetaInterface {

	private static Class<?> PKG = SourceFormExeStepMeta.class;

	private String outputField;

	public SourceFormExeStepMeta() {
		super(); 
	}
	
	public StepDialogInterface getDialog(Shell shell, StepMetaInterface meta, TransMeta transMeta, String name) {
		return new SourceFormExeStepDialog(shell, meta, transMeta, name);
	}

	public StepInterface getStep(StepMeta stepMeta, StepDataInterface stepDataInterface, int cnr, TransMeta transMeta, Trans disp) {
		return new SourceFormExeStep(stepMeta, stepDataInterface, cnr, transMeta, disp);
	}

	public StepDataInterface getStepData() {
		return new SourceFormExeStepData();
	}	

	public void setDefault() {
		outputField = "demo_field";
	}
	
	public String getOutputField() {
		return outputField;
	}

	public void setOutputField(String outputField) {
		this.outputField = outputField;
	}
	
	public Object clone() {
		Object retval = super.clone();
		return retval;
	}
	
	public String getXML() throws KettleValueException {
		
		String xml = XMLHandler.addTagValue("outputfield", outputField);
		return xml;
	}

	public void loadXML(Node stepnode, List<DatabaseMeta> databases, IMetaStore metaStore) throws KettleXMLException {

		try {
			setOutputField(XMLHandler.getNodeValue(XMLHandler.getSubNode(stepnode, "outputfield")));
		} catch (Exception e) {
			throw new KettleXMLException("Demo plugin unable to read step info from XML node", e);
		}

	}	
	
	public void saveRep(Repository rep, IMetaStore metaStore, ObjectId id_transformation, ObjectId id_step) throws KettleException
	{
		try{
			rep.saveStepAttribute(id_transformation, id_step, "outputfield", outputField); //$NON-NLS-1$
		}
		catch(Exception e){
			throw new KettleException("Unable to save step into repository: "+id_step, e); 
		}
	}		
	
	public void readRep(Repository rep, IMetaStore metaStore, ObjectId id_step, List<DatabaseMeta> databases) throws KettleException  {
		try{
			outputField  = rep.getStepAttributeString(id_step, "outputfield"); //$NON-NLS-1$
		}
		catch(Exception e){
			throw new KettleException("Unable to load step from repository", e);
		}
	}

	public void getFields(RowMetaInterface inputRowMeta, String name, RowMetaInterface[] info, StepMeta nextStep, VariableSpace space, Repository repository, IMetaStore metaStore) throws KettleStepException{

		
		ValueMetaInterface v = new ValueMeta(outputField, ValueMeta.TYPE_STRING);
		v.setTrimType(ValueMeta.TRIM_TYPE_BOTH);
		v.setOrigin(name); 
		inputRowMeta.addValueMeta(v);
		
	}

	public void check(List<CheckResultInterface> remarks, TransMeta transMeta, StepMeta stepMeta, RowMetaInterface prev, String input[], String output[], RowMetaInterface info, VariableSpace space, Repository repository, IMetaStore metaStore)  {
		
		CheckResult cr;

		if (input.length > 0) {
			cr = new CheckResult(CheckResult.TYPE_RESULT_OK, BaseMessages.getString(PKG, "SourceFormExe.CheckResult.ReceivingRows.OK"), stepMeta);
			remarks.add(cr);
		} else {
			cr = new CheckResult(CheckResult.TYPE_RESULT_ERROR, BaseMessages.getString(PKG, "SourceFormExe.CheckResult.ReceivingRows.ERROR"), stepMeta);
			remarks.add(cr);
		}	
    	
	}


}
