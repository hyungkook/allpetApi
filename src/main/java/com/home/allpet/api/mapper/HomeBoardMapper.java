package com.home.allpet.api.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.home.allpet.api.model.BoardVo;

public interface HomeBoardMapper {

	public void insertBoard(BoardVo boardVo);
	public List<BoardVo> getBoardList(Map<String, Object> param);
}
