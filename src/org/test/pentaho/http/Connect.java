/*
 * Class allowing to make a connection with the PlugBI server.
 * Note: The port http part isn't fonctionnal for now.
 */

package org.test.pentaho.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.test.pentaho.GlobalFeature;
import org.test.pentaho.convertedcsharp.Configs;
import org.test.pentaho.convertedcsharp.Requests;

import com.google.gson.Gson;


public class Connect {

	private static URI uri;
	private String password;
	private CloseableHttpClient httpclient;
	private CloseableHttpResponse response;

	public Connect(String portal, String portHttp, String serverName,
			String userName, String password) throws IOException,
			URISyntaxException {

		httpclient = HttpClients.createDefault();

		this.password = GlobalFeature.convertToMd5(password);
		
		if(portHttp.equals("")){
			portHttp = Configs.DEFAULT_PORT;
		}

		System.out.println(TestConnection.getInstance().MakeUrl(Configs.URL_PORTAL_PBI));
		uri = new URI(TestConnection.getInstance().MakeUrl(Configs.URL_PORTAL_PBI) + Requests.getInstance().Connection(userName, this.password));/*":" + portHttp +"/" + portal + "/pbi").setParameter("mdl", "con")
				.setParameter("act", "conusr").setParameter("usr", userName)
				.setParameter("pwd", this.password).build();*/
		
		/*uri = new URIBuilder().setScheme("http").setHost(serverName)
				.setPath(":" + portHttp +"/" + portal + "/pbi").setParameter("mdl", "blk")
				.setParameter("act", "blknames").setParameter("prlid", "2").build();*/

		HttpPost httpPost = new HttpPost(uri);

		try {
			response = httpclient.execute(httpPost);

			if (response.getStatusLine().getStatusCode() == 404) {
				JOptionPane.showMessageDialog(null,
						"Le nom du portail semble introuvable",
						"404 not found", JOptionPane.WARNING_MESSAGE);
				response.close();
				httpclient.close();
				return;
			}

			HttpEntity entity2 = response.getEntity();
			String json = EntityUtils.toString(entity2);
			System.out.println(json);
			Gson gson = new Gson();
			MyJson mj = gson.fromJson(json, MyJson.class);
			System.out.println(mj.getList().length);

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
		} finally {
			if (response != null) {
				response.close();
			}
		}

		httpclient.close();
	}

}
