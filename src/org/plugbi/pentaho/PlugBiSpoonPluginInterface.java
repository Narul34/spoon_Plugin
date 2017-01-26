/*
 * From this class start the configuration of the PlugBI plug-in, linked to the overlay xul type file,
 * managed by the handler.
 */

package org.plugbi.pentaho;

import org.pentaho.di.ui.spoon.SpoonLifecycleListener;
import org.pentaho.di.ui.spoon.SpoonPerspective;
import org.pentaho.di.ui.spoon.SpoonPlugin;
import org.pentaho.di.ui.spoon.SpoonPluginCategories;
import org.pentaho.di.ui.spoon.SpoonPluginInterface;
import org.pentaho.ui.xul.XulDomContainer;
import org.pentaho.ui.xul.XulException;

/**
 * Configure the plug-in and indicate the XUL file with which is related, and add an handler.
 * 
 * @author Adrien Blanes
 * @since 01/2017
 * @version 0.5
 *
 */

@SpoonPlugin(id = "myPlugIn", image = "")
@SpoonPluginCategories({ "spoon" })
public class PlugBiSpoonPluginInterface implements SpoonPluginInterface {
	
	private String overlayName = "org/plugbi/pentaho/xul/overlay.xul";

	/**
	 * A empty default constructor.
	 */
	public PlugBiSpoonPluginInterface() {

	}

	/**
	 * This method is called while the Spoon.bat is loading, and integrate the plug-in to the interface.
	 * @param category 
	 * 			Should be supposed equals to "spoon" : when loading, Spoon seek where to apply plug-ins;
	 *  To apply it where you wish to, you have to put "spoon" as the condition parameter (see http://wiki.pentaho.com/display/EAI/PDI+Spoon+Plugin+Development)
	 */
	@Override
	public void applyToContainer(String category, XulDomContainer container)
			throws XulException {
		if (category.equals("spoon")) {
			container.registerClassLoader(getClass().getClassLoader());
			container.loadOverlay(overlayName);
			container.addEventHandler(new PlugBiPerspectiveHandler());
		}
	}

	/**
	 * This method can prepare actions during life cycle of Spoon (at start, at end...)
	 */
	@Override
	public SpoonLifecycleListener getLifecycleListener() {
		return null;
	}

	/**
	 * This feature allow to create your own perspective on Spoon (selectable on the drop down menu, upper right corner)
	 */
	@Override
	public SpoonPerspective getPerspective() {
		return PlugBiSwtPerspective.getInstance();
	}

}