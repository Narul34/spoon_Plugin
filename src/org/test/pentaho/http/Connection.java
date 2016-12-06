package org.test.pentaho.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.test.pentaho.Screen;

public class Connection {

	private static URI uri;
	private String password;

	public Connection() {

	}

	public Connection(String portal, String portHttp, String serverName,
			String userName, String password) {
		CloseableHttpClient httpclient = HttpClients.createDefault();

		try {
			
			this.password = Screen.convertToMd5(password);
			
			uri = new URIBuilder().setScheme("http")
					.setHost(serverName).setPath("/" + portal +"/pbi")
					.setParameter("mdl", "con").setParameter("act", "conusr")
					.setParameter("usr", userName)
					.setParameter("pwd", this.password)
					.build();
			
		} catch (URISyntaxException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {

			HttpGet httpGet = new HttpGet(uri);
			CloseableHttpResponse response1 = httpclient.execute(httpGet);

			try {
				System.out.println(response1.getStatusLine());
				HttpEntity entity1 = response1.getEntity();
				System.out.println(EntityUtils.toString(entity1));
				// do something useful with the response body
				// and ensure it is fully consumed
				EntityUtils.consume(entity1);
			} finally {
				response1.close();
			}

			HttpPost httpPost = new HttpPost(uri);
			CloseableHttpResponse response2 = httpclient.execute(httpPost);

			try {
				System.out.println(response2.getStatusLine());
				HttpEntity entity2 = response2.getEntity();
				System.out.println(EntityUtils.toString(entity2));
				// do something useful with the response body
				// and ensure it is fully consumed
				EntityUtils.consume(entity2);
			} finally {
				response2.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
