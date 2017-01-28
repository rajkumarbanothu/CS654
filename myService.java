package com.pack;

//@WebService
public class myService {
	static int lat=20;
	static int lon=30;
	String latlon;
//	@WebMethod
	public void setLocation(int latitude,int longitude) {
		lat=latitude;
		lon=longitude;
		System.out.println("lat : "+lat+" lon : "+lon);
		
	}
//	@WebMethod
	public String getLocation() {
		latlon="lat "+lat+" lon "+lon;
		return latlon;
	}

}
