package com.exam.bookingSystem.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.exam.bookingSystem.service.LectTableService;

@RestController
public class bookingController {

	@Resource
	private LectTableService sqlService;

	@RequestMapping(value = "list")
	public ModelAndView AllListView(Map<String, Object> map) throws Exception {
		ModelAndView mav = new ModelAndView();

		List<Map<String, Object>> AllList = sqlService.SelectAllList();

		mav.addObject("Alllist", AllList);
		mav.setViewName("list");
		return mav;
	}

}
