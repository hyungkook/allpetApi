package com.home.allpet.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.allpet.api.mapper.HomeMapper;
import com.home.allpet.base.util.Common;
import com.home.allpet.base.util.XMLParserUtil;

@Service
@Transactional
public class HomeService {
	@Autowired
	HomeMapper homeMapper;
	
	public HashMap<String, String> getSidbyDomain(String domain){
		return homeMapper.getSidbyDomain(domain);
	}
	public HashMap<String, String> getHomeInfo(String ssid){
		return homeMapper.getHomeInfo(ssid);
	}
	public HashMap<String, String> getAddressInfo(String ssid){
		return homeMapper.getAddressInfo(ssid);
	}
	public ArrayList<HashMap<String, String>> getInfo(HashMap<String, String> params){
		return homeMapper.getInfo(params);
	}
	public ArrayList<HashMap<String, String>> getWorkingTime(String ssid){
		return homeMapper.getWorkingTime(ssid);
	}
	public ArrayList<HashMap<String, String>> getImageByKey(HashMap<String, String> params){
		return homeMapper.getImageByKey(params);
	}
	
	public String getSite(HashMap<String, String> params){
		return homeMapper.getSite(params);
	}
	public List<Object> getBlogList(HashMap<String, String> params){
		
		ArrayList<HashMap<String, String>> blogList = homeMapper.getBlogRss(params);
		List<Object> returnList = null;
		if( blogList != null && !blogList.isEmpty()){
			HashMap<String, String> blogSiteMap= blogList.get(0);
			String blogSite = blogSiteMap.get("S_VALUE");
			
			if( blogSite != null ){
				// RSS 처리
				XMLParserUtil xml = new XMLParserUtil();
				
				ArrayList<String> keyTree = new ArrayList<String>();
				keyTree.add("rss");
				keyTree.add("channel");
				keyTree.add("item");
				
				ArrayList<Object> list = xml.parseByDOM("URL", blogSite, keyTree);
				
				if(list!=null&&!list.isEmpty()){
					
					if(Common.strEqual(params.get("search_type"),"subjectcontents")){
						
						xml.search(list, new String[]{"title","description"},
								params.get("search_text"), Common.toInt(params.get("pageNumber")), 10);
					}
					else{
						xml.search(list, null, null, Common.toInt(params.get("pageNumber")), 10);
					}
					params.put("totalCount", xml.getSearchTotal()+"");
					params.put("pageNumber", xml.getPageNumber()+"");
					params.put("pageCount", xml.getPageCount()+"");
					
					returnList = xml.getSearchResultList();
				}
			}
		}
		
		
		return returnList;
	}
	
}
