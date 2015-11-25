package com.home.allpet.api.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.home.allpet.api.model.BoardVo;
import com.home.allpet.api.model.SvmaEduUserVo;

public interface SvmaMapper {

	public void insertBoard(BoardVo boardVo);
	public void updateBoard(BoardVo boardVo);
	public List<BoardVo> getBoardList(Map<String, Object> param);
	public List<BoardVo> getEduList(Map<String, Object> param);
	public int getBoardListTotalCnt(Map<String, Object> param);
	
	public BoardVo getBoardByBoardSeq(Long boardSeq);
	public void increaseRCount(Long boardSeq);
	
	public void deleteBoard(Long boardSeq);
	
	
	
	public void updateEduUser(SvmaEduUserVo boardVo);
	public void insertEduUser(SvmaEduUserVo boardVo);
	public List<SvmaEduUserVo> getEduUserList(Map<String, Object> param);
	public int getEduUserListTotalCnt(Map<String, Object> param);
	
	public List<SvmaEduUserVo> svmaUserList(Map<String, Object> param);
	public int svmaUserListCnt(Map<String, Object> param);
	
	public SvmaEduUserVo svmaLoginCheck(SvmaEduUserVo boardVo);
	public void insertSvmaUser(SvmaEduUserVo boardVo);
//	public void updateSvmaUser(SvmaEduUserVo boardVo);
	public void deleteSvmaUser(SvmaEduUserVo boardVo);
	
}
