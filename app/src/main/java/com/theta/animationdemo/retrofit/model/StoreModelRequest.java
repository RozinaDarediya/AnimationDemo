package com.theta.animationdemo.retrofit.model;

public class StoreModelRequest{
	private int distance;
	private double latitude;
	private double longitude;

	public StoreModelRequest(int distance, double latitude, double longitude) {
		this.distance = distance;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public void setDistance(int distance){
		this.distance = distance;
	}

	public int getDistance(){
		return distance;
	}

	public void setLatitude(double latitude){
		this.latitude = latitude;
	}

	public double getLatitude(){
		return latitude;
	}

	public void setLongitude(double longitude){
		this.longitude = longitude;
	}

	public double getLongitude(){
		return longitude;
	}

	@Override
 	public String toString(){
		return 
			"StoreModelRequest{" + 
			"distance = '" + distance + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",longitude = '" + longitude + '\'' + 
			"}";
		}
}
