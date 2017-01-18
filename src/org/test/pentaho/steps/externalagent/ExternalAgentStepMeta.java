package org.test.pentaho.steps.externalagent;

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
		id = "ExternalAgentStep",
		image = "org/test/pentaho/steps/resources/Settings-Window.svg",
		i18nPackageName="org.test.pentaho.steps.externalagent",
		name="ExternalAgentStep.Name",
		description = "ExternalAgentStep.TooltipDesc",
		categoryDescription="ExternalAgentStep.Test.Category"
)
public class ExternalAgentStepMeta extends BaseStepMeta implements StepMetaInterface {

	private static Class<?> PKG = ExternalAgentStepMeta.class; // for i18n purposes
	
	private String agentNameField, optionField, vmOptionField;
	
	private String classPath, mainClass, installDir, processName, pbiLibPath, label;

	public ExternalAgentStepMeta() {
		super(); 
	}
	
	public StepDialogInterface getDialog(Shell shell, StepMetaInterface meta, TransMeta transMeta, String name) {
		return new ExternalAgentStepDialog(shell, meta, transMeta, name);
	}

	public StepInterface getStep(StepMeta stepMeta, StepDataInterface stepDataInterface, int cnr, TransMeta transMeta, Trans disp) {
		return new ExternalAgentStep(stepMeta, stepDataInterface, cnr, transMeta, disp);
	}

	public StepDataInterface getStepData() {
		return new ExternalAgentStepData();
	}	

	public void setDefault() {
		agentNameField = "";
		optionField = ""; 
		vmOptionField = "";
		classPath = "";
		mainClass = "";
		installDir = "";
		processName = "";
		pbiLibPath = "";
		label = "";
	}
	
	public String getAgentNameField() {
		return agentNameField;
	}

	public void setAgentNameField(String agentNameField) {
		this.agentNameField = agentNameField;
	}
	
	public String getOptionField() {
		return optionField;
	}

	public void setOptionField(String optionField) {
		this.optionField = optionField;
	}

	public String getVmOptionField() {
		return vmOptionField;
	}

	public void setVmOptionField(String vmOptionField) {
		this.vmOptionField = vmOptionField;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public String getMainClass() {
		return mainClass;
	}

	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}

	public String getInstallDir() {
		return installDir;
	}

	public void setInstallDir(String installDir) {
		this.installDir = installDir;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getPbiLibPath() {
		return pbiLibPath;
	}

	public void setPbiLibPath(String pbiLibPath) {
		this.pbiLibPath = pbiLibPath;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * This method is used when a step is duplicated in Spoon. It needs to return a deep copy of this
	 * step meta object. Be sure to create proper deep copies if the step configuration is stored in
	 * modifiable objects.
	 * 
	 * See org.pentaho.di.trans.steps.rowgenerator.RowGeneratorMeta.clone() for an example on creating
	 * a deep copy.
	 * 
	 * @return a deep copy of this
	 */
	public Object clone() {
		Object retval = super.clone();
		return retval;
	}
	
	/**
	 * This method is called by Spoon when a step needs to serialize its configuration to XML. The expected
	 * return value is an XML fragment consisting of one or more XML tags.  
	 * 
	 * Please use org.pentaho.di.core.xml.XMLHandler to conveniently generate the XML.
	 * 
	 * @return a string containing the XML serialization of this step
	 */
	public String getXML() throws KettleValueException {
		
		// only one field to serialize
		String xml = XMLHandler.addTagValue("agentNameField", agentNameField)
				+ XMLHandler.addTagValue("optionField", optionField)
				+ XMLHandler.addTagValue("vmOptionField", vmOptionField)
				+ XMLHandler.addTagValue("classPath", classPath)
				+ XMLHandler.addTagValue("mainClass", mainClass)
				+ XMLHandler.addTagValue("installDir", installDir)
				+ XMLHandler.addTagValue("processName", processName)
				+ XMLHandler.addTagValue("pbiLibPath", pbiLibPath)
				+ XMLHandler.addTagValue("label", label);
		
		return xml;
	}

	/**
	 * This method is called by PDI when a step needs to load its configuration from XML.
	 * 
	 * Please use org.pentaho.di.core.xml.XMLHandler to conveniently read from the
	 * XML node passed in.
	 * 
	 * @param stepnode	the XML node containing the configuration
	 * @param databases	the databases available in the transformation
	 * @param metaStore the metaStore to optionally read from
	 */
	public void loadXML(Node stepnode, List<DatabaseMeta> databases, IMetaStore metaStore) throws KettleXMLException {

		try {
			setAgentNameField(XMLHandler.getNodeValue(XMLHandler.getSubNode(stepnode, "agentNameField")));
			setOptionField(XMLHandler.getNodeValue(XMLHandler.getSubNode(stepnode, "optionField")));
			setVmOptionField(XMLHandler.getNodeValue(XMLHandler.getSubNode(stepnode, "vmOptionField")));
			setClassPath(XMLHandler.getNodeValue(XMLHandler.getSubNode(stepnode, "classPath")));
			setMainClass(XMLHandler.getNodeValue(XMLHandler.getSubNode(stepnode, "mainClass")));
			setInstallDir(XMLHandler.getNodeValue(XMLHandler.getSubNode(stepnode, "installDir")));
			setProcessName(XMLHandler.getNodeValue(XMLHandler.getSubNode(stepnode, "processName")));
			setPbiLibPath(XMLHandler.getNodeValue(XMLHandler.getSubNode(stepnode, "pbiLibPath")));
			setLabel(XMLHandler.getNodeValue(XMLHandler.getSubNode(stepnode, "label")));
		} catch (Exception e) {
			throw new KettleXMLException("Demo plugin unable to read step info from XML node", e);
		}

	}	
	
	/**
	 * This method is called by Spoon when a step needs to serialize its configuration to a repository.
	 * The repository implementation provides the necessary methods to save the step attributes.
	 *
	 * @param rep					the repository to save to
	 * @param metaStore				the metaStore to optionally write to
	 * @param id_transformation		the id to use for the transformation when saving
	 * @param id_step				the id to use for the step  when saving
	 */
	public void saveRep(Repository rep, IMetaStore metaStore, ObjectId id_transformation, ObjectId id_step) throws KettleException
	{
		try{
			rep.saveStepAttribute(id_transformation, id_step, "agentNameField", agentNameField); //$NON-NLS-1$
			rep.saveStepAttribute(id_transformation, id_step, "optionField", optionField);
			rep.saveStepAttribute(id_transformation, id_step, "vmOptionField", vmOptionField);
			rep.saveStepAttribute(id_transformation, id_step, "classPath", classPath);
			rep.saveStepAttribute(id_transformation, id_step, "mainClass", mainClass);
			rep.saveStepAttribute(id_transformation, id_step, "installDir", installDir);
			rep.saveStepAttribute(id_transformation, id_step, "processName", processName);
			rep.saveStepAttribute(id_transformation, id_step, "pbiLibPath", pbiLibPath);
			rep.saveStepAttribute(id_transformation, id_step, "label", label);
		}
		catch(Exception e){
			throw new KettleException("Unable to save step into repository: "+id_step, e); 
		}
	}		
	
	/**
	 * This method is called by PDI when a step needs to read its configuration from a repository.
	 * The repository implementation provides the necessary methods to read the step attributes.
	 * 
	 * @param rep		the repository to read from
	 * @param metaStore	the metaStore to optionally read from
	 * @param id_step	the id of the step being read
	 * @param databases	the databases available in the transformation
	 * @param counters	the counters available in the transformation
	 */
	public void readRep(Repository rep, IMetaStore metaStore, ObjectId id_step, List<DatabaseMeta> databases) throws KettleException  {
		try{
			agentNameField  = rep.getStepAttributeString(id_step, "agentNameField"); //$NON-NLS-1$
			optionField  = rep.getStepAttributeString(id_step, "optionField");
			vmOptionField  = rep.getStepAttributeString(id_step, "vmOptionField");
			classPath  = rep.getStepAttributeString(id_step, "classPath");
			mainClass  = rep.getStepAttributeString(id_step, "mainClass");
			installDir  = rep.getStepAttributeString(id_step, "installDir");
			processName  = rep.getStepAttributeString(id_step, "processName");
			pbiLibPath  = rep.getStepAttributeString(id_step, "pbiLibPath");
			label  = rep.getStepAttributeString(id_step, "label");
		}
		catch(Exception e){
			throw new KettleException("Unable to load step from repository", e);
		}
	}

	/**
	 * This method is called to determine the changes the step is making to the row-stream.
	 * To that end a RowMetaInterface object is passed in, containing the row-stream structure as it is when entering
	 * the step. This method must apply any changes the step makes to the row stream. Usually a step adds fields to the
	 * row-stream.
	 * 
	 * @param inputRowMeta		the row structure coming in to the step
	 * @param name 				the name of the step making the changes
	 * @param info				row structures of any info steps coming in
	 * @param nextStep			the description of a step this step is passing rows to
	 * @param space				the variable space for resolving variables
	 * @param repository		the repository instance optionally read from
	 * @param metaStore			the metaStore to optionally read from
	 */
	public void getFields(RowMetaInterface inputRowMeta, String name, RowMetaInterface[] info, StepMeta nextStep, VariableSpace space, Repository repository, IMetaStore metaStore) throws KettleStepException{

		/*
		 * This implementation appends the outputField to the row-stream
		 */

		// a value meta object contains the meta data for a field
		ValueMetaInterface v = new ValueMeta(agentNameField, ValueMeta.TYPE_STRING);
		
		// setting trim type to "both"
		v.setTrimType(ValueMeta.TRIM_TYPE_BOTH);

		// the name of the step that adds this field
		v.setOrigin(name);
		
		// modify the row structure and add the field this step generates  
		inputRowMeta.addValueMeta(v);
		
	}

	/**
	 * This method is called when the user selects the "Verify Transformation" option in Spoon. 
	 * A list of remarks is passed in that this method should add to. Each remark is a comment, warning, error, or ok.
	 * The method should perform as many checks as necessary to catch design-time errors.
	 * 
	 * Typical checks include:
	 * - verify that all mandatory configuration is given
	 * - verify that the step receives any input, unless it's a row generating step
	 * - verify that the step does not receive any input if it does not take them into account
	 * - verify that the step finds fields it relies on in the row-stream
	 * 
	 *   @param remarks		the list of remarks to append to
	 *   @param transmeta	the description of the transformation
	 *   @param stepMeta	the description of the step
	 *   @param prev		the structure of the incoming row-stream
	 *   @param input		names of steps sending input to the step
	 *   @param output		names of steps this step is sending output to
	 *   @param info		fields coming in from info steps 
	 *   @param metaStore	metaStore to optionally read from
	 */
	public void check(List<CheckResultInterface> remarks, TransMeta transMeta, StepMeta stepMeta, RowMetaInterface prev, String input[], String output[], RowMetaInterface info, VariableSpace space, Repository repository, IMetaStore metaStore)  {
		
		CheckResult cr;

		// See if there are input streams leading to this step!
		if (input.length > 0) {
			cr = new CheckResult(CheckResult.TYPE_RESULT_OK, BaseMessages.getString(PKG, "ExternalAgent.CheckResult.ReceivingRows.OK"), stepMeta);
			remarks.add(cr);
		} else {
			cr = new CheckResult(CheckResult.TYPE_RESULT_ERROR, BaseMessages.getString(PKG, "ExternalAgent.CheckResult.ReceivingRows.ERROR"), stepMeta);
			remarks.add(cr);
		}	
    	
	}


}
