package org.test.pentaho.steps.restartjobview;

import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.trans.step.BaseStepData;
import org.pentaho.di.trans.step.StepDataInterface;

public class RestartJobViewStepData extends BaseStepData implements StepDataInterface {

	public RowMetaInterface outputRowMeta;
	
    public RestartJobViewStepData()
	{
		super();
	}
}
	
