/**
 * 
 */
package ca.datamagic.noaa.radar.dao;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.io.AbstractGridFormat;
import org.geotools.gce.geotiff.GeoTiffFormat;
import org.geotools.gce.geotiff.GeoTiffReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.opengis.geometry.Envelope;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import ca.datamagic.noaa.radar.dto.RadarImageDTO;
import ca.datamagic.noaa.radar.dto.RadarSiteDTO;
import ca.datamagic.util.IOUtils;

/**
 * @author Greg
 *
 */
public class RadarSiteDAO {
	private static final Logger logger = Logger.getLogger(RadarSiteDAO.class.getName());
	private static final String RADAR_SITE_URL = "https://www.roc.noaa.gov/WSR88D/Program/SiteID/Network_Sites_iframe.asp";	
	private static final String RADAR_DATA_URL = "https://mrms.ncep.noaa.gov/data";
	private static String dataPath = "C:/Dev/Applications/RadarSiteBuilder/src/main/resources";
	
	public static String getDataPath() {
		return dataPath;
	}
	
	public static void setDataPath(String newVal) {
		dataPath = newVal;
	}
	
	private static String getTextValue(Node node) {
		if (node instanceof TextNode) {
			TextNode textNode = (TextNode)node;
			return textNode.text();
		}		
		List<Node> childNodes = node.childNodes();
		if (childNodes.size() > 0) {
			return getTextValue(childNodes.get(0));
		}
		return null;
	}
	
	public RadarSiteDTO[] loadFromWeb() throws IOException {
		List<RadarSiteDTO> sites = new ArrayList<RadarSiteDTO>();
		Document document = Jsoup.parse(new URL(RADAR_SITE_URL), 10000);
		Elements tables = document.getElementsByTag("table");
		Element table1 = tables.get(0);
		Element tBody1 = table1.child(0);
		List<Node> rows1 = tBody1.childNodes();
		for (int ii = 0; ii < rows1.size(); ii++) {
			Node row = rows1.get(ii);
			if (row.nodeName().compareToIgnoreCase("tr") == 0) {
				List<Node> childNodes = row.childNodes();
				List<Node> cells = new ArrayList<Node>();
				for (int jj = 0; jj < childNodes.size(); jj++) {
					if (childNodes.get(jj).nodeName().compareToIgnoreCase("td") == 0) {
						cells.add(childNodes.get(jj));
					}
				}
				if (cells.size() == 6) {
					Node cell1 = cells.get(0);								
					if (cell1.attr("class").compareToIgnoreCase("DATA") == 0) {
						String nexradSystem = getTextValue(cell1);
						String icao = getTextValue(cells.get(1));
						String rdaLocation = getTextValue(cells.get(2));
						String responsibleWFO = getTextValue(cells.get(3));
						String wfo = getTextValue(cells.get(4));
						String equipment = getTextValue(cells.get(5));
						RadarSiteDTO site = new RadarSiteDTO();
						site.setNexradSystem(nexradSystem);
						site.setICAO(icao);
						site.setRDALocation(rdaLocation);
						site.setResponsibleWFO(responsibleWFO);
						site.setWFO(wfo);
						site.setEquipment(equipment);
						sites.add(site);
					}
				}
			}
		}
		
		Element table2 = tables.get(1);
		Element tBody2 = table2.child(0);
		List<Node> rows2 = tBody2.childNodes();
		for (int ii = 0; ii < rows2.size(); ii++) {
			Node row = rows2.get(ii);
			if (row.nodeName().compareToIgnoreCase("tr") == 0) {
				List<Node> childNodes = row.childNodes();
				List<Node> cells = new ArrayList<Node>();
				for (int jj = 0; jj < childNodes.size(); jj++) {
					if (childNodes.get(jj).nodeName().compareToIgnoreCase("td") == 0) {
						cells.add(childNodes.get(jj));
					}
				}
				if (cells.size() == 5) {
					Node cell1 = cells.get(0);								
					if (cell1.attr("class").compareToIgnoreCase("DATA") == 0) {
						String nexradSystem = getTextValue(cell1);
						String icao = getTextValue(cells.get(1));
						String rdaLocation = getTextValue(cells.get(2));
						String responsibleWFO = getTextValue(cells.get(3));
						String wfo = getTextValue(cells.get(4));
						RadarSiteDTO site = new RadarSiteDTO();
						site.setNexradSystem(nexradSystem);
						site.setICAO(icao);
						site.setRDALocation(rdaLocation);
						site.setResponsibleWFO(responsibleWFO);
						site.setWFO(wfo);
						sites.add(site);
					}
				}
			}
		}
		
		Element table3 = tables.get(2);
		Element tBody3 = table3.child(0);
		List<Node> rows3 = tBody3.childNodes();
		for (int ii = 0; ii < rows3.size(); ii++) {
			Node row = rows3.get(ii);
			if (row.nodeName().compareToIgnoreCase("tr") == 0) {
				List<Node> childNodes = row.childNodes();
				List<Node> cells = new ArrayList<Node>();
				for (int jj = 0; jj < childNodes.size(); jj++) {
					if (childNodes.get(jj).nodeName().compareToIgnoreCase("td") == 0) {
						cells.add(childNodes.get(jj));
					}
				}
				if (cells.size() == 5) {
					Node cell1 = cells.get(0);								
					if (cell1.attr("class").compareToIgnoreCase("DATA") == 0) {
						String nexradSystem = getTextValue(cell1);
						String icao = getTextValue(cells.get(1));
						String responsibleWFO = getTextValue(cells.get(3));
						String wfo = getTextValue(cells.get(4));
						RadarSiteDTO site = new RadarSiteDTO();
						site.setNexradSystem(nexradSystem);
						site.setICAO(icao);
						site.setResponsibleWFO(responsibleWFO);
						site.setWFO(wfo);
						sites.add(site);
					}
				}
			}
		}
		
		Element table4 = tables.get(3);
		Element tBody4 = table4.child(0);
		List<Node> rows4 = tBody4.childNodes();
		String currWFO = null;
		String currResponsibleWFO = null;
		for (int ii = 0; ii < rows4.size(); ii++) {
			Node row = rows4.get(ii);
			if (row.nodeName().compareToIgnoreCase("tr") == 0) {
				List<Node> childNodes = row.childNodes();
				List<Node> cells = new ArrayList<Node>();
				for (int jj = 0; jj < childNodes.size(); jj++) {
					if (childNodes.get(jj).nodeName().compareToIgnoreCase("td") == 0) {
						cells.add(childNodes.get(jj));
					}
				}
				if (cells.size() > 0) {
					Node cell1 = cells.get(0);	
					if (cell1.attr("class").compareToIgnoreCase("DATA") == 0) {
						if (cells.size() == 5) {
							currWFO = getTextValue(cell1);
							currResponsibleWFO = getTextValue(cells.get(1));
							String icao = getTextValue(cells.get(2));
							String nexradSystem = getTextValue(cells.get(3));
							String rdaLocation = getTextValue(cells.get(4));
							RadarSiteDTO site = new RadarSiteDTO();
							site.setNexradSystem(nexradSystem);
							site.setICAO(icao);
							site.setRDALocation(rdaLocation);
							site.setResponsibleWFO(currResponsibleWFO);
							site.setWFO(currWFO);
							sites.add(site);
						} else if (cells.size() == 4) {
							String icao = getTextValue(cells.get(1));
							String nexradSystem = getTextValue(cells.get(2));
							String rdaLocation = getTextValue(cells.get(3));
							RadarSiteDTO site = new RadarSiteDTO();
							site.setNexradSystem(nexradSystem);
							site.setICAO(icao);
							site.setRDALocation(rdaLocation);
							site.setResponsibleWFO(currResponsibleWFO);
							site.setWFO(currWFO);
							sites.add(site);
						}
					}
				}
			}
		}
		RadarSiteDTO[] array = new RadarSiteDTO[sites.size()];
		return sites.toArray(array);
	}
	
	public List<String> listUrls(String icao) throws IOException {
		List<String> urls = new ArrayList<String>();
		String baseUrl = MessageFormat.format("{0}/RIDGEII/L3/{1}/CREF/", RADAR_DATA_URL, icao);
		Document document = Jsoup.parse(new URL(baseUrl), 10000);
		Elements anchors = document.getElementsByTag("a");
		if (anchors != null) {
			for (int ii = 0; ii < anchors.size(); ii++) {
				Element anchor = anchors.get(ii);
				String href = anchor.attr("href");
				if ((href != null) && (href.length() > 0)) {
					if (href.endsWith(".tif.gz")) {
						urls.add(baseUrl + href);
					}
				}
			}
		}
		return urls;
	}
	
	public RadarImageDTO readImageMetaData(String urlSpec) throws IOException {
		InputStream inputStream = null;
		GZIPInputStream gzipInputStream = null;		
		File tifFile = null;
		GeoTiffReader reader = null;
		GridCoverage2D coverage = null;
		try {
			String tempPath = MessageFormat.format("{0}/temp", dataPath);
			logger.info("tempPath: " + tempPath);
			File tempDir = new File(tempPath);
			if (!tempDir.exists()) {
				tempDir.mkdir();
			}
			String gzFile = urlSpec.substring(urlSpec.lastIndexOf("/") + 1);
			logger.info("gzFile: " + gzFile);
			String tifFileName = gzFile.replace(".gz", "");
			logger.info("tifFileName: " + tifFileName);
			tifFile = new File(tempPath + "/" + tifFileName);
			inputStream = IOUtils.getRequestStream(urlSpec);
			gzipInputStream = new GZIPInputStream(inputStream);
			byte[] inputBytes = IOUtils.readEntireByteArray(gzipInputStream);
			gzipInputStream.close();
			gzipInputStream = null;
			inputStream.close();
			inputStream = null;
			IOUtils.writeEntireByteArray(inputBytes, tifFile.getAbsolutePath());
			AbstractGridFormat format = new GeoTiffFormat();
			reader = (GeoTiffReader)format.getReader(tifFile);
			CoordinateReferenceSystem crs = reader.getCoordinateReferenceSystem();
			//System.out.println("CRS: " + crs.toWKT());
			coverage = reader.read(null);
			Envelope env = coverage.getEnvelope();
			double[] lowerCorner = env.getLowerCorner().getCoordinate();
			double[] upperCorner = env.getUpperCorner().getCoordinate();
	        RenderedImage image = coverage.getRenderedImage();
	        RadarImageDTO dto = new RadarImageDTO();
	        dto.setCRS(crs.toWKT());
	        dto.setLowerCorner(lowerCorner);
	        dto.setUpperCorner(upperCorner);
	        dto.setWidth(image.getWidth());
	        dto.setHeight(image.getHeight());
			return dto;
		} finally {
			if (coverage != null) {
				try {
					coverage.dispose(true);
				} catch (Throwable t) {
					logger.warning("Throwable: " + t.getMessage());
				}
			}
			if (reader != null) {
				try {
					reader.dispose();
				} catch (Throwable t) {
					logger.warning("Throwable: " + t.getMessage());
				}
			}
			if (gzipInputStream != null) {
				IOUtils.closeQuietly(gzipInputStream);
			}
			if (inputStream != null) {
				IOUtils.closeQuietly(inputStream);
			}
			if (tifFile != null) {
				try {
					if (tifFile.exists()) {
						tifFile.delete();
					}
				} catch (Throwable t) {
					logger.warning("Throwable: " + t.getMessage());
				}
			}
		}
	}
}
