package org.test.pentaho;

import org.pentaho.ui.xul.XulException;
import org.pentaho.ui.xul.components.XulButton;
import org.pentaho.ui.xul.containers.XulVbox;
import org.pentaho.ui.xul.impl.AbstractXulEventHandler;
import org.test.pentaho.loggin.LoginUI;
import org.test.pentaho.option.OptionUI;

public class HelloWorldPerspectiveHandler extends AbstractXulEventHandler {

	public HelloWorldPerspectiveHandler() {
	}

	public String getName() {
		return "myMenu"; // this name should match event handler aliases in the
							// XUL source file
	}

	public void showLogin() {
		LoginUI lui = new LoginUI();
		lui.createUI();
	}
	
	public void showOption(){
		OptionUI oui = new OptionUI();
		oui.createUI();
	}

	public void nothin() {
		XulVbox panel = (XulVbox) document.getElementById("targetPanel");
		XulButton newButton;
		try {
			newButton = (XulButton) document.createElement("button");
			newButton.setLabel("Test");
			newButton.setOnclick("handler.doSomething()");
			panel.addChild(newButton);
		} catch (XulException e) {
			e.printStackTrace();
		}

	}
}
