package com.theta.animationdemo.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class UpdateProfile{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private UpdateProfileData data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("description")
	private String description;

	@SerializedName("message")
	private String message;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setData(UpdateProfileData data){
		this.data = data;
	}

	public UpdateProfileData getData(){
		return data;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"UpdateProfile{" + 
			"code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			",success = '" + success + '\'' + 
			",description = '" + description + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}