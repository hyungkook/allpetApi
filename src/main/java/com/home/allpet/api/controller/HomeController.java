package com.home.allpet.api.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.home.allpet.api.service.HomeService;
import com.home.allpet.base.util.Codes;

@RestController
@RequestMapping("/v1")
public class HomeController {
	
	@Autowired
	HomeService homeService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> home(HttpServletRequest request) throws Exception {
		
		String domain = request.getHeader("referer");//request.getRequestURL().toString();
		
		domain = domain.replace("http://", "");
		domain = domain.substring(0, domain.indexOf("/"));
		
		HashMap<String,String> domainMap = homeService.getSidbyDomain(domain);
		String ssid = domainMap.get("S_SID");
		String loc = domainMap.get("S_HOMEPAGE_LOC");
		
		URI uri = new URI(loc);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(uri);
		return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	}
	
	@RequestMapping(value = "/mainData", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> homeData(HttpServletRequest request) throws Exception {
		
		String domain = request.getHeader("referer");//request.getRequestURL().toString();
		
		domain = domain.replace("http://", "");
		domain = domain.substring(0, domain.indexOf("/"));
		
		HashMap<String,String> domainMap = homeService.getSidbyDomain(domain);
		String ssid = domainMap.get("S_SID");
		
		// 병원 기본 정보
		HashMap<String, String> baseInfo = homeService.getHomeInfo(ssid); 
				
		HashMap<String, String> params = new HashMap<String, String>();
		// 병원의 추가 정보를 가져옴
		params.put("ssid", ssid);
		params.put("group", Codes.STATUS_INFO_GROUP_HOSPITAL);
		params.put("lcode", Codes.STATUS_INFO_LCODE_INFO);
		ArrayList<HashMap<String, String>> infoList = homeService.getInfo(params); 
				
		// 병원 위치
		HashMap<String, String> hospitalAddress = homeService.getAddressInfo(ssid); 
		
		// 업무시간 정보
		ArrayList<HashMap<String, String>> workingTimeList = homeService.getWorkingTime(ssid); 
		
		// 병원 소개 이미지
		params.put("imageKey", Codes.IMAGE_TYPE_HOSPITAL_INTRO); 	// 병원 소개 이미지
		ArrayList<HashMap<String, String>> mainImageList = homeService.getImageByKey(params); 
		
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("hospitalInfo", infoList);
		parameter.put("baseInfo", baseInfo);
		parameter.put("hospitalAddress", hospitalAddress);
		parameter.put("workingTime", workingTimeList);
		parameter.put("mainImage", mainImageList);
		
		return parameter;
	}
}
