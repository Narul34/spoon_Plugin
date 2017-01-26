/*
 * This important class is used to communicate between pentaho environnment and this project.
 */

package org.plugbi.pentaho;

import org.pentaho.ui.xul.impl.AbstractXulEventHandler;
import org.plugbi.pentaho.loggin.LoginOut;
import org.plugbi.pentaho.loggin.LoginUI;

/**
 * A class imperatively needed to create a Spoon plug-in, it communicates between Pentaho environnment and this project.
 * 
 * @author Adrien Blanes
 * @since 01/2017
 * @version 0.5
 *
 */

public class PlugBiPerspectiveHandler extends AbstractXulEventHandler {
	
	/**
	 * This object represent the connection interface. 
	 */
	private static LoginUI lui = null;
	
	///// not used for now////////////////////
	//private MenuModel mm1 = new MenuModel();
	//////////////////////////////////////////
	

	/**
	 * An empty default constructor.
	 */
	public PlugBiPerspectiveHandler() {

	}

	//This a test class to try to change and manage the Xul files, but was not reach so far.
	/**
	 * A temporary method to try to manage Xul.
	 */
	public void managingXul(){ 
		/*System.out.println("LE DOCUMENT : " + document);*/
		// document.getElementById("open-item").setAttribute("disabled", "false");
		/*JfaceMenu xmb = (JfaceMenu) document.getElementById("myMenu");
		bind = new DefaultBinding(mm1, "okEnabled", xmb.getElementById("myMenu-popup").getElementById("open-item").getAttributeValue("disabled") , "true");
		xc.getElementById("myMenu-popup").getElementById("open-item").setAttribute("disabled", "false");
		try {
			//document.replaceChild(xc, document.getElementById("myMenu"));
		} catch (XulDomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(xc.getAttributeValue("disabled"));
		BindingFactory bf = new DefaultBindingFactory();
		bf.setDocument(document);
		Binding bind = bf.createBinding(mm1, "okEnabled", xc, "disabled");
        mm1.setOkEnabled(false);
        System.out.println(mm1.isOkEnabled());
		System.out.println(xc.getAttributeValue("disabled"));
		*/
	}
	

	/**
	 * An important getter, should match event handler aliases in the associated xul file.
	 */
	@Override
	public String getName() {
		return "myMenu";
	}
	

	/**
	 * This method is called in the xul file to display the connection UI.
	 */
	public void showLogin() {
		if (lui == null){
			lui = new LoginUI(document);
			managingXul();
		}
		if(!lui.isVisible()){
		lui = new LoginUI(document);
		managingXul();
		}
	}

	
	/**
	 * A simple UI to close connection between user and server.
	 */
	public void showLoginAlert() {
		new LoginOut("/res/plugbiicon.png");
	}

}
