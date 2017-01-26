package org.plugbi.pentaho.http;

// The class where servor connection is managed, descended from the C# plug-in.
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
import org.pentaho.di.i18n.BaseMessages;
import org.plugbi.pentaho.Globals;
import org.plugbi.pentaho.csharp.Configs;
import org.plugbi.pentaho.csharp.Requests;
import org.stringtemplate.v4.ST;

import com.google.gson.Gson;

/**
 * This class contains all the methods to connect to the PBI servor.
 * 
 * @author Adrien Blanes
 * 
 * @see IConnector, MyJson
 *
 */
public class Connect implements IConnector {

	static Connect _instance;
	// private static ILog log =
	// LogManager.GetLogger(_instance.getClass().Name);
	private static Class<?> PKG = Connect.class; // for i18n purposes

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

	/**
	 * 
	 * @return the only instance of the object as in singleton pattern.
	 */
	public static Connect getInstance() {

		if (_instance == null)
			_instance = new Connect();
		return _instance;

	}

	/**
	 * The constructor of the class. We only need the methods in the class so
	 * nothing happenned in it.
	 */
	public Connect() {

	}

	// ////////////////////// not functionnal features for
	// now/////////////////////
	// //////////////////////// must be implemented however
	// ///////////////////////

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
		// final String OCTET_STREAM_TYPE = "application/octet-stream";
		// final String STR_BOUNDARY = "3fbd04f5-b1ed-4060-99b9-fca7ff59c113";
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

	@SuppressWarnings("null")
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

	// ////////////////////////////////////////////////////////////////////////////
	/**
	 * @return the url of the portal
	 */
	@Override
	public String UrlPortal(String param) {
		param = "";
		return MakeUrl() + param;
	}

	/**
	 * @return the url of the portal then a "pbi?" parameter
	 */
	@Override
	public String getUrlPortalPbi() {

		return this.MakeUrl(Configs.URL_PORTAL_PBI);
	}

	/**
	 * @return the user name
	 */
	@Override
	public String getUser() {
		if (_user == null)
			_user = "";
		return _user;
	}

	/**
	 * Define the user name.
	 */
	@Override
	public void setUser(String user) {
		_user = user;

	}

	/**
	 * @return the user's password.
	 */
	@Override
	public String getPassword() {
		if (_password == null)
			_password = "";
		return _password;
	}

	/**
	 * Define the user's password.
	 */
	@Override
	public void setPassword(String password) {
		_password = password;

	}

	/**
	 * @return If not null, the MD5 crypted password.
	 */
	@Override
	public String getMd5Password() throws UnsupportedEncodingException {

		if (StringUtils.isEmpty(_password) || StringUtils.isBlank(_password))
			return "";

		return Globals.convertToMd5(_password);
	}

	/**
	 * @return the protocol.
	 */
	@Override
	public String getProtocol() {
		if (_protocol == null)
			_protocol = Configs.DEFAULT_PROTOCOL;
		return _protocol;
	}

	/**
	 * Define the protocol.
	 */
	@Override
	public void setProtocol(String protocol) {
		_protocol = protocol;

	}

	/**
	 * @return the host.
	 */
	@Override
	public String getHost() {
		if (_host == null)
			_host = Configs.DEFAULT_HOST;

		return _host;
	}

	/**
	 * Define the host.
	 */
	@Override
	public void setHost(String host) {
		_host = host;

	}

	/**
	 * @return the port which is used.
	 */
	@Override
	public String getPort() {
		if (_port == null)
			_port = Configs.DEFAULT_PORT;
		return _port;
	}

	/**
	 * Define the port which is used.
	 */
	@Override
	public void setPort(String port) {
		_port = port;

	}

	/**
	 * @return the portal name.
	 */
	@Override
	public String getPortal() {
		if (_portal == null)
			_portal = "";
		return _portal;
	}

	/**
	 * Define the portal name.
	 */
	@Override
	public void setPortal(String portal) {
		_portal = portal;

	}

	/**
	 * @return the user id.
	 */
	@Override
	public Integer getID() {

		return _id;
	}

	/**
	 * Define the user id.
	 */
	@Override
	public void setID(Integer iD) {
		_id = iD;

	}

	/**
	 * This method should refresh the url information.
	 */
	@Override
	public void Refresh() {
		this.Load();
	}

	/**
	 * With the Configs class, this method define how is construct the url.
	 * 
	 * @param tpl
	 *            A String object which will be use to create a StringTemplate
	 *            object.
	 * @see Configs
	 * @return a String as the url.
	 */
	public String MakeUrl(String tpl) {
		ST template = new ST(tpl, '$', '$');
		template.add(Configs.PROTOCOL, getProtocol());
		template.add(Configs.HOST, getHost());
		template.add(Configs.PORT, getPort());
		template.add(Configs.PORTAL, getPortal());
		return template.render();

	}

	/**
	 * This method define a default constructed url.
	 * 
	 * @return a String as the url.
	 */
	public String MakeUrl() {
		return MakeUrl(Configs.URL_PORTAL);

	}

	/**
	 * As the _id value is equal to -1 if not connected, all others value means
	 * you are.
	 * 
	 */
	public Boolean IsConnected() {
		if (_id != null && _id >= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * A default method to load parameters for creating the url.
	 */
	@Override
	public void Load() {

	}

	/**
	 * This method set all the parameters needed to create the url.
	 * 
	 * @param portal
	 * @param port
	 * @param host
	 * @param user
	 * @param password
	 */
	public void Load(String portal, String port, String host, String user,
			String password) {

		setProtocol(Configs.DEFAULT_PROTOCOL);
		setHost(host);
		setPortal(portal);
		setPort(port);
		setUser(user);
		setPassword(password);

		/*
		 * User = ConfigManager.Ctx.User; Password = ConfigManager.Ctx.Password;
		 * Protocol = ConfigManager.Ctx.Protocol; Host = ConfigManager.Ctx.Host;
		 * Port = ConfigManager.Ctx.Port; Portal = ConfigManager.Ctx.Portal;
		 */
	}

	/**
	 * The login method, where is created the url.
	 * 
	 * @return true if connection succeeded, false if not.
	 */
	@Override
	public Boolean Login() throws IOException, URISyntaxException {

		httpclient = HttpClients.createDefault();

		try {

			uri = new URI(Connect.getInstance().MakeUrl(Configs.URL_PORTAL_PBI)
					+ Requests.getInstance().Connection(getUser(),
							getMd5Password()));

			HttpPost httpPost = new HttpPost(uri);

			CloseableHttpResponse response = httpclient.execute(httpPost);

			if (response.getStatusLine().getStatusCode() == 404) {
				JOptionPane.showMessageDialog(null, BaseMessages.getString(PKG,
						"Connect.PortalErrorDialog.Mess"), BaseMessages
						.getString(PKG, "Connect.PortalErrorDialog.Title"),
						JOptionPane.WARNING_MESSAGE);
				response.close();
				httpclient.close();
				return false;
			}

			HttpEntity entity2 = response.getEntity();
			String json = EntityUtils.toString(entity2);
			Gson gson = new Gson();
			MyJson mj = gson.fromJson(json, MyJson.class);

			if (mj.getList()[0].equals("-1")) {
				JOptionPane.showMessageDialog(null, BaseMessages.getString(PKG,
						"Connect.AuthentificationDialog.Mess1"),
						BaseMessages.getString(PKG,
								"Connect.AuthentificationDialog.Title"),
						JOptionPane.ERROR_MESSAGE);
			}

			if (!mj.getList()[0].equals("-1")) {
				JLabel label = new JLabel(BaseMessages.getString(PKG,
						"Connect.AuthentificationDialog.Mess2"));
				label.setHorizontalAlignment(JLabel.CENTER);
				JOptionPane.showMessageDialog(null, label,
						BaseMessages.getString(PKG,
								"Connect.AuthentificationDialog.Title"),
						JOptionPane.PLAIN_MESSAGE);
			}

			EntityUtils.consume(entity2);
			return true;

		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null,
					BaseMessages.getString(PKG, "Connect.URIDialog.Mess1"),
					BaseMessages.getString(PKG, "Connect.URIDialog.Title"),
					JOptionPane.ERROR_MESSAGE);
		} catch (ClientProtocolException e2) {
			JOptionPane.showMessageDialog(null,
					BaseMessages.getString(PKG, "Connect.URIDialog.Mess2"),
					BaseMessages.getString(PKG, "Connect.URIDialog.Title"),
					JOptionPane.ERROR_MESSAGE);
		} catch (HttpHostConnectException e3) {
			JOptionPane.showMessageDialog(null, BaseMessages.getString(PKG,
					"Connect.ConnectionDialog.Mess"), BaseMessages.getString(
					PKG, "Connect.ConnectionDialog.Title"),
					JOptionPane.ERROR_MESSAGE);
		}

		return false;
	}

	/**
	 * the reverse Login() method to close connection.
	 */
	public Boolean Logout() {

		try {

			if (response != null) {

				response.close();
			}
			if (httpclient != null) {
				httpclient.close();
			}
			_id = null;
			return true;
		} catch (IOException e) {
			JOptionPane
					.showMessageDialog(
							null,
							"Il semble qu'il y ai eu une erreur lors de la déconnexion",
							"client or response uncloseable",
							JOptionPane.INFORMATION_MESSAGE);
		}

		return false;
	}

}
