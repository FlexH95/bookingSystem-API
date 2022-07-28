package com.exam.bookingSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.bookingSystem.model.LectApplyList;
import com.exam.bookingSystem.model.LectList;
import com.exam.bookingSystem.service.LectTableService;

@RestController
@RequestMapping("/booking/lect")
public class bookingController {

	@Autowired
	private LectTableService sqlService;

	@GetMapping(value = "/all")
	public List<LectList> getLectAll() throws Exception {
		List<LectList> list = sqlService.getLectAll();
		return list;
	}

	@PostMapping(value = "/new")
	public List<LectList> insertMember(@RequestBody LectList list) throws Exception {
		sqlService.insertLect(list);
		return sqlService.getLectAll();
	}

	@GetMapping(value = "/emp/all")
	public List<LectApplyList> getLectEmpList() throws Exception {
		List<LectApplyList> list = sqlService.getLectEmpList();
		return list;
	}

	@GetMapping(value = "/show")
	public List<LectList> getLectBefore7days() throws Exception {
		List<LectList> list = sqlService.getLectBefore7days();
		return list;
	}

	@PostMapping(value = "/emp/new")
	public List<LectApplyList> insertMember(@RequestBody LectApplyList list) throws Exception {
		sqlService.insertLectEmp(list);
		return sqlService.getLectEmpList();
	}

	@GetMapping(value = "/emp/{empNo}")
	public List<LectApplyList> getLectListByEmp(@PathVariable String empNo) throws Exception {
		List<LectApplyList> list = sqlService.getLectListByEmp(empNo);
		return list;
	}

	@DeleteMapping(value = "/emp/del")
	public List<LectApplyList> deleteLectEmp(@RequestBody LectApplyList list) throws Exception {
		sqlService.deleteLectEmp(list);
		return sqlService.getLectEmpList();
	}

	@GetMapping(value = "/rank")
	public List<LectApplyList> getLectRank() throws Exception {
		List<LectApplyList> list = sqlService.getLectRank();
		return list;
	}

}
