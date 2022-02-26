package ca.datamagic.noaa.radar.dto;

public class BoundsDTO {
	private LocationDTO northeast = null;
	private LocationDTO southwest = null;
	
	public LocationDTO getNortheast() {
		return this.northeast;
	}
	
	public void setNortheast(LocationDTO newVal) {
		this.northeast = newVal;
	}
	
	public LocationDTO getSouthwest() {
		return this.southwest;
	}
	
	public void setSouthwest(LocationDTO newVal) {
		this.southwest = newVal;
	}
}
