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
		
		homeBoardMapper.insertBoard(boardVo);
		
		return null;
	}
	
	public List<BoardVo> getBoardList(HashMap<String, Object> parameter){
		Map<String, Object> param = new HashMap<String, Object>();
		String ssid = (String)parameter.get("ssid");
		param.put("boardSsid", ssid);
		if( parameter.containsKey("boardType")){
			param.put("boardType", (String)parameter.get("boardType"));
		}
		
		if( parameter.containsKey("pageNum")){
			param.put("pageNum", parameter.get("pageNum"));
		}
		if( parameter.containsKey("pageCon")){
			param.put("pageCon", parameter.get("pageCon"));
		}
    	
		List<BoardVo> boardList = homeBoardMapper.getBoardList(param);
		return boardList;
	}
	
	public BoardVo getBoard(HashMap<String, Object> parameter){
		Map<String, Object> param = new HashMap<String, Object>();
		String ssid = (String)parameter.get("ssid");
		param.put("ssid", ssid);
		if( parameter.containsKey("boardType")){
			param.put("boardType", (String)parameter.get("boardType"));
		}
		
		if( parameter.containsKey("pageNum")){
			param.put("pageNum", parameter.get("pageNum"));
		}
		if( parameter.containsKey("pageCon")){
			param.put("pageCon", parameter.get("pageCon"));
		}
		
		List<BoardVo> boardList = homeBoardMapper.getBoardList(param);
		
		return null;
	}
	
}
