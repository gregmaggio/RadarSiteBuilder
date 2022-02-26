/**
 * 
 */
package ca.datamagic.noaa.radar;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.List;
import java.util.Properties;

import com.google.gson.Gson;

import ca.datamagic.noaa.radar.dao.GeocodeDAO;
import ca.datamagic.noaa.radar.dao.RadarSiteDAO;
import ca.datamagic.noaa.radar.dto.GeocodeDTO;
import ca.datamagic.noaa.radar.dto.GeocodeResultDTO;
import ca.datamagic.noaa.radar.dto.GeometryDTO;
import ca.datamagic.noaa.radar.dto.LocationDTO;
import ca.datamagic.noaa.radar.dto.RadarImageDTO;
import ca.datamagic.noaa.radar.dto.RadarSiteDTO;
import ca.datamagic.noaa.radar.dto.RadarSiteInfoDTO;
import ca.datamagic.util.IOUtils;

/**
 * @author Greg
 *
 */
public class Builder {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			File securePropertiesFile = new File("secure.properties");
			if (!securePropertiesFile.exists()) {
				securePropertiesFile = new File("src/main/resources/secure.properties");
			}
			if (!securePropertiesFile.exists()) {
				System.err.println("secure.properties not found!");
				return;
			}
			String dataPath = securePropertiesFile.getParent();
			System.out.println("dataPath: " + dataPath);			
			
			Properties secureProperties = null;
			InputStream secureStream = null;
			try {
				secureStream = new FileInputStream(securePropertiesFile);
				secureProperties = new Properties();
				secureProperties.load(secureStream);				
			} finally {
				if (secureStream != null) {
					IOUtils.closeQuietly(secureStream);
				}
			}
			
			GeocodeDAO.setAPIKey(secureProperties.getProperty("google_maps_api_key"));
			GeocodeDAO geocodeDAO = new GeocodeDAO();			
			RadarSiteDAO.setDataPath(dataPath);
			RadarSiteDAO radarSiteDAO = new RadarSiteDAO();
			RadarSiteDTO[] sites = radarSiteDAO.loadFromWeb();
			for (RadarSiteDTO site : sites) {
				// Geocode the site
				String radLocation = site.getRDALocation();
				if ((radLocation != null) && (radLocation.length() > 0)) {
					GeocodeDTO geocode = geocodeDAO.geocode(radLocation);
					if (geocode != null) {
						GeocodeResultDTO[] geocodeResults = geocode.getResults();
						if ((geocodeResults != null) && (geocodeResults.length > 0)) {
							for (GeocodeResultDTO geocodeResult : geocodeResults) {
								GeometryDTO geometry = geocodeResult.getGeometry();
								if (geometry != null) {
									LocationDTO location = geometry.getLocation();
									if (location != null) {
										site.setLatitude(location.getLatitude());
										site.setLongitude(location.getLongitude());
										break;
									}
								}
							}
						}
					}
				}
				
				// Get the first image for the meta data
				List<String> urls = radarSiteDAO.listUrls(site.getICAO());
				if ((urls != null) && (urls.size() > 0)) {
					String url = urls.get(0);
					RadarImageDTO radarImage = radarSiteDAO.readImageMetaData(url);
					if (radarImage != null) {
						RadarSiteInfoDTO siteInfo = new RadarSiteInfoDTO();
						siteInfo.setCrs(radarImage.getCRS());
						siteInfo.setUpperCorner(radarImage.getUpperCorner());
						siteInfo.setLowerCorner(radarImage.getLowerCorner());
						siteInfo.setHeight(radarImage.getHeight());
						siteInfo.setWidth(radarImage.getWidth());
						site.setSiteInfo(siteInfo);
					}
				}
			}			
			Gson gson = new Gson();
			String json = gson.toJson(sites);
			IOUtils.writeEntireByteArray(json.getBytes(), MessageFormat.format("{0}/radar.json", RadarSiteDAO.getDataPath()));
		} catch (Throwable t) {
			System.out.println("Throwable: " + t.getMessage());
			t.printStackTrace();
		}
	}
}
