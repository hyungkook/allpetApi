package com.home.allpet.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.allpet.api.mapper.HomeBoardMapper;

@Service
@Transactional
public class HomeBoardService {

	@Autowired
	HomeBoardMapper homeBoardMapper;
	
}
