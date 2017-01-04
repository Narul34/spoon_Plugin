/*
 * This important class is using to communicate between pentaho environnment and this project.
 */

package org.test.pentaho;

import org.pentaho.ui.xul.XulComponent;
import org.pentaho.ui.xul.binding.Binding;
import org.pentaho.ui.xul.binding.BindingFactory;
import org.pentaho.ui.xul.binding.DefaultBinding;
import org.pentaho.ui.xul.binding.DefaultBindingFactory;
import org.pentaho.ui.xul.components.XulToolbarbutton;
import org.pentaho.ui.xul.impl.AbstractXulEventHandler;
import org.pentaho.ui.xul.swing.SwingBindingFactory;
import org.test.pentaho.loggin.LoginUI;
import org.test.pentaho.loggin.Unlogin;
import org.test.pentaho.model.MenuModel;
import org.test.pentaho.option.OptionUI;



public class PlugBiPerspectiveHandler extends AbstractXulEventHandler {
	
	private LoginUI lui = null;
	private MenuModel mm1 = new MenuModel();
	private Binding bind;
	

	public PlugBiPerspectiveHandler() {

	}

	
	public void test(){
		System.out.println("ici c'est le doc tavu rpzZZZ : " + document);
		XulComponent xc =   (XulComponent) document.getElementById("myMenu").getElementById("open-item");
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
		}*/
		
		System.out.println(xc.getAttributeValue("disabled"));
		BindingFactory bf = new DefaultBindingFactory();
		bf.setDocument(document);
		Binding bind = bf.createBinding(mm1, "okEnabled", xc, "disabled");
        mm1.setOkEnabled(false);
        System.out.println(mm1.isOkEnabled());
		System.out.println(xc.getAttributeValue("disabled"));
	}
	
	// this name should match event handler aliases in the overlay.xul file

	@Override
	public String getName() {
		return "myMenu";
	}
	
	public void onLoad(){
		
	}

	public void showLogin() {
		if (lui == null){
			lui = new LoginUI(document);
			test();
		}
		if(!lui.isVisible())
		lui = new LoginUI(document);
	}

	public void showOption() {
		OptionUI oui = new OptionUI();
		oui.createUI();
	}

	public void showLoginAlert() {
		new Unlogin();
	}

}
