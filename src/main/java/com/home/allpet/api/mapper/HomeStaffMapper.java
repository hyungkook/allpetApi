package com.home.allpet.api.mapper;

import java.util.ArrayList;
import java.util.HashMap;

public interface HomeStaffMapper {

	public ArrayList<HashMap<String, String>> getByParent(HashMap<String, String> params);
	public ArrayList<HashMap<String, String>> getStaffList(HashMap<String, String> params);
	public ArrayList<HashMap<String, String>> getStaffPastList(HashMap<String, String> params);
}
