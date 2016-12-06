package org.test.pentaho;

import java.awt.Dimension;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Screen {
	
	public static final Dimension SCREEN_DIMENSION = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	public static final int SCREEN_WIDTH =  (int) SCREEN_DIMENSION.getWidth();
	public static final int SCREEN_HEIGHT = (int) SCREEN_DIMENSION.getHeight();
	
	private Screen(){
		
	}
	
	public static void setPosition(int xpos, int ypos, int width, int height){
		
		xpos = SCREEN_WIDTH / 2 - width / 2;
		ypos = SCREEN_HEIGHT / 2 - height / 2;
	}
	
	public static String convertToMd5(final String md5) throws UnsupportedEncodingException {
        StringBuffer sb = null;
        try {
            final MessageDigest md = MessageDigest.getInstance("MD5");
            final byte[] array = md.digest(md5.getBytes("UTF-8"));
            sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (final NoSuchAlgorithmException e) {
        	e.printStackTrace();
        }
        return null;
    }

}
