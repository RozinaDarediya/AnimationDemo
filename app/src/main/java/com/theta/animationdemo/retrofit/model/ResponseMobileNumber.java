package com.theta.animationdemo.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class ResponseMobileNumber{

	@SerializedName("totalSwap")
	private int totalSwap;

	@SerializedName("firstTime")
	private boolean firstTime;

	@SerializedName("firstName")
	private String firstName;

	@SerializedName("lastName")
	private String lastName;

	@SerializedName("s3link")
	private String s3link;

	@SerializedName("gender")
	private Object gender;

	@SerializedName("countryCode")
	private String countryCode;

	@SerializedName("usedSwap")
	private int usedSwap;

	@SerializedName("id")
	private int id;

	@SerializedName("mobileNo")
	private String mobileNo;

	@SerializedName("email")
	private String email;

	@SerializedName("status")
	private boolean status;

	public void setTotalSwap(int totalSwap){
		this.totalSwap = totalSwap;
	}

	public int getTotalSwap(){
		return totalSwap;
	}

	public void setFirstTime(boolean firstTime){
		this.firstTime = firstTime;
	}

	public boolean isFirstTime(){
		return firstTime;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setS3link(String s3link){
		this.s3link = s3link;
	}

	public String getS3link(){
		return s3link;
	}

	public void setGender(Object gender){
		this.gender = gender;
	}

	public Object getGender(){
		return gender;
	}

	public void setCountryCode(String countryCode){
		this.countryCode = countryCode;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public void setUsedSwap(int usedSwap){
		this.usedSwap = usedSwap;
	}

	public int getUsedSwap(){
		return usedSwap;
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

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
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
			"ResponseMobileNumber{" + 
			"totalSwap = '" + totalSwap + '\'' + 
			",firstTime = '" + firstTime + '\'' + 
			",firstName = '" + firstName + '\'' + 
			",lastName = '" + lastName + '\'' + 
			",s3link = '" + s3link + '\'' + 
			",gender = '" + gender + '\'' + 
			",countryCode = '" + countryCode + '\'' + 
			",usedSwap = '" + usedSwap + '\'' + 
			",id = '" + id + '\'' + 
			",mobileNo = '" + mobileNo + '\'' + 
			",email = '" + email + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}