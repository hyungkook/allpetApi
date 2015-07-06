package com.home.allpet.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.home.allpet.api.service.HomeBoardService;

@RestController
@RequestMapping("/v1/board")
public class HomeBoardController {

	@Autowired
	HomeBoardService homeBoardService;
	
	@RequestMapping(value = "/saveBoard", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveBoard(HashMap<String, Object> parameter) throws Exception {
		
		return null;
	}
	
	@RequestMapping(value = "/getBoard", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBoard(HashMap<String, Object> parameter) throws Exception {
		
		return null;
	}
	
	@RequestMapping(value = "/boardList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> boardList(HashMap<String, Object> parameter) throws Exception {
		
		return null;
	}
}
