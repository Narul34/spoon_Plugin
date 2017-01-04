/*
 * A personnal class with variables you can find in the request response made in Connection.
 * We use it to convert the json type response into an object.
 */

package org.test.pentaho.http;

import java.io.Serializable;

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
