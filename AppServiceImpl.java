package com.demo.hm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.hm.dao.AppDao;
import com.demo.hm.model.AppSort;

@Service
public class AppServiceImpl implements AppService{
	
	
	private AppDao appDao;
	
	public void setAppDao(AppDao appDao) {
		this.appDao = appDao;
	}

	
	
	@Override
	@Transactional
	public AppSort saveUnsortedArray(String genIdStr){
		return this.appDao.saveUnsortedArray(genIdStr);
	}
	
	@Override
	@Transactional
	public List<AppSort> displaySortedList() {
		return this.appDao.displaySortedList();
	}
}
	    


