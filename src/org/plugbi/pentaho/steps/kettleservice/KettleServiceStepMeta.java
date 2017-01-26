package org.plugbi.pentaho.steps.kettleservice;

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
		id = "KettleService",
		image = "org/plugbi/pentaho/steps/resources/Data-Settings.svg",
		i18nPackageName="org.plugbi.pentaho.steps.kettleservice",
		name="KettleServiceStep.Name",
		description = "KettleServiceStep.TooltipDesc",
		categoryDescription="KettleServiceStep.Test.Category"
)
public class KettleServiceStepMeta extends BaseStepMeta implements StepMetaInterface {

	private static Class<?> PKG = KettleServiceStepMeta.class; // for i18n purposes
	
	private String ktlPath;

	public KettleServiceStepMeta() {
		super(); 
	}
	
	public StepDialogInterface getDialog(Shell shell, StepMetaInterface meta, TransMeta transMeta, String name) {
		return new KettleServiceStepDialog(shell, meta, transMeta, name);
	}

	public StepInterface getStep(StepMeta stepMeta, StepDataInterface stepDataInterface, int cnr, TransMeta transMeta, Trans disp) {
		return new KettleServiceStep(stepMeta, stepDataInterface, cnr, transMeta, disp);
	}

	public StepDataInterface getStepData() {
		return new KettleServiceStepData();
	}	

	public void setDefault() {
		ktlPath = "";
	}
	
	public String getKtlPath() {
		return ktlPath;
	}

	public void setKtlPath(String ktlPath) {
		this.ktlPath = ktlPath;
	}
	
	public Object clone() {
		Object retval = super.clone();
		return retval;
	}
	
	public String getXML() throws KettleValueException {
		
		String xml = XMLHandler.addTagValue("ktlPath", ktlPath);
		return xml;
	}

	public void loadXML(Node stepnode, List<DatabaseMeta> databases, IMetaStore metaStore) throws KettleXMLException {

		try {
			setKtlPath(XMLHandler.getNodeValue(XMLHandler.getSubNode(stepnode, "ktlPath")));
		} catch (Exception e) {
			throw new KettleXMLException("Plugin unable to read step info from XML node", e);
		}

	}	
	
	public void saveRep(Repository rep, IMetaStore metaStore, ObjectId id_transformation, ObjectId id_step) throws KettleException
	{
		try{
			rep.saveStepAttribute(id_transformation, id_step, "ktlPath", ktlPath);
		}
		catch(Exception e){
			throw new KettleException("Unable to save step into repository: "+id_step, e); 
		}
	}		
	
	public void readRep(Repository rep, IMetaStore metaStore, ObjectId id_step, List<DatabaseMeta> databases) throws KettleException  {
		try{
			ktlPath  = rep.getStepAttributeString(id_step, "outputfield");
		}
		catch(Exception e){
			throw new KettleException("Unable to load step from repository", e);
		}
	}

	public void getFields(RowMetaInterface inputRowMeta, String name, RowMetaInterface[] info, StepMeta nextStep, VariableSpace space, Repository repository, IMetaStore metaStore) throws KettleStepException{

	
		ValueMetaInterface v = new ValueMeta(ktlPath, ValueMeta.TYPE_STRING);
		
		v.setTrimType(ValueMeta.TRIM_TYPE_BOTH);

		v.setOrigin(name);
		
		inputRowMeta.addValueMeta(v);
		
	}

	public void check(List<CheckResultInterface> remarks, TransMeta transMeta, StepMeta stepMeta, RowMetaInterface prev, String input[], String output[], RowMetaInterface info, VariableSpace space, Repository repository, IMetaStore metaStore)  {
		
		CheckResult cr;

		if (input.length > 0) {
			cr = new CheckResult(CheckResult.TYPE_RESULT_OK, BaseMessages.getString(PKG, "KettleService.CheckResult.ReceivingRows.OK"), stepMeta);
			remarks.add(cr);
		} else {
			cr = new CheckResult(CheckResult.TYPE_RESULT_ERROR, BaseMessages.getString(PKG, "KettleService.CheckResult.ReceivingRows.ERROR"), stepMeta);
			remarks.add(cr);
		}	
    	
	}


}
