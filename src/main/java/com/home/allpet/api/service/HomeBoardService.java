package com.home.allpet.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.allpet.api.mapper.HomeBoardMapper;
import com.home.allpet.api.model.BoardVo;

@Service
@Transactional
public class HomeBoardService {

	@Autowired
	HomeBoardMapper homeBoardMapper;
	
	public Map<String, Object> insertBoard(HashMap<String, Object> parameter){
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
		
		if( "edit".equalsIgnoreCase(viewType)){
			Object tempBoardSeq = parameter.get("boardSeq");
			Long boardSeq = 0L;
			if( tempBoardSeq instanceof Long){
				boardSeq = (Long)tempBoardSeq;
			}else{
				boardSeq = Long.parseLong(tempBoardSeq.toString());
			}
			boardVo.setBoardSeq(boardSeq);
			homeBoardMapper.updateBoard(boardVo);	
		}else{
			homeBoardMapper.insertBoard(boardVo);	
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
    	
		List<BoardVo> boardList = homeBoardMapper.getBoardList(param);
		int total = homeBoardMapper.getBoardListTotalCnt(param);
		
		param.put("total", total);
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
		
		homeBoardMapper.increaseRCount(boardSeq);
		BoardVo data = homeBoardMapper.getBoardByBoardSeq(boardSeq);
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
		
		homeBoardMapper.deleteBoard(boardSeq);
	}
	
}
