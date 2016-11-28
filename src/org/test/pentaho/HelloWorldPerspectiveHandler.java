package org.test.pentaho;

//import org.pentaho.ui.xul.XulDomContainer;
import org.pentaho.ui.xul.impl.AbstractXulEventHandler;
import org.test.pentaho.loggin.LoginUI;

public class HelloWorldPerspectiveHandler extends AbstractXulEventHandler {
	
	
	public HelloWorldPerspectiveHandler(){ 
	}
	
	public String getName() {
		return "myMenu";  //this name should match event handler aliases in the XUL source file
	}

	public void show(){
       LoginUI login = new LoginUI(); 
       login.createUI();
    }
}
