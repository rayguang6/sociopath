package com.sociopath.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GraphController {
	
	@GetMapping("/barChart")
	public String barChart(Model model)
	{
		Map<String, Integer> data = new LinkedHashMap<String, Integer>();
		data.put("abc", 30);
		data.put("Lei", 50);
		data.put("Suddnely", 70);
		data.put("CCF", 90);
		data.put("Thunder", 25);
		model.addAttribute("keySet", data.keySet());
		model.addAttribute("values", data.values());
		return "barChart";
		
	}
	
	@GetMapping("/pieChart")
	public String pieChart(Model model) {
		model.addAttribute("pass", 90);
		model.addAttribute("fail", 10);
		return "pieChart";
		
	}
	
	@GetMapping("/get-data")
	public ResponseEntity<Map<String, Integer>> getPieChart() {
		Map<String, Integer> graphData = new TreeMap<>();
		graphData.put("2016", 147);
		graphData.put("2017", 1256);
		graphData.put("2018", 3856);
		graphData.put("2019", 19807);
		return new ResponseEntity<>(graphData, HttpStatus.OK);
	}
	
	
	

}