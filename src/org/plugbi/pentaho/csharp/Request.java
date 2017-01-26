package org.plugbi.pentaho.csharp;
/**
 * This a simple request class, with some methods to create url.
 * @author Adrien Blanes(conversion)
 * @version 0.5
 * @see Requests
 *
 */
public class Request {

	public final String EQUAL = "=";
	public final String AND = "&";
	public final String NAME_ACTION = "act";
	public final String NAME_MODEL = "mdl";
	public final String NOT_SUPPORTED = "not_supported";

	//
	public String NotSupported() {
		return NOT_SUPPORTED;
	}

	public String MakeParam(String name, String value) {
		return name + EQUAL + value;
	}

	public String MakeAct(String actName) {
		return NAME_ACTION + EQUAL + actName;
	}

	public String MakeModel(String modelName) {
		return NAME_MODEL + EQUAL + modelName;
	}

}
