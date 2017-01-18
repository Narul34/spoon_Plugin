/*
 * This important class is used to communicate between pentaho environnment and this project.
 */

package org.test.pentaho;

import org.pentaho.ui.xul.impl.AbstractXulEventHandler;
import org.test.pentaho.loggin.LoginUI;
import org.test.pentaho.loggin.LoginOut;

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
	
	
	//private MenuModel mm1 = new MenuModel();
	

	public PlugBiPerspectiveHandler() {

	}

	
	public void test(){
		System.out.println("ici c'est le doc tavu rpzZZZ : " + document);
		//XulComponent xc =   (XulComponent) document.getElementById("myMenu").getElementById("open-item");
		//JfaceMenu xmb = (JfaceMenu) document.getElementById("myMenu");
		//bind = new DefaultBinding(mm1, "okEnabled", xmb.getElementById("myMenu-popup").getElementById("open-item").getAttributeValue("disabled") , "true");
		/*System.out.println("lalala c'est la tavu lame culo : : : :" +xc.getElementById("myMenu-popup").getElementById("open-item").getAttributeValue("disabled"));
		xc.getElementById("myMenu-popup").getElementById("open-item").setAttribute("disabled", "false");
		System.out.println("lalala c'est la tavu lame culo : : : :" +document.getElementById("myMenu").getElementById("myMenu-popup").getElementById("open-item").getAttributeValue("disabled"));
		System.out.println("ici  c'est  la string du xul compojnent : " + xc.getText());*/
		/*try {
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
	
	
	public void onLoad(){
		
	}

	/**
	 * This method is called in the xul file to display the connection UI.
	 */
	public void showLogin() {
		if (lui == null){
			lui = new LoginUI(document);
			test();
		}
		if(!lui.isVisible())
		lui = new LoginUI(document);
	}

	
	/**
	 * A simple UI to close connection between user and server.
	 */
	public void showLoginAlert() {
		new LoginOut();
	}

}
