/**
 * 
 */
package ca.datamagic.noaa.radar.dto;

/**
 * @author Greg
 *
 */
public class AddressComponentDTO {
	private String long_name = null;
	private String short_name = null;
	private String[] types = null;
	
	public String getLongName() {
		return this.long_name;
	}
	
	public void setLongName(String newVal) {
		this.long_name = newVal;
	}
	
	public String getShortName() {
		return this.short_name;
	}
	
	public void setShortName(String newVal) {
		this.short_name = newVal;
	}
	
	public String[] getTypes() {
		return this.types;
	}
	
	public void setTypes(String[] newVal) {
		this.types = newVal;
	}
}
