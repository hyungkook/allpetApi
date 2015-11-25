package com.home.allpet.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.home.allpet.api.model.BoardVo;
import com.home.allpet.api.service.SvmaService;

@RestController
@RequestMapping("/v1/svma")
public class SvmaController {

	@Autowired
	SvmaService svmaService;
	
	/**
	 * 교육을 저장한다.
	 */
	@RequestMapping(value = "/saveEdu", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveEdu(
								@RequestParam String viewType,
								@RequestParam Long boardSeq,
								@RequestBody HashMap<String, Object> parameter) throws Exception {
		parameter.put("viewType", viewType);
		parameter.put("boardSeq", boardSeq);
		return svmaService.insertEdu(parameter);
	}
	@RequestMapping(value = "/getBoard", method = RequestMethod.GET)
	@ResponseBody
	public BoardVo getBoard(
									@RequestParam Long boardSeq,
									@RequestParam HashMap<String, Object> parameter) throws Exception {
		parameter.put("boardSeq", boardSeq);
		return svmaService.getBoard(parameter);
	}
	@RequestMapping(value = "/deleteBoard", method = RequestMethod.GET)
	@ResponseBody
	public void deleteBoard(	@RequestParam Long boardSeq,
									@RequestParam HashMap<String, Object> parameter) throws Exception {
		parameter.put("boardSeq", boardSeq);
		svmaService.deleteBoard(parameter);
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
		
		return svmaService.getBoardList(parameter);
	}
	
	@RequestMapping(value = "/eduList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> eduList(
												@RequestParam String ssid,
												@RequestParam String boardType
												) throws Exception {
		
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("ssid", ssid);
		parameter.put("boardType", boardType);
		
		return svmaService.getEduList(parameter);
	}
	
	
	/**
	 * 교육 신청 - 회원용
	 */
	@RequestMapping(value = "/eduUserList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> eduUserList(
											@RequestParam Long parentBoardSeq
											) throws Exception {

		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("parentBoardSeq", parentBoardSeq);
		
		return svmaService.getEduUserList(parameter);
	}
	/**
	 * 교육 신청 - 회원용
	 */
	@RequestMapping(value = "/saveEduUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveEduUser(
								@RequestBody HashMap<String, Object> parameter) throws Exception {
		return svmaService.saveEduUser(parameter);
	}
	
	/**
	 * 회원 로그인 체크
	 */
	@RequestMapping(value = "/userLogin", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> svmaUserCheck(
								@RequestParam String userCreditId,
								@RequestParam String userNameKo) throws Exception {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("userCreditId", userCreditId);
		parameter.put("userNameKo", userNameKo);
		return svmaService.svmaUserCheck(parameter);
	}
	
	/**
	 * 관리자 - 회원 리스트 보기
	 */
	@RequestMapping(value = "admin/userList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> svmaUserList(
											@RequestParam(value="searchPhrase", required=false) String searchPhrase,
											@RequestParam(value="current", required=false) Integer current,
									 		@RequestParam(value="rowCount", defaultValue="10") Integer rowCount
									 		) throws Exception {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("searchPhrase", searchPhrase);
		parameter.put("current", current);
		parameter.put("rowCount", rowCount);
		return svmaService.svmaUserList(parameter);
	}
	/**
	 * 관리자 - 회원 추가
	 */
	@RequestMapping(value = "admin/userAdd", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> svmaUserAdd(
									@RequestParam String userCreditId,
									@RequestParam String userNameKo) throws Exception {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("userCreditId", userCreditId);
		parameter.put("userNameKo", userNameKo);
		return svmaService.svmaUserAdd(parameter);
	}
	/**
	 * 관리자 - 회원 삭제
	 */
	@RequestMapping(value = "admin/userDel", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> svmaUserDel(
										@RequestParam (value="userCreditId", required=true) String userCreditId) throws Exception {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("userCreditId", userCreditId);
		return svmaService.svmaUserDel(parameter);
	}
	
}
