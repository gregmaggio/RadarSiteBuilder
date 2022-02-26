/**
 * 
 */
package ca.datamagic.noaa.radar.dto;

/**
 * @author Greg
 *
 */
public class GeocodeDTO {
	private String status = null;
	private GeocodeResultDTO[] results = null;
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String newVal) {
		this.status = newVal;
	}
	
	public GeocodeResultDTO[] getResults() {
		return this.results;
	}
	
	public void setResults(GeocodeResultDTO[] newVal) {
		this.results = newVal;
	}
}
