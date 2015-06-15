package com.home.allpet.api.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.allpet.api.mapper.HomeStaffMapper;

@Service
@Transactional
public class HomeStaffService {
	@Autowired
	HomeStaffMapper homeStaffMapper;
	
	public ArrayList<HashMap<String, String>> getByParent(HashMap<String, String> params){
		return homeStaffMapper.getByParent(params);
	}
	public ArrayList<HashMap<String, String>> getStaffList(HashMap<String, String> params){
		return homeStaffMapper.getStaffList(params);
	}
	public ArrayList<HashMap<String, String>> getStaffPastList(HashMap<String, String> params){
		return homeStaffMapper.getStaffPastList(params);
	}
	
}
