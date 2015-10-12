package com.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.test.service.ITestService;

@Controller
public class TestListController {
	
	@Autowired
	private ITestService testService;

	public ITestService getTestService() {
		return testService;
	}

	public void setTestService(ITestService testService) {
		this.testService = testService;
	}
	
	@RequestMapping(value = "/testlist/{param}", method = RequestMethod.GET)  
    public ModelAndView myMethod(HttpServletRequest request,HttpServletResponse response,   
            @PathVariable("param") String param, ModelMap modelMap) throws Exception {  
        modelMap.put("listparam", param);  
        
        testService.getList(param);
        
        return new ModelAndView("/login/hello", modelMap);  
    }  

}
