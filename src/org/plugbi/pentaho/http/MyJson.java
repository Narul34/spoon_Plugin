/*
 * A personnal class with variables you can find in the request response made in Connect.
 * We use it to convert the json type response into an object.
 */

package org.plugbi.pentaho.http;

import java.io.Serializable;
/**
 * Since requests to the PBI servor throw json file as string, an object is needed to retrieve and use it in code.
 * 
 * This is the normal format of a response from the servor :
 * 
 * {"list":["2"],"message":"","status":200}
 * 
 * @author Adrien Blanes
 * 
 * @see Connect
 *
 */
public class MyJson implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Object[] list;
	private String message;
	private int status;
	
	public MyJson(){
		
	}

	public Object[] getList() {
		return list;
	}

	public String getMessage() {
		return message;
	}

	public int getStatus() {
		return status;
	}

}
