package org.test.pentaho.steps.sourceformexe;

import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.row.RowDataUtil;
import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStep;
import org.pentaho.di.trans.step.StepDataInterface;
import org.pentaho.di.trans.step.StepInterface;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.step.StepMetaInterface;

public class SourceFormExeStep extends BaseStep implements StepInterface {

	public SourceFormExeStep(StepMeta s, StepDataInterface stepDataInterface, int c, TransMeta t, Trans dis) {
		super(s, stepDataInterface, c, t, dis);
	}
	
	public boolean init(StepMetaInterface smi, StepDataInterface sdi) {
		SourceFormExeStepMeta meta = (SourceFormExeStepMeta) smi;
		SourceFormExeStepData data = (SourceFormExeStepData) sdi;

		return super.init(meta, data);
	}	

	public boolean processRow(StepMetaInterface smi, StepDataInterface sdi) throws KettleException {

		SourceFormExeStepMeta meta = (SourceFormExeStepMeta) smi;
		SourceFormExeStepData data = (SourceFormExeStepData) sdi;

		Object[] r = getRow(); 
		
		if (r == null){
			setOutputDone();
			return false;
		}

		if (first) {
			first = false;
			data.outputRowMeta = (RowMetaInterface) getInputRowMeta().clone();
			meta.getFields(data.outputRowMeta, getStepname(), null, null, this, null, null);
		}

		
		Object[] outputRow = RowDataUtil.addValueData(r, data.outputRowMeta.size() - 1, "Hello World!");

		putRow(data.outputRowMeta, outputRow); 

		if (checkFeedback(getLinesRead())) {
			logBasic("Linenr " + getLinesRead()); 
		}

		return true;
	}

	public void dispose(StepMetaInterface smi, StepDataInterface sdi) {

		SourceFormExeStepMeta meta = (SourceFormExeStepMeta) smi;
		SourceFormExeStepData data = (SourceFormExeStepData) sdi;
		
		super.dispose(meta, data);
	}

}
