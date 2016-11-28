package org.test.pentaho;

import org.pentaho.di.ui.spoon.SpoonLifecycleListener;
import org.pentaho.di.ui.spoon.SpoonPerspective;
import org.pentaho.di.ui.spoon.SpoonPlugin;
import org.pentaho.di.ui.spoon.SpoonPluginCategories;
import org.pentaho.di.ui.spoon.SpoonPluginInterface;
import org.pentaho.ui.xul.XulDomContainer;
import org.pentaho.ui.xul.XulException;





@SpoonPlugin(id = "myPlugIn", image = "")
@SpoonPluginCategories({ "spoon" })
public class HelloWorldSpoonPluginInterface implements SpoonPluginInterface {

	@Override
	public void applyToContainer(String category, XulDomContainer container)
			throws XulException {
		if (category.equals("spoon")) { 
			container.registerClassLoader(getClass().getClassLoader());
			container.loadOverlay("org/test/pentaho/xul/overlay.xul"); 
			container.addEventHandler(new HelloWorldPerspectiveHandler());
		}
	}
 
	@Override
	public SpoonLifecycleListener getLifecycleListener() {
		return null;
	}

	@Override
	public SpoonPerspective getPerspective() {
		return HelloWorldSwtPerspective.getInstance();
	}

	public HelloWorldSpoonPluginInterface() {
	}
	/*
	 * @Override public void onEvent(SpoonLifeCycleEvent event) { switch (event)
	 * { case MENUS_REFRESHED: break; case REPOSITORY_CHANGED: break; case
	 * REPOSITORY_CONNECTED: break; case REPOSITORY_DISCONNECTED: break; case
	 * SHUTDOWN: System.out.println("ca s'éteint toi meme tu sais"); break; case
	 * STARTUP: System.out.println("ca démarre tavu"); break; default: break; }
	 * 
	 * }
	 */
	
	
}