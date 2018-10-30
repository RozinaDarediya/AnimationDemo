package com.theta.animationdemo.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class DataItemStore{

	@SerializedName("address")
	private String address;

	@SerializedName("code")
	private String code;

	@SerializedName("latitude")
	private double latitude;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("mobileNo")
	private String mobileNo;

	@SerializedName("longitude")
	private double longitude;

	@SerializedName("status")
	private boolean status;

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setLatitude(double latitude){
		this.latitude = latitude;
	}

	public double getLatitude(){
		return latitude;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setMobileNo(String mobileNo){
		this.mobileNo = mobileNo;
	}

	public String getMobileNo(){
		return mobileNo;
	}

	public void setLongitude(double longitude){
		this.longitude = longitude;
	}

	public double getLongitude(){
		return longitude;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"DataItemStore{" + 
			"address = '" + address + '\'' + 
			",code = '" + code + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",mobileNo = '" + mobileNo + '\'' + 
			",longitude = '" + longitude + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}