/**
 * 
 */
package ca.datamagic.noaa.radar.dto;

/**
 * @author Greg
 *
 */
public class RadarSiteDTO {
	private String nexradSystem = null;
	private String icao = null;
	private String rdaLocation = null;
	private String responsibleWFO = null;
	private String wfo = null;
	private String equipment = null;
	private Double latitude = null;
	private Double longitude = null;
	private RadarSiteInfoDTO siteInfo = null;
	
	public String getNexradSystem() {
		return this.nexradSystem;
	}
	
	public void setNexradSystem(String newVal) {
		this.nexradSystem = newVal;
	}
	
	public String getICAO() {
		return this.icao;
	}
	
	public void setICAO(String newVal) {
		this.icao = newVal;
	}
	
	public String getRDALocation() {
		return this.rdaLocation;
	}
	
	public void setRDALocation(String newVal) {
		this.rdaLocation = newVal;
	}
	
	public String getResponsibleWFO() {
		return this.responsibleWFO;
	}
	
	public void setResponsibleWFO(String newVal) {
		this.responsibleWFO = newVal;
	}
	
	public String getWFO() {
		return this.wfo;
	}
	
	public void setWFO(String newVal) {
		this.wfo = newVal;
	}
	
	public String getEquipment() {
		return this.equipment;
	}
	
	public void setEquipment(String newVal) {
		this.equipment = newVal;
	}
	
	public Double getLatitude() {
		return this.latitude;
	}
	
	public void setLatitude(Double newVal) {
		this.latitude = newVal;
	}
	
	public Double getLongitude() {
		return this.longitude;
	}
	
	public void setLongitude(Double newVal) {
		this.longitude = newVal;
	}
	
	public RadarSiteInfoDTO getSiteInfo() {
		return this.siteInfo;
	}
	
	public void setSiteInfo(RadarSiteInfoDTO newVal) {
		this.siteInfo = newVal;
	}
}
