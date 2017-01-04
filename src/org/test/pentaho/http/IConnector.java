package org.test.pentaho.http;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public interface IConnector {

	 String UrlPortal(String param);
	
     void Load();
     void Save();
     void Refresh();
     //
     String GetResponse(String strParams);
     String PostOctetStream(String cmdparams, File file);
     long DownloadOctetStream(String cmdparams, File fileInfo, String response); // out String response in the C#
     File DownloadWebScreen(String cmdparams, String ScreenName, String response, String Extension); // out String response in the C# et String extension = xlsx
     //
     Boolean Login() throws IOException,
 	URISyntaxException;
     Boolean IsConnected();
     Boolean Logout();
     
    public String getUser();
  	public void setUser(String user);
  	public String getPassword();
  	public void setPassword(String password);
  	public String getProtocol();
  	public void setProtocol(String protocol);
  	public String getHost();
  	public void setHost(String host);
  	public String getPort();
  	public void setPort(String port);
  	public String getPortal();
  	public void setPortal(String portal);
  	public Integer getID();
  	public void setID(Integer iD);
  	public String getMd5Password() throws IOException;
  	public String getUrlPortalPbi();

}
