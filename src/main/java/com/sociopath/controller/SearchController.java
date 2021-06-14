package com.sociopath.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sociopath.model.dto.SearchResult;
import com.sociopath.model.entity.StatusUpdate;
import com.sociopath.service.SearchService;
import com.sociopath.service.StatusUpdateService;


@Controller
public class SearchController {
	
	@Autowired
	SearchService searchService;

	@RequestMapping(value="/search", method={RequestMethod.POST, RequestMethod.GET})
	public ModelAndView search(ModelAndView modelAndView, @RequestParam("s") String text) {
		
		ArrayList<SearchResult> results = searchService.search(text);
		
		modelAndView.getModel().put("s", text);
		modelAndView.getModel().put("results", results);
		modelAndView.setViewName("result");
		
		return modelAndView;
	}
	
	
}
