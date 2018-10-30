package com.theta.animationdemo.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StoreModel {

    @SerializedName("data")
    private List<DataItemStore> data;

    public StoreModel(List<DataItemStore> data) {
        this.data = data;
    }

    public List<DataItemStore> getData() {
        return data;
    }

    public void setData(List<DataItemStore> data) {
        this.data = data;
    }

		/*@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<DataItemStore> data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("description")
	private String description;

	@SerializedName("message")
	private String message;

	public StoreModel() {
	}

	public StoreModel(int code, List<DataItemStore> data, boolean success, String description, String message) {
		this.code = code;
		this.data = data;
		this.success = success;
		this.description = description;
		this.message = message;
	}

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setData(List<DataItemStore> data){
		this.data = data;
	}

	public List<DataItemStore> getData(){
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
			"StoreModel{" + 
			"code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			",success = '" + success + '\'' + 
			",description = '" + description + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}*/
}