package com.exam.bookingSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exam.bookingSystem.model.LectApplyList;
import com.exam.bookingSystem.model.LectList;
import com.exam.bookingSystem.service.LectTableService;

@ComponentScan(basePackages = "com.exam.bookingSystem.service")
@RestController
@RequestMapping("/booking/lect")
public class bookingController {

	@Autowired
	private LectTableService sqlService;

	/** BackOffice Start **/
	/** 강연 목록(전체 강연 목록) **/
	@GetMapping(value = "/all")
	public List<LectList> getLectAll() throws Exception {
		List<LectList> list = sqlService.getLectAll("");
		return list;
	}

	/** 강연 등록 **/
	@PostMapping(value = "/new")
	public List<LectList> insertLect(@RequestBody LectList list) throws Exception {
		sqlService.insertLect(list);
		return sqlService.getLectAll("");
	}

	/** 강연신청자 목록 **/
	@GetMapping(value = { "/emp/list/{lectName}", "/emp/list" })
	public List<LectApplyList> getLectEmpList(@PathVariable(required = false) String lectName) throws Exception {
		List<LectApplyList> list = sqlService.getLectEmpList(lectName);
		return list;
	}

	/** BackOffice End **/

	/** Front Start **/

	/** 강연 목록(신청 가능한 싯점(강연시작시간 1주일 전 노출)부터 강연시작시간 1일 후까지 노출, Exposure) **/
	@GetMapping(value = "/expo-7")
	public List<LectList> getLectBefore7days() throws Exception {
		List<LectList> list = sqlService.getLectBefore7days();
		return list;
	}

	/** 강연 신청 **/
	@PostMapping(value = "/emp/new")
	public List<LectApplyList> insertLectEmp(@RequestBody LectApplyList list) throws Exception {
		sqlService.insertLectEmp(list);
		return sqlService.getLectEmpList("");
	}

	/** 신청 내역 조회(사번만 입력) **/
	@GetMapping(value = "/emp/{empNo}")
	public List<LectApplyList> getLectListByEmp(@PathVariable String empNo) throws Exception {
		List<LectApplyList> list = sqlService.getLectListByEmp(empNo);
		return list;
	}

	/** 신청한 강연 취소(조회한 신청한 강연 정보 취소) **/
	@DeleteMapping(value = "/emp/{lect}")
	public List<LectApplyList> deleteLectEmp(@PathVariable(name = "lect") String lectName, @RequestParam String empNo)
			throws Exception {
		LectApplyList list = new LectApplyList();
		list.setLecturerName(lectName);
		list.setEmpNo(empNo);

		sqlService.deleteLectEmp(list);
		return sqlService.getLectEmpList("");
	}

	/**
	 * 실시간 인기 강연(실시간 3일간 가능 신청이 많은 강연순)
	 * http://localhost:8080/booking/lect/emp/A?empNo={}
	 **/
	@GetMapping(value = "/rank")
	public List<LectApplyList> getLectRank() throws Exception {
		List<LectApplyList> list = sqlService.getLectRank();
		return list;
	}
	/** Front End **/
}
