package com.home.allpet.api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.allpet.api.mapper.SvmaMapper;
import com.home.allpet.api.model.BoardVo;
import com.home.allpet.api.model.SvmaEduUserVo;

@Service
@Transactional
public class SvmaService {

	@Autowired
	SvmaMapper svmaMapper;
	
	public Map<String, Object> insertEdu(HashMap<String, Object> parameter){
		String viewType = (String)parameter.get("viewType");
		
		BoardVo boardVo = new BoardVo();
		boardVo.setTitle((String)parameter.get("title"));
		boardVo.setContent((String)parameter.get("content"));
		boardVo.setBoardSsid((String)parameter.get("ssid"));
		boardVo.setBoardType((String)parameter.get("boardType"));
		if( parameter.containsKey("username")){
			boardVo.setUsername((String)parameter.get("username"));
		}
		if( parameter.containsKey("passwd")){
			boardVo.setPasswd((String)parameter.get("passwd"));
		}
		if( parameter.containsKey("email")){
			boardVo.setEmail((String)parameter.get("email"));
		}
		if( parameter.containsKey("rcount")){
			boardVo.setRcount((int)parameter.get("rcount"));
		}
		if( parameter.containsKey("masterSeq")){
			boardVo.setMasterSeq((int)parameter.get("masterSeq"));
		}
		if( parameter.containsKey("replyNum")){
			boardVo.setReplyNum((int)parameter.get("replyNum"));
		}
		if( parameter.containsKey("step")){
			boardVo.setStep((int)parameter.get("step"));
		}
		if( parameter.containsKey("eduDate")){
			String eduDate = (String)parameter.get("eduDate");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
			
			java.util.Date utilDate = new java.util.Date();
			try {
				utilDate = dateFormat.parse(eduDate);
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				boardVo.setEduDate(sqlDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if( "edit".equalsIgnoreCase(viewType)){
			Object tempBoardSeq = parameter.get("boardSeq");
			Long boardSeq = 0L;
			if( tempBoardSeq instanceof Long){
				boardSeq = (Long)tempBoardSeq;
			}else{
				boardSeq = Long.parseLong(tempBoardSeq.toString());
			}
			boardVo.setBoardSeq(boardSeq);
			svmaMapper.updateBoard(boardVo);	
		}else{
			svmaMapper.insertBoard(boardVo);	
		}
		
		return null;
	}
	
	public Map<String, Object> getBoardList(HashMap<String, Object> parameter){
		Map<String, Object> param = new HashMap<String, Object>();
		String ssid = (String)parameter.get("ssid");
		param.put("boardSsid", ssid);
		if( parameter.containsKey("boardType")){
			param.put("boardType", (String)parameter.get("boardType"));
		}
		
		if( parameter.containsKey("pageNum")){
			int pageNum = (int)parameter.get("pageNum");
			int startRow = pageNum == 1 ? 0 : ((pageNum -1) * 10)  ;
			param.put("pageNum", startRow);
		}
		if( parameter.containsKey("pageCon")){
			param.put("pageCon", parameter.get("pageCon"));
		}
    	
		List<BoardVo> boardList = svmaMapper.getBoardList(param);
		int total = svmaMapper.getBoardListTotalCnt(param);
		
		param.put("total", total);
		param.put("list", boardList);
		
		return param;
	}
	public Map<String, Object> getEduList(HashMap<String, Object> parameter){
		Map<String, Object> param = new HashMap<String, Object>();
		String ssid = (String)parameter.get("ssid");
		param.put("boardSsid", ssid);
		if( parameter.containsKey("boardType")){
			param.put("boardType", (String)parameter.get("boardType"));
		}
		
		List<BoardVo> boardList = svmaMapper.getEduList(param);
		
		param.put("list", boardList);
		
		return param;
	}
	
	public BoardVo getBoard(HashMap<String, Object> parameter){
		Object tempBoardSeq = parameter.get("boardSeq");
		Long boardSeq = 0L;
		if( tempBoardSeq instanceof Long){
			boardSeq = (Long)tempBoardSeq;
		}else{
			boardSeq = Long.parseLong(tempBoardSeq.toString());
		}
		
		svmaMapper.increaseRCount(boardSeq);
		BoardVo data = svmaMapper.getBoardByBoardSeq(boardSeq);
		return data;
	}
	
	public void deleteBoard(HashMap<String, Object> parameter){
		Object tempBoardSeq = parameter.get("boardSeq");
		Long boardSeq = 0L;
		if( tempBoardSeq instanceof Long){
			boardSeq = (Long)tempBoardSeq;
		}else{
			boardSeq = Long.parseLong(tempBoardSeq.toString());
		}
		
		svmaMapper.deleteBoard(boardSeq);
	}
	
	
	public Map<String, Object> getEduUserList(HashMap<String, Object> parameter){
    	
		Map<String, Object> param = new HashMap<String, Object>();
		List<SvmaEduUserVo> boardList = svmaMapper.getEduUserList(parameter);
		int total = 0;
		if( boardList != null ){
			total = boardList.size();
		}
		param.put("total", total);
		param.put("list", boardList);
		
		return param;
	}
	
	public Map<String, Object> saveEduUser(HashMap<String, Object> parameter){
		String viewType = (String)parameter.get("viewType");
		Object tempBoardSeq = parameter.get("parentBoardSeq");
		Long parentBoardSeq = 0L;
		if( tempBoardSeq instanceof Long){
			parentBoardSeq = (Long)tempBoardSeq;
		}else{
			parentBoardSeq = Long.parseLong(tempBoardSeq.toString());
		}
		SvmaEduUserVo svmaEduUserVo = new SvmaEduUserVo();
		svmaEduUserVo.setParentBoardSeq(parentBoardSeq);
		svmaEduUserVo.setUserType(parameter.get("userType") != null ? (String)parameter.get("userType") : "");
		svmaEduUserVo.setUserNameKo(parameter.get("userNameKo") != null ? (String)parameter.get("userNameKo") : "");
		svmaEduUserVo.setUserNameEn(parameter.get("userNameEn") != null ? (String)parameter.get("userNameEn") : "");
		svmaEduUserVo.setUserCreditId(parameter.get("userCreditId") != null ? (String)parameter.get("userCreditId") : "");
		svmaEduUserVo.setHospitalName(parameter.get("hospitalName") != null ? (String)parameter.get("hospitalName") : "");
		svmaEduUserVo.setHospitalAddr(parameter.get("hospitalAddr") != null ? (String)parameter.get("hospitalAddr") : "");
		svmaEduUserVo.setPhoneNo(parameter.get("phoneNo") != null ? (String)parameter.get("phoneNo") : "");
		if( "edit".equalsIgnoreCase(viewType)){
			svmaMapper.updateEduUser(svmaEduUserVo);	
		}else{
			svmaMapper.insertEduUser(svmaEduUserVo);	
		}
		
		return null;
	}
	
	public Map<String, Object> svmaUserCheck(HashMap<String, Object> parameter){
		Map<String, Object> param = new HashMap<String, Object>();
		boolean isLogin = false;
		
		SvmaEduUserVo userVo = new SvmaEduUserVo();
		userVo.setUserCreditId((String)parameter.get("userCreditId"));
		userVo.setUserNameKo((String)parameter.get("userNameKo"));
		
		SvmaEduUserVo temp = svmaMapper.svmaLoginCheck(userVo);
		if( temp != null && !"".equalsIgnoreCase(temp.getUserCreditId()) ){
			isLogin = true;
		}
		param.put("isLogin", isLogin);
		param.put("user", temp);
		return param;
	}
	public Map<String, Object> svmaUserList(HashMap<String, Object> parameter){
		int current = (Integer)parameter.get("current");
		int rowCount = (Integer)parameter.get("rowCount");
		String limit =  (current - 1) * rowCount + "," + rowCount;
		parameter.put("limit", limit);
		
		Map<String, Object> param = new HashMap<String, Object>();
		List<SvmaEduUserVo> userList = svmaMapper.svmaUserList(parameter);
		int total = svmaMapper.svmaUserListCnt(parameter);
		param.put("current", parameter.get("current"));
		param.put("rowCount", parameter.get("rowCount"));
		param.put("rows", userList);
		param.put("total", total);
		return param;
	}
	public Map<String, Object> svmaUserAdd(HashMap<String, Object> parameter){
		Map<String, Object> param = new HashMap<String, Object>();
		SvmaEduUserVo userVo = new SvmaEduUserVo();
		userVo.setUserCreditId((String)parameter.get("userCreditId"));
		userVo.setUserNameKo((String)parameter.get("userNameKo"));
		boolean returnFlag = false;
		try{
			svmaMapper.insertSvmaUser(userVo);
			returnFlag = true;
		}catch(Exception e){
			returnFlag = false;
		}
		param.put("result", returnFlag);
		return param;
	}
	public Map<String, Object> svmaUserDel(HashMap<String, Object> parameter){
		
		Map<String, Object> param = new HashMap<String, Object>();
		SvmaEduUserVo userVo = new SvmaEduUserVo();
		userVo.setUserCreditId((String)parameter.get("userCreditId"));
		userVo.setUserNameKo((String)parameter.get("userNameKo"));
		boolean returnFlag = false;
		try{
			svmaMapper.deleteSvmaUser(userVo);
			returnFlag = true;
		}catch(Exception e){
			returnFlag = false;
		}
		param.put("result", returnFlag);
		return param;
	}
}
