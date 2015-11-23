package com.example.showphotodemo.net;

public interface PostRequestCallBack {
	public void startPost();
	public void endPost();
	public void success(String respons);
	public void localFaild(int errorCode);
	public void serviceFaild(int errorCode, String respons);
}
