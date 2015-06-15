package com.home.allpet.api.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.home.allpet.api.service.HomeService;
import com.home.allpet.api.service.HomeStaffService;
import com.home.allpet.base.util.Codes;

@RestController
@RequestMapping("/v1")
public class HomeStaffController {
	
	@Autowired
	HomeStaffService homeStaffService;
	@Autowired
	HomeService homeService;

	
	@RequestMapping(value = "/staffInfo", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> staffInfo(HttpServletRequest request) throws Exception {
		
		String domain = request.getHeader("referer");//request.getRequestURL().toString();
		
		domain = domain.replace("http://", "");
		domain = domain.substring(0, domain.indexOf("/"));
		
		HashMap<String,String> domainMap = homeService.getSidbyDomain(domain);
		String ssid = domainMap.get("S_SID");
		
//		getByParent
		
		HashMap<String, String> s = new HashMap<String, String>();
		s.put("ssid", ssid);
		s.put("parent", Codes.STATUS_INFO_GROUP_STAFF);
		s.put("status", "Y");
		ArrayList<HashMap<String, String>> staffMenuList = homeStaffService.getByParent(s);
		
		
		ArrayList<HashMap<String, String>> staffList = homeStaffService.getStaffList(s);
		
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("staffMenuList", staffMenuList);
		return parameter;
	}
	
	@RequestMapping(value = "/staffDetailInfo", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> staffDetailInfo(
							HttpServletRequest request,
							@RequestParam(value = "category")	String category 
																) throws Exception {
		
		String domain = request.getHeader("referer");//request.getRequestURL().toString();
		
		domain = domain.replace("http://", "");
		domain = domain.substring(0, domain.indexOf("/"));
		
		HashMap<String,String> domainMap = homeService.getSidbyDomain(domain);
		String ssid = domainMap.get("S_SID");
		
//		getByParent
		
		HashMap<String, String> s = new HashMap<String, String>();
		s.put("ssid", ssid);
		s.put("category", category);
		s.put("state", "Y");
		
		ArrayList<HashMap<String, String>> staffList = homeStaffService.getStaffList(s);
		LinkedHashMap<String, Object> staffDetailList = new LinkedHashMap<String, Object>(); 
		for(int i = 0; i < staffList.size(); i++){
			HashMap<String, String> staff = staffList.get(i);
			String stid = staff.get("s_stid");
			s.put("stid", stid);
			ArrayList<HashMap<String, String>> staffPastList = homeStaffService.getStaffPastList(s);
			staffDetailList.put(stid, staffPastList);
		}
		
		
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("staffList", staffList);
		parameter.put("staffDetailList", staffDetailList);
		return parameter;
	}
}
