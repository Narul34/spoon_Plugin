package org.plugbi.pentaho.steps.sourceformexe;

import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.trans.step.BaseStepData;
import org.pentaho.di.trans.step.StepDataInterface;

public class SourceFormExeStepData extends BaseStepData implements StepDataInterface {

	public RowMetaInterface outputRowMeta;
	
    public SourceFormExeStepData()
	{
		super();
	}
}
	
