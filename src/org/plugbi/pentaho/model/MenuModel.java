package org.plugbi.pentaho.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.pentaho.ui.xul.XulEventSource;

// A class made to be used in the handler to try managing Xul values.
/**
 * A class made to be used in the handler to try managing Xul values.
 * 
 * @author Adrien Blanes
 *
 */
public class MenuModel implements Serializable, XulEventSource  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		PropertyChangeSupport pcs;
		private boolean okEnabled;
		private String name;
		
		public MenuModel(){
			pcs = new PropertyChangeSupport(this);
		}

		public boolean isOkEnabled() {
			return okEnabled;
		}
		// Any bean property that is using for binding must fire property change events
		public void setOkEnabled(boolean okEnabled) {
		 	this.okEnabled = okEnabled;
			pcs.firePropertyChange("okEnabled", null, okEnabled);
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
			pcs.firePropertyChange("name", null, name);
			validate();
		}

		// A very simple validator that allows the user to procede if there is anything in the name field.
		// Notice that we are not manipulating  UI component here to achieve this, just a bean property.
		private void validate() {
			setOkEnabled(!StringUtils.isEmpty(name));
		}

		@Override
		public void addPropertyChangeListener(PropertyChangeListener arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removePropertyChangeListener(PropertyChangeListener arg0) {
			// TODO Auto-generated method stub
			
		}
	}

