package com.theta.animationdemo.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class VerifyUser{

	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("refresh_token")
	private String refreshToken;

	@SerializedName("scope")
	private String scope;

	@SerializedName("token_type")
	private String tokenType;

	@SerializedName("expires_in")
	private int expiresIn;

	@SerializedName("jti")
	private String jti;

	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
	}

	public String getAccessToken(){
		return accessToken;
	}

	public void setRefreshToken(String refreshToken){
		this.refreshToken = refreshToken;
	}

	public String getRefreshToken(){
		return refreshToken;
	}

	public void setScope(String scope){
		this.scope = scope;
	}

	public String getScope(){
		return scope;
	}

	public void setTokenType(String tokenType){
		this.tokenType = tokenType;
	}

	public String getTokenType(){
		return tokenType;
	}

	public void setExpiresIn(int expiresIn){
		this.expiresIn = expiresIn;
	}

	public int getExpiresIn(){
		return expiresIn;
	}

	public void setJti(String jti){
		this.jti = jti;
	}

	public String getJti(){
		return jti;
	}

	@Override
 	public String toString(){
		return 
			"VerifyUser{" + 
			"access_token = '" + accessToken + '\'' + 
			",refresh_token = '" + refreshToken + '\'' + 
			",scope = '" + scope + '\'' + 
			",token_type = '" + tokenType + '\'' + 
			",expires_in = '" + expiresIn + '\'' + 
			",jti = '" + jti + '\'' + 
			"}";
		}
}