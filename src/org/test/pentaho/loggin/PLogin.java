package org.test.pentaho.loggin;

import javax.swing.JPanel;

public class PLogin extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel titlePanel, firstRowPanel, secondRowpanel;

	public PLogin(){
		titlePanelInit();
	}
	
	public void titlePanelInit(String name){
		titlePanel = new JPanel();
		titlePanel.add(new JLabel(name))
	}

}
