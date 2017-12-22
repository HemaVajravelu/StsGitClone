package com.demo.hm.dao;

import java.util.List;

import com.demo.hm.model.AppSort;

public interface AppDao {
	
	public AppSort saveUnsortedArray(String genIdStr);
	public List<AppSort> displaySortedList();

}
