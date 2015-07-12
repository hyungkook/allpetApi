package com.home.allpet.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.home.allpet.api.model.BoardVo;
import com.home.allpet.api.service.HomeBoardService;

@RestController
@RequestMapping("/v1/board")
public class HomeBoardController {

	@Autowired
	HomeBoardService homeBoardService;
	
	@RequestMapping(value = "/saveBoard", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveBoard(
								@RequestParam String viewType,
								@RequestParam Long boardSeq,
								@RequestBody HashMap<String, Object> parameter) throws Exception {
		parameter.put("viewType", viewType);
		parameter.put("boardSeq", boardSeq);
		
		return homeBoardService.insertBoard(parameter);
	}
	
	@RequestMapping(value = "/getBoard", method = RequestMethod.GET)
	@ResponseBody
	public BoardVo getBoard(
									@RequestParam Long boardSeq,
									@RequestParam HashMap<String, Object> parameter) throws Exception {
		parameter.put("boardSeq", boardSeq);
		return homeBoardService.getBoard(parameter);
	}
	@RequestMapping(value = "/deleteBoard", method = RequestMethod.GET)
	@ResponseBody
	public void deleteBoard(	@RequestParam Long boardSeq,
									@RequestParam HashMap<String, Object> parameter) throws Exception {
		parameter.put("boardSeq", boardSeq);
		homeBoardService.deleteBoard(parameter);
	}
	
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> boardList(
											@RequestParam String ssid,
											@RequestParam String boardType,
											@RequestParam(value="pageNum", required=false) Integer pageNum,
									 		@RequestParam(value="pageCon", defaultValue="10") Integer pageCon
											) throws Exception {
		
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("ssid", ssid);
		parameter.put("boardType", boardType);
		parameter.put("pageNum", pageNum);
		parameter.put("pageCon", pageCon);
		
		return homeBoardService.getBoardList(parameter);
	}
}
