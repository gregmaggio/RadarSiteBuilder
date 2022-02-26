/**
 * 
 */
package ca.datamagic.noaa.radar.dto;

/**
 * @author Greg
 *
 */
public class RadarImageDTO {
	private String crs = null;
	private double[] lowerCorner = null;
	private double[] upperCorner = null;
	private Integer width = null;
	private Integer height = null;
	
	public String getCRS() {
		return this.crs;
	}
	public void setCRS(String newVal) {
		this.crs = newVal;
	}
	public double[] getLowerCorner() {
		return this.lowerCorner;
	}
	public void setLowerCorner(double[] newVal) {
		this.lowerCorner = newVal;
	}
	public double[] getUpperCorner() {
		return this.upperCorner;
	}
	public void setUpperCorner(double[] newVal) {
		this.upperCorner = newVal;
	}
	public Integer getWidth() {
		return this.width;
	}
	public void setWidth(Integer newVal) {
		this.width = newVal;
	}
	public Integer getHeight() {
		return this.height;
	}
	public void setHeight(Integer newVal) {
		this.height = newVal;
	}
}
