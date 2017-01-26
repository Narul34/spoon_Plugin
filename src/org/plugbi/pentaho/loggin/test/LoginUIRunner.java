package org.plugbi.pentaho.loggin.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class LoginUIRunner {
	
	public static void main(String[] args){
		
		Result result = JUnitCore.runClasses(LoginUITest.class);
		
		for(Failure failure : result.getFailures()){
			System.out.println(failure.toString());
		}
		
		System.out.println("Resultat des test : " + result.wasSuccessful() );
	}

}
