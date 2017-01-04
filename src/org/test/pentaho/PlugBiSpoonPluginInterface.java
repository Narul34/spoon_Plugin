/*
 * From this class start the configuration of the PlugBI plug-in, linked to the overlay xul type file,
 * managed by the handler.
 */

package org.test.pentaho;

//import org.eclipse.jface.window.ApplicationWindow;
import org.pentaho.di.ui.spoon.SpoonLifecycleListener;
import org.pentaho.di.ui.spoon.SpoonPerspective;
import org.pentaho.di.ui.spoon.SpoonPlugin;
import org.pentaho.di.ui.spoon.SpoonPluginCategories;
import org.pentaho.di.ui.spoon.SpoonPluginInterface;
import org.pentaho.ui.xul.XulDomContainer;
import org.pentaho.ui.xul.XulException;
//import org.pentaho.ui.xul.XulLoader;
//import org.pentaho.ui.xul.XulOverlay;
//import org.pentaho.ui.xul.jface.tags.ApplicationWindowLocal;

@SpoonPlugin(id = "myPlugIn", image = "")
@SpoonPluginCategories({ "spoon" })
public class PlugBiSpoonPluginInterface implements SpoonPluginInterface {

	public PlugBiSpoonPluginInterface() {

	}

	@Override
	public void applyToContainer(String category, XulDomContainer container)
			throws XulException {
		if (category.equals("spoon")) {
			container.registerClassLoader(getClass().getClassLoader());
			container.loadOverlay("org/test/pentaho/xul/overlay.xul");
			container.addEventHandler(new PlugBiPerspectiveHandler());
			/*
			 * XulLoader lala = container.getXulLoader();
			 * System.out.print("prout prout lalalal c'est la" + container.getDocumentRoot() + " pouet pouet" /*+ container.getDocumentRoot().getChildNodes().get(0).getElementById("myMenu").getAttributeValue("disabled")*//*);
			 * container.getDocumentRoot().getChildNodes().get(0).getElementById("myMenu").setAttribute("disabled",  "true");
			container.getDocumentRoot().getChildNodes().get(0).getElementById("tools").getElementById("capability-manager").setAttribute("disabled",  "true");
			System.out.print( container.getDocumentRoot().getElementById("spoon-menubar").getElementById("file") == null ? container.getDocumentRoot().getElementById("spoon-menubar").getElementById("file").getId() : "Is null MF"); 
			container.getDocumentRoot().getElementById("spoon-menubar").removeChild(container.getDocumentRoot().getElementById("spoon-menubar").getElementById("file"));
			 */
			
			
		}
	}

	@Override
	public SpoonLifecycleListener getLifecycleListener() {
		return null;
	}

	@Override
	public SpoonPerspective getPerspective() {
		return PlugBiSwtPerspective.getInstance();
	}

}