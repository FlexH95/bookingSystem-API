package com.exam.bookingSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.bookingSystem.model.LectList;
import com.exam.bookingSystem.service.LectTableService;

@RestController
public class bookingController {

	@Autowired
	private LectTableService sqlService;

	@RequestMapping(value = "/list")
	public List<LectList> main() {
		List<LectList> list = sqlService.SelectList();
		return list;
	}

}
