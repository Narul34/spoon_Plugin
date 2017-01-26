package org.plugbi.pentaho.loggin.test;

import java.awt.Dimension;

import org.junit.Test;
import org.plugbi.pentaho.loggin.LoginUI;

import static org.junit.Assert.*;

/**
 * This test is made to maintain some values for the UI size.
 * 
 * @author Adrien Blanes
 *
 * @see LoginUI
 */
public class LoginUITest {
	
	@Test
	public void testUI(){
		
		LoginUI lui = new LoginUI(null);
		
		assertEquals(lui.WIDTH, 580);
		assertEquals(lui.HEIGHT, 318);
		assertEquals(lui.HOME_BUTTON_DIM, new Dimension(40, 36));
		assertEquals(lui.PLOGIN_DIM, new Dimension(lui.WIDTH, 100));
		assertEquals(lui.TOP_BOT_DIM, new Dimension(lui.WIDTH, 45));
		
	}
	

}
