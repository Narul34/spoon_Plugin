
package org.test.pentaho;

import java.io.InputStream;
import java.util.Locale;

import org.eclipse.swt.SWT;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Label;
import org.pentaho.di.core.EngineMetaInterface;
import org.pentaho.di.core.gui.SpoonFactory;
import org.pentaho.di.ui.spoon.Spoon;
import org.pentaho.di.ui.spoon.SpoonPerspective;
import org.pentaho.di.ui.spoon.SpoonPerspectiveListener;
import org.pentaho.ui.xul.XulOverlay; 
import org.pentaho.ui.xul.impl.DefaultXulOverlay;
import org.pentaho.ui.xul.impl.XulEventHandler;

import java.util.Collections;
import java.util.List;

public class HelloWorldSwtPerspective implements SpoonPerspective {
	private Composite comp;
	private static HelloWorldSwtPerspective instance = new HelloWorldSwtPerspective();

	private HelloWorldSwtPerspective() {
		createUI();
	}

	private void createUI() {
		comp = new Composite(((Spoon) SpoonFactory.getInstance()).getShell(),
				SWT.BORDER);/*
		comp.setLayout(new GridLayout());
		//comp.setLayoutData(new GridData(GridData.CENTER));
	 	Label lbl = new Label(comp, SWT.CENTER);
		lbl.setLayoutData(new GridData(GridData.FILL_BOTH));
		lbl.setText("This perspective has added two menu-items to the Spoon menu-system. One is under "
				+ "Tools->\"Spoon Plugin Example\", the other is only visible when the perspective is active: "
				+ "Edit->\"Perspective Only Action\"");*/
	} 
     
	public static HelloWorldSwtPerspective getInstance() {
		return instance;
	}

	public void setActive(boolean b) {
	}

	public List<XulOverlay> getOverlays() {
		return Collections
				.singletonList((XulOverlay) new DefaultXulOverlay(
						"org/test/pentaho/xul/perspective.xul"));
	}

	public List<XulEventHandler> getEventHandlers() {
		return Collections
				.singletonList((XulEventHandler) new HelloWorldPerspectiveHandler());
	}

	public void addPerspectiveListener(
			SpoonPerspectiveListener spoonPerspectiveListener) {
	}

	public String getId() {
		return "helloWorld";
	}
	


	public InputStream getPerspectiveIcon() {
		ClassLoader loader = getClass().getClassLoader();
		return loader
				.getResourceAsStream("");
	}

	/**
	 * This perspective is not Document based, therefore there is no EngineMeta
	 * to save/open.
	 */

	public EngineMetaInterface getActiveMeta() {
		return null;
	}


	/*
	 * Whatever you pass out will be reparented. Don't construct the UI in this
	 * method as it may be called more than once.
	 */
	
	@Override
	public Composite getUI() {
		return comp;
	}
	

	@Override
	public String getDisplayName(Locale locale) {
		return "Spoon Example";
	}

}