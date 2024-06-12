package com.quest.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quest.admin.NavigationConstants;

@Controller
@RequestMapping(NavigationConstants.BASE_PATH)
public class NavigationController {
	  @GetMapping(value = "/navigation")
	    public String getNavigationPage() {
	        return "navbar";
	    }
}
