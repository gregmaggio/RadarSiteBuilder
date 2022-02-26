/**
 * 
 */
package ca.datamagic.noaa.radar.dto;

/**
 * @author Greg
 *
 */
public class GeocodeResultDTO {
	private String place_id = null;
	private String[] types = null;
	private AddressComponentDTO[] address_components = null;
	private String formatted_address = null;
	private GeometryDTO geometry = null;
	
	public String getPlaceId() {
		return this.place_id;
	}
	
	public void setPlaceId(String newVal) {
		this.place_id = newVal;
	}
	
	public String[] getTypes() {
		return this.types;
	}
	
	public void setTypes(String[] newVal) {
		this.types = newVal;
	}
	
	public AddressComponentDTO[] getAddressComponents() {
		return this.address_components;
	}
	
	public void setAddressComponents(AddressComponentDTO[] newVal) {
		this.address_components = newVal;
	}
	
	public String getFormattedAddress() {
		return this.formatted_address;
	}
	
	public void setFormattedAddress(String newVal) {
		this.formatted_address = newVal;
	}
	
	public GeometryDTO getGeometry() {
		return geometry;
	}
	
	public void setGeometry(GeometryDTO newVal) {
		geometry = newVal;
	}
}
