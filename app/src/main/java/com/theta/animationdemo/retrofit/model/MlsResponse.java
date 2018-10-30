package com.theta.animationdemo.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MlsResponse{

	@SerializedName("data")
	private List<MlsDataItem> data;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("banner_image")
	private String bannerImage;

	@SerializedName("message")
	private String message;

	@SerializedName("current_page")
	private int currentPage;

	@SerializedName("token")
	private String token;

	@SerializedName("status")
	private boolean status;

	public void setData(List<MlsDataItem> data){
		this.data = data;
	}

	public List<MlsDataItem> getData(){
		return data;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public void setBannerImage(String bannerImage){
		this.bannerImage = bannerImage;
	}

	public String getBannerImage(){
		return bannerImage;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setCurrentPage(int currentPage){
		this.currentPage = currentPage;
	}

	public int getCurrentPage(){
		return currentPage;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
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
			"MlsResponse{" + 
			"data = '" + data + '\'' + 
			",total_pages = '" + totalPages + '\'' + 
			",banner_image = '" + bannerImage + '\'' + 
			",message = '" + message + '\'' + 
			",current_page = '" + currentPage + '\'' + 
			",token = '" + token + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}