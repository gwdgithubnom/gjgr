/**
 * 
 */
package com.kindlebird.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author gwd
 *
 */
@Controller
@RequestMapping("/index")
public class IndexController {

	/**
	 * 
	 */
	public IndexController() {
		// TODO Auto-generated constructor stub
		/*
		 * @Autowired
		    private Service service;
		    public ModelAndView login(HttpServletRequest request, JSONObject jsonObject){
		        ModelAndView modelAndView=new ModelAndView("/fail");
		        String username=(String)jsonObject.get("username");
		        String password=(String)jsonObject.get("password");
		        if(username==null||password==null){
		            username=request.getParameter("username");
		            password=request.getParameter("password");
		        }
		        if(username!=null&&password!=null){
		            if(userService.login(username,password))
		              modelAndView.setViewName("/sucess");
		            return modelAndView;
		        }
		        return modelAndView;
		    }
		 */
	}

}
