package com.home.allpet.api.mapper;

import java.util.ArrayList;
import java.util.HashMap;

public interface HomeMapper {

	public HashMap<String, String> getSidbyDomain(String domain);
	public HashMap<String, String> getHomeInfo(String ssid);
	public HashMap<String, String> getAddressInfo(String ssid);
	public ArrayList<HashMap<String, String>> getInfo(HashMap<String, String> params);
	public ArrayList<HashMap<String, String>> getWorkingTime(String ssid);
	public ArrayList<HashMap<String, String>> getImageByKey(HashMap<String, String> params);
}
