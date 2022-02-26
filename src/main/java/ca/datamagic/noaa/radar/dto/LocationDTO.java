/**
 * 
 */
package ca.datamagic.noaa.radar.dto;

/**
 * @author Greg
 *
 */
public class LocationDTO {
	private Double lat = null;
	private Double lng = null;
	
	public Double getLatitude() {
		return this.lat;
	}
	
	public void setLatitude(Double newVal) {
		this.lat = newVal;
	}
	
	public Double getLongitude() {
		return this.lng;
	}
	
	public void setLongitude(Double newVal) {
		this.lng = newVal;
	}
}
