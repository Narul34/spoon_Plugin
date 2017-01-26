package org.plugbi.pentaho.csharp;

// This class is the replica of the original C# one (from Ms Excel plug-in), except for DEFAULT_HOST below...
/**
 * This class contains many different final strings to use for http connection.
 * 
 * @author Adrien Blanes(conversion)
 * @version 0.5
 * 
 */
public final class Configs {
	
	private Configs(){
		
	}
	
	//Env
    public static final String ENV_LOG_DIR = "ENV_LOG_DIR";

    //User dir
    public static final String XL_EXTENSION = ".xlsx";
    public static final String USER_DIRECTORY = "Plugbi\\Plugbi350";
    public static final String IMG_DIRECTORY = "images";
    public static final String TEMP_DIRECTORY = "temp";
    
    //
    public static final String FILE_HTTP_USER = "http.user";
    public static final String SECTION_OPTION = "OPTION";
    public static final String LANGUAGES = "LANGUAGES";
    public static final String USER_LANGUAGE = "USERLANGUAGE";
    public static final String PBI_ZIP = "PBIZIP";
    public static final String TIMEOUT = "TIMEOUT";
    public static final String VERSION = "VERSION";
    public static final String BUILD = "BUILD";

    public static final String SECTION_RESOURCE = "RESOURCE";
    public static final String XLA = "XLA";
    
    //
    public static final String SECTION_NAME = "CONFIG";
    public static final String USER = "USER";
    public static final String PASSWORD = "PASSWORD";
    public static final String PROTOCOL = "PROTOCOL";
    public static final String HOST = "HOST";
    public static final String PORT = "PORT";
    public static final String PORTAL = "PORTAL";
    public static final String URL_PORTAL_PBI = "$PROTOCOL$://$HOST$:$PORT$/$PORTAL$/pbi?";
    public static final String URL_PORTAL = "$PROTOCOL$://$HOST$:$PORT$/$PORTAL$";
    
    //default value
    public static final int DEFAULT_TIMEOUT = 50000; //ms
    public static final String DEFAULT_PROTOCOL = "http";
    public static final String DEFAULT_PORT = "80";    
    //TODO this was change to test, should probably be readjust as "localhost" afterward.
    public static final String DEFAULT_HOST = "ns211649.ovh.net"; 

    /*
     * Not used for now...
     * 
     * Default language
     * public static Language DEFAULT_LANGUAGE = new Language("en-US");
     * public static Language LANGUAGE_FR = new Language("fr-FR");
     */

    //TODO remove value later to be in resource file
    public static final String VERSION_NUM = "350.1";
    public static final String BUILD_NUM = "19052016";


}
