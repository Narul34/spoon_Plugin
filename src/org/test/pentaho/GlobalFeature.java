/*
 * Here lies all variables and features needed to be use in differents ways, should be implemented in future class
 * and deleted from here if exclusively used in a single one.
 */

package org.test.pentaho;

import java.awt.Dimension;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * A class where is stock global parameters or features possibly needed by severals others class (like calculated screen width/height).
 * 
 * @author Adrien Blanes
 * @since 01/2017
 * @version 0.5
 *
 */
public final class GlobalFeature {

	// All finals here are used both in LoginUI and Unlogin
	
	/**
	 * Determine the size of the screen.
	 */
	public static final Dimension SCREEN_DIMENSION = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	
	/**
	 * Determine width of the screen.
	 */
	public static final int SCREEN_WIDTH = (int) SCREEN_DIMENSION.getWidth();
	
	/**
	 * Determine height of the screen
	 * 
	 */
	public static final int SCREEN_HEIGHT = (int) SCREEN_DIMENSION.getHeight();

	/**
	 * A private constructor as we don't want to instantiate this class.
	 */
	private GlobalFeature() {

	}

	// For now used only in Connection
	
	/**
	 * 
	 * @param md5
	 * 		this parameter is supposed to be the user's password.
	 * @return String
	 * @throws UnsupportedEncodingException
	 */
	public static String convertToMd5(final String md5)
			throws UnsupportedEncodingException {
		StringBuffer sb = null;
		try {
			final MessageDigest md = MessageDigest.getInstance("MD5");
			final byte[] array = md.digest(md5.getBytes("UTF-8"));
			sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
						.substring(1, 3));
			}
			return sb.toString();
		} catch (final NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
