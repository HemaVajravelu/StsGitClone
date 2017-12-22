package com.demo.hm.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.hm.model.AppSort;
import com.demo.hm.service.AppService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AppController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AppController.class);
	private AppService appService;

	@Autowired(required = true)
	@Qualifier(value = "appService")
	
	public void setAppService(AppService appService) {
		this.appService = appService;
	}
/**
 * Generated Random values hit this controller method sortAndSaveValues. 
 * Input:Example  8,4,9,1,3
 * Output:Example:Unsorted values:: 8 4 9 1 3  || Sorted values :: 1 3 4 8 9 || Number of swaps :: 7
 * 
 */ 
 
	@RequestMapping(value="/HMApp/sortNsave/{ids}", method = RequestMethod.POST,headers= {"Accept=text/xml, application/json"})
	public @ResponseBody AppSort sortAndSaveValues (@PathVariable("ids") String genIdStr){
			
	    LOG.info("Entered savegeneratedNumbers method..");  
		return appService.saveUnsortedArray(genIdStr);  
	    
	}
	
	@RequestMapping(value = "/HMApp/displaySortedLists", method = RequestMethod.GET)
	public List<AppSort> displayLists(Model model) {
		//model.addAttribute("appSort", new AppSort());
		//model.addAttribute("displayLists", this.appService.displaySortedList());
		
		return this.appService.displaySortedList();
	}
}
