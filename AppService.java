package com.demo.hm.service;

import java.util.List;

import com.demo.hm.model.AppSort;

public interface AppService {
	
	public AppSort saveUnsortedArray(String genIdStr);
	public List<AppSort> displaySortedList();
}
