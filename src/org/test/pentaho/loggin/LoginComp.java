package org.test.pentaho.loggin;

import javax.swing.JLabel;
import javax.swing.JPanel;


public abstract class LoginComp {
	
	protected JPanel titlePanel, firstRowPanel, secondRowpanel;
	
	protected void titlePanelInit(String name){
		titlePanel = new JPanel();
		titlePanel.add(new JLabel(name));
		
	}
	
	protected void firstRowInit(){
		
	}
	
	protected void secondRowInit(){
		
	}

}
