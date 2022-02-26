/**
 * 
 */
package ca.datamagic.noaa.radar.dao;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

import ca.datamagic.noaa.radar.dto.GeocodeDTO;
import ca.datamagic.util.IOUtils;

/**
 * @author Greg
 *
 */
public class GeocodeDAO {
	private static final String url = "https://maps.googleapis.com/maps/api/geocode/json";
	private static String apiKey = null;
	
	public static String getAPIKey() {
		return apiKey;
	}
	
	public static void setAPIKey(String newVal) {
		apiKey = newVal;
	}
	
	public GeocodeDTO geocode(String address) throws IOException {
		HttpsURLConnection connection = null;
		InputStream inputStream = null;
		try {
			String urlSpec = MessageFormat.format("{0}?address={1}&key={2}", url, URLEncoder.encode(address, "UTF-8"), apiKey);
			URL url = new URL(urlSpec);
			connection = (HttpsURLConnection)url.openConnection();
	        connection.setDoInput(true);
	        connection.setDoOutput(false);
	        connection.setRequestMethod("GET");
	        connection.setConnectTimeout(2000);
	        connection.connect();
	        inputStream = connection.getInputStream();
	        String responseText = IOUtils.readEntireStream(inputStream);
	        Gson gson = new Gson();
			return gson.fromJson(responseText, GeocodeDTO.class);
		} finally {
			if (inputStream != null) {
				IOUtils.closeQuietly(inputStream);
			}
			if (connection != null) {
				IOUtils.closeQuietly(connection);
			}
		}
	}
}
