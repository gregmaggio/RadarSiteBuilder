/**
 * 
 */
package ca.datamagic.noaa.radar.dto;

/**
 * @author Greg
 *
 */
public class GeometryDTO {
	private BoundsDTO bounds = null;
	private LocationDTO location = null;
	private String location_type = null;
	private BoundsDTO viewport = null;
	
	public BoundsDTO getBounds() {
		return this.bounds;
	}
	
	public void setBounds(BoundsDTO newVal) {
		this.bounds = newVal;
	}
	
	public LocationDTO getLocation() {
		return this.location;
	}
	
	public void setLocation(LocationDTO newVal) {
		this.location = newVal;
	}
	
	public String getLocationType() {
		return this.location_type;
	}
	
	public void setLocationType(String newVal) {
		this.location_type = newVal;
	}
	
	public BoundsDTO getViewport() {
		return this.viewport;
	}
	
	public void setViewport(BoundsDTO newVal) {
		this.viewport = newVal;
	}
}
