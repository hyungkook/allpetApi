package com.home.allpet.api.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.allpet.api.mapper.HomeMapper;

@Service
@Transactional
public class HomeService {
	@Autowired
	HomeMapper homeMapper;
	
	public HashMap<String, String> getSidbyDomain(String domain){
		return homeMapper.getSidbyDomain(domain);
	}
	public HashMap<String, String> getHomeInfo(String ssid){
		return homeMapper.getHomeInfo(ssid);
	}
	public HashMap<String, String> getAddressInfo(String ssid){
		return homeMapper.getAddressInfo(ssid);
	}
	public ArrayList<HashMap<String, String>> getInfo(HashMap<String, String> params){
		return homeMapper.getInfo(params);
	}
	public ArrayList<HashMap<String, String>> getWorkingTime(String ssid){
		return homeMapper.getWorkingTime(ssid);
	}
	public ArrayList<HashMap<String, String>> getImageByKey(HashMap<String, String> params){
		return homeMapper.getImageByKey(params);
	}
	
}
