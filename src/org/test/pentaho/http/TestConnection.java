package org.test.pentaho.http;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.stringtemplate.v4.ST;
import org.test.pentaho.GlobalFeature;
import org.test.pentaho.convertedcsharp.Configs;
import org.test.pentaho.convertedcsharp.Requests;

import com.google.gson.Gson;

public class TestConnection implements IConnector {

	static TestConnection _instance;
	//private static ILog log = LogManager.GetLogger(_instance.getClass().Name);

	private String _user;
	private String _password;
	private String _protocol;
	private String _host;
	private String _port;
	private String _portal;
	private Integer _id = null;
	private URI uri;
	private CloseableHttpClient httpclient;
	private CloseableHttpResponse response = null;

	public static TestConnection getInstance() {

		if (_instance == null)
			_instance = new TestConnection();
		return _instance;

	}

	public TestConnection() {
		
	}

	
	
	//////////////////////// not functionnal features for now/////////////////////	
	
	@Override
	public void Save() {
		/*
		 * For saving in an .ini file, this is the c# code
		 * ConfigManager.Ctx.Save(User: User, Password: Password, Protocol:
		 * Protocol, Host: Host, Port: Port, Portal: Portal);
		 */
	}

	@Override
	public String GetResponse(String strParams) {

		try {
			/*
			 * HttpWebRequest myWebRequest = null; myWebRequest =
			 * (HttpWebRequest)WebRequest.Create(this.UrlPortalPbi + cmdparams +
			 * Requests.PBI.AndProfileId(ID)); HttpWebResponse myWebResponse =
			 * (HttpWebResponse)myWebRequest.GetResponse(); // Stream dataStream
			 * = myWebResponse.GetResponseStream(); StreamReader reader = new
			 * StreamReader(dataStream);
			 */
			String responseFromServer = "";// = reader.ReadToEnd();
			//
			// reader.Close();
			// myWebResponse.Close();
			return responseFromServer;
		} catch (Exception e) {
			// log.Error(e);
			return null;
		}
	}

	@Override
	public String PostOctetStream(String cmdparams, File file) {
		//final String OCTET_STREAM_TYPE = "application/octet-stream";
		//final String STR_BOUNDARY = "3fbd04f5-b1ed-4060-99b9-fca7ff59c113";
		// byte[] startBytes = System.Text.Encoding.ASCII.GetBytes("\r\n--" +
		// STR_BOUNDARY + "\r\n");
		// byte[] endBytes = System.Text.Encoding.ASCII.GetBytes("\r\n--" +
		// STR_BOUNDARY + "--\r\n");
		//
		try {
			/*
			 * HttpWebRequest request =
			 * (HttpWebRequest)WebRequest.Create(this.UrlPortalPbi + cmdparams +
			 * Requests.PBI.AndProfileId(ID)); // request.ContentType =
			 * "multipart/form-data; boundary=" + STR_BOUNDARY; request.Method =
			 * "POST"; request.KeepAlive = true; request.Credentials =
			 * System.Net.CredentialCache.DefaultCredentials; // using (Stream
			 * requestStream = request.GetRequestStream()) { //
			 * requestStream.Write(startBytes, 0, startBytes.Length); string
			 * header = "Content-Disposition: form-data; name=\"uploadfile\"" +
			 * "; filename=\"" + file.Name + "\"\r\nContent-Type: " +
			 * OCTET_STREAM_TYPE + "\r\n\r\n";
			 * 
			 * byte[] bytes = System.Text.Encoding.UTF8.GetBytes(header);
			 * requestStream.Write(bytes, 0, bytes.Length); // byte[] buffer =
			 * new byte[28960]; int bytesRead; if (file.Exists) { //read byte
			 * file using (FileStream fileStream = file.OpenRead()) { while
			 * ((bytesRead = fileStream.Read(buffer, 0, buffer.Length)) != 0)
			 * requestStream.Write(buffer, 0, bytesRead); fileStream.Close(); }
			 * } requestStream.Write(endBytes, 0, endBytes.Length);
			 * requestStream.Close(); }
			 */
			// using (WebResponse response = request.GetResponse())
			// {
			// using (Stream responseStream = response.GetResponseStream())
			// using (StreamReader reader = new StreamReader(responseStream))
			return "";// reader.ReadToEnd();
			// }
		} catch (Exception e) {
			// log.Error(e);
			return null; // message error
		}
	}

	@Override
	public File DownloadWebScreen(String cmdparams, String ScreenName,
			String message, String Extension) {
		message = "";
		if (ScreenName == null)
			return null;
		//
		File fileInfo = null; // IOFile.Ctx.GetTempFile(ScreenName + Extension);
		//
		if (DownloadOctetStream(cmdparams, fileInfo, message) > 0)
			return fileInfo;
		//
		return null;
	}

	@Override
	public long DownloadOctetStream(String cmdparams, File fileInfo,
			String response) {
		/*
		 * Stream streamreader = null; message = String.Empty; try { if
		 * (fileInfo == null) return -1; //Create a WebRequest to get the file
		 * HttpWebRequest streamReq =
		 * (HttpWebRequest)HttpWebRequest.Create(this.UrlPortalPbi + cmdparams +
		 * Requests.PBI.AndProfileId(ID)); streamReq.Timeout =
		 * ConfigManager.Ctx.TimeOut; //Create a response for this request
		 * HttpWebResponse streamResp =
		 * (HttpWebResponse)streamReq.GetResponse(); // streamreader =
		 * streamResp.GetResponseStream(); FileStream outFile =
		 * fileInfo.Create(); //read stream byte[] buffer = new byte[1024]; int
		 * bytesRead; while ((bytesRead=streamreader.Read(buffer, 0,
		 * buffer.Length)) != 0) outFile.Write(buffer, 0, bytesRead); // long
		 * length = outFile.Length; outFile.Close(); return length;
		 * 
		 * }catch(Exception e){ log.Error(e); message = e.Message; return -1;
		 * 
		 * }finally { if (streamreader != null) streamreader.Close(); }
		 */
		Long lg = null;
		return lg;
	}

	//////////////////////////////////////////////////////////////////////////////

	@Override
	public String UrlPortal(String param) {
		param = "";
		return MakeUrl() + param;
	}

	@Override
	public String getUrlPortalPbi() {

		return this.MakeUrl(Configs.URL_PORTAL_PBI);
	}

	@Override
	public String getUser() {
		if (_user == null)
			_user = "";
		return _user;
	}

	@Override
	public void setUser(String user) {
		_user = user;

	}

	@Override
	public String getPassword() {
		if (_password == null)
			_password = "";
		return _password;
	}

	@Override
	public void setPassword(String password) {
		_password = password;

	}

	@Override
	public String getMd5Password() throws UnsupportedEncodingException {

		if (StringUtils.isEmpty(_password) || StringUtils.isBlank(_password))
			return "";

		return GlobalFeature.convertToMd5(_password);
	}

	@Override
	public String getProtocol() {
		if (_protocol == null)
			_protocol = Configs.DEFAULT_PROTOCOL;
		return _protocol;
	}

	@Override
	public void setProtocol(String protocol) {
		_protocol = protocol;

	}

	@Override
	public String getHost() {
		if (_host == null)
			_host = Configs.DEFAULT_HOST;

		return _host;
	}

	@Override
	public void setHost(String host) {
		_host = host;

	}

	@Override
	public String getPort() {
		if (_port == null)
			_port = Configs.DEFAULT_PORT;
		return _port;
	}

	@Override
	public void setPort(String port) {
		_port = port;

	}

	@Override
	public String getPortal() {
		if (_portal == null)
			_portal = "";
		return _portal;
	}

	@Override
	public void setPortal(String portal) {
		_portal = portal;

	}

	@Override
	public Integer getID() {

		return _id;
	}

	@Override
	public void setID(Integer iD) {
		_id = iD;

	}

	@Override
	public void Refresh() {
		this.Load();
	}

	public String MakeUrl(String tpl){
		ST template = new ST(tpl, '$', '$');
		template.add(Configs.PROTOCOL, getProtocol());
		template.add(Configs.HOST, getHost());
		template.add(Configs.PORT, getPort());
		template.add(Configs.PORTAL, getPortal());
		return template.render();
				
	}
	
	public String MakeUrl() {
		return MakeUrl(Configs.URL_PORTAL);
		
	}

	public Boolean IsConnected() {
		if (_id != null && _id >= 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void Load(){
		
	}
	
	public void Load( String portal, String port, String host,
			String user, String password) {
		
		
		setProtocol(Configs.DEFAULT_PROTOCOL);
		setHost(host);
		setPortal(portal);
		setPort(port);
		setUser(user);
		setPassword(password);
		
		 /* User = ConfigManager.Ctx.User; Password = ConfigManager.Ctx.Password;
		  Protocol = ConfigManager.Ctx.Protocol; Host = ConfigManager.Ctx.Host;
		  Port = ConfigManager.Ctx.Port; Portal = ConfigManager.Ctx.Portal;
		 */
	}
	
	@Override
	public Boolean Login() throws IOException,
	URISyntaxException {
		
		httpclient = HttpClients.createDefault();

		System.out.println(TestConnection.getInstance().MakeUrl(Configs.URL_PORTAL_PBI));
		
			
		try {
			
			uri = new URI(TestConnection.getInstance().MakeUrl(Configs.URL_PORTAL_PBI) + Requests.getInstance().Connection(getUser(), getMd5Password()));
			
			
			HttpPost httpPost = new HttpPost(uri);

			CloseableHttpResponse response = httpclient.execute(httpPost);

			if (response.getStatusLine().getStatusCode() == 404) {
				JOptionPane.showMessageDialog(null,
						"Le nom du portail semble introuvable",
						"404 not found", JOptionPane.WARNING_MESSAGE);
				response.close();
				httpclient.close();
				return false;
			}

			HttpEntity entity2 = response.getEntity();
			String json = EntityUtils.toString(entity2);
			System.out.println(json);
			Gson gson = new Gson();
			MyJson mj = gson.fromJson(json, MyJson.class);

			if (mj.getList()[0].equals("-1")) {
				JOptionPane.showMessageDialog(
								null,
								"Le nom d'utilisateur ou le mot de passe est incorrect!",
								"Authentification", JOptionPane.ERROR_MESSAGE);
			}
			
			if (!mj.getList()[0].equals("-1"))
			{
				JLabel label = new JLabel("Bienvenue");
				label.setHorizontalAlignment(JLabel.CENTER);
				JOptionPane.showMessageDialog(
						null,
						label,
						"Authentification", JOptionPane.PLAIN_MESSAGE);
			}
			

			EntityUtils.consume(entity2);
			return true;

		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null,
					"Le nom du serveur est invalide.", "Unknown host",
					JOptionPane.ERROR_MESSAGE);
		} catch (ClientProtocolException e2) {
			JOptionPane.showMessageDialog(null,
					"Veuillez entre un nom de serveur", "URI Problem",
					JOptionPane.ERROR_MESSAGE);
		} catch (HttpHostConnectException e3){
			JOptionPane.showMessageDialog(null,
					"La connexion a échoué, le temps de réponse du serveur est trop long.\nVerifiez le port.", "Connection timed out",
					JOptionPane.ERROR_MESSAGE);
		}	
		
		
		/*
		 * try { 
		 * HttpWebRequest myWebRequest = (HttpWebRequest)WebRequest.Create(this.UrlPortalPbi +
		 * Requests.PBI.Connection(User, Md5Password));
		 *  myWebRequest.Timeout = ConfigManager.Ctx.TimeOut;
		 *  //default 50 000
		 * 
		 * HttpWebResponse myWebResponse = (HttpWebResponse)myWebRequest.GetResponse(); 
		 * StreamReader reader = new StreamReader(myWebResponse.GetResponseStream()); 
		 * String jsonResponse = reader.ReadToEnd();
		 * reader.Close();
		 * myWebResponse.Close(); 
		 * // Token jtk = Json.GetRootTkn(jsonResponse);
		 * List<String> list = Json.GetStringList(jtk);
		 * 
		 * ID = list != null && list.Count > 0 ? Int32.Parse(list[0]) : -1;
		 * return ID != null && ID >= 0;
		 * 
		 * } catch (WebException e) { 
		 * //TODO #redmine : to be Completed later use NetMsg as out arg for this method 
		 * #redmine 5666 
		 * //e.Status == WebExceptionStatus.Timeout 
		 * //netMsg = e.Message + "\n[Status=" + e.Status +"]" log.Error("PBI-ERROR >> [Status= " + e.Status +"]");
		 * return false; 
		 * } catch (Exception e) {
		 *  log.Error(e); return false; 
		 *  }
		 */
		return false;
	}
	
	public Boolean Logout() {
		
		try {
		
		if (response != null) {
			
				response.close();
		}
		if (httpclient != null) {
			httpclient.close();
		}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Il semble qu'il y ai eu une erreur lors de la déconnexion",
					"client or response uncloseable", JOptionPane.INFORMATION_MESSAGE);
		}

		_id = null;
		return true;
	}

}
