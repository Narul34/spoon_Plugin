package org.plugbi.pentaho.steps.datacenter;

import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.trans.step.BaseStepData;
import org.pentaho.di.trans.step.StepDataInterface;

/**
 *   
 * This class is the implementation of StepDataInterface.
 *   
 * Implementing classes inherit from BaseStepData, which implements the entire
 * interface completely. 
 * 
 * In addition classes implementing this interface usually keep track of
 * per-thread resources during step execution. Typical examples are:
 * result sets, temporary data, caching indexes, etc.
 *   
 * The implementation for the demo step stores the output row structure in 
 * the data class. 
 *   
 */
public class DataCenterStepData extends BaseStepData implements StepDataInterface {

	public RowMetaInterface outputRowMeta;
	
    public DataCenterStepData()
	{
		super();
	}
}
	
