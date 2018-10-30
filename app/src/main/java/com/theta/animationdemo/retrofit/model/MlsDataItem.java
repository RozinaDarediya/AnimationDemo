package com.theta.animationdemo.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class MlsDataItem {

	@SerializedName("image")
	private String image;

	@SerializedName("color")
	private String color;

	@SerializedName("consumerId")
	private String consumerId;

	@SerializedName("withOutImage")
	private boolean withOutImage;

	@SerializedName("description")
	private String description;

	@SerializedName("mrp")
	private int mrp;

	@SerializedName("is_favourite")
	private boolean isFavourite;

	@SerializedName("productName")
	private String productName;

	@SerializedName("productCode")
	private String productCode;

	@SerializedName("brandId")
	private String brandId;

	@SerializedName("subcategoryId")
	private String subcategoryId;

	@SerializedName("id")
	private String id;

	@SerializedName("branchIdMap")
	private BranchIdMap branchIdMap;

	@SerializedName("specialPrice")
	private int specialPrice;

	@SerializedName("categoryId")
	private String categoryId;

	@SerializedName("productLineId")
	private String productLineId;

	@SerializedName("status")
	private boolean status;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setColor(String color){
		this.color = color;
	}

	public String getColor(){
		return color;
	}

	public void setConsumerId(String consumerId){
		this.consumerId = consumerId;
	}

	public String getConsumerId(){
		return consumerId;
	}

	public void setWithOutImage(boolean withOutImage){
		this.withOutImage = withOutImage;
	}

	public boolean isWithOutImage(){
		return withOutImage;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setMrp(int mrp){
		this.mrp = mrp;
	}

	public int getMrp(){
		return mrp;
	}

	public void setIsFavourite(boolean isFavourite){
		this.isFavourite = isFavourite;
	}

	public boolean isIsFavourite(){
		return isFavourite;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setProductCode(String productCode){
		this.productCode = productCode;
	}

	public String getProductCode(){
		return productCode;
	}

	public void setBrandId(String brandId){
		this.brandId = brandId;
	}

	public String getBrandId(){
		return brandId;
	}

	public void setSubcategoryId(String subcategoryId){
		this.subcategoryId = subcategoryId;
	}

	public String getSubcategoryId(){
		return subcategoryId;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setBranchIdMap(BranchIdMap branchIdMap){
		this.branchIdMap = branchIdMap;
	}

	public BranchIdMap getBranchIdMap(){
		return branchIdMap;
	}

	public void setSpecialPrice(int specialPrice){
		this.specialPrice = specialPrice;
	}

	public int getSpecialPrice(){
		return specialPrice;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getCategoryId(){
		return categoryId;
	}

	public void setProductLineId(String productLineId){
		this.productLineId = productLineId;
	}

	public String getProductLineId(){
		return productLineId;
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
			"MlsDataItem{" +
			"image = '" + image + '\'' + 
			",color = '" + color + '\'' + 
			",consumerId = '" + consumerId + '\'' + 
			",withOutImage = '" + withOutImage + '\'' + 
			",description = '" + description + '\'' + 
			",mrp = '" + mrp + '\'' + 
			",is_favourite = '" + isFavourite + '\'' + 
			",productName = '" + productName + '\'' + 
			",productCode = '" + productCode + '\'' + 
			",brandId = '" + brandId + '\'' + 
			",subcategoryId = '" + subcategoryId + '\'' + 
			",id = '" + id + '\'' + 
			",branchIdMap = '" + branchIdMap + '\'' + 
			",specialPrice = '" + specialPrice + '\'' + 
			",categoryId = '" + categoryId + '\'' + 
			",productLineId = '" + productLineId + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}