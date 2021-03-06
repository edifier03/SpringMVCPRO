package com.mvc.controller;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.ModelMap;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.servlet.ModelAndView;  

import com.mvc.service.IUserService;

@Controller
public class RestConstroller {  
	@Autowired 
	private IUserService userService;
	
	
    public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public RestConstroller() {}  
    @RequestMapping(value = "/login123/{user}", method = RequestMethod.GET)  
    public ModelAndView myMethod(HttpServletRequest request,HttpServletResponse response,   
            @PathVariable("user") String user, ModelMap modelMap) throws Exception {  
        modelMap.put("loginUser", user);  
        userService.traInsert(user);
        
        return new ModelAndView("/login/hello", modelMap);  
    }  
    @RequestMapping(value = "/welcome1", method = RequestMethod.GET)  
    public String registPost() {  
        return "/welcome";  
    }  
}  