package com.exam.bookingSystem.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.exam.bookingSystem.model.LectApplyList;
import com.exam.bookingSystem.model.LectList;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class LectTableServiceTest {

	int iResult = 0;

	@Autowired
	LectTableService sqlService;

	@Test
	public void testGetLectAll() {
		try {

			List<LectList> List = sqlService.getLectAll();
			assertNotNull(List.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Rollback(true)
	@Test
	public void testInsertLect() {
		try {
			iResult = 0;
			LectList List = new LectList();
			List.setLecturerName("B");
			List.setPlaceName("B_Place");
			List.setCapCnt(10);
			List.setDateTimeStamp("202208011230");
			List.setLectDesc("B Lect");

			iResult = sqlService.insertLect(List);
			assertTrue(iResult == 1);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetLectEmpList() {
		try {

			List<LectApplyList> List = sqlService.getLectEmpList();
			assertNotNull(List);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetLectBefore7days() {
		try {

			List<LectList> List = sqlService.getLectBefore7days();
			assertNotNull(List);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Rollback(true)
	@Test
	public void testInsertLectEmp() {
		try {
			iResult = 0;
			LectApplyList List = new LectApplyList();
			List.setLecturerName("B");
			List.setEmpNo("12345");

			int iCnt = sqlService.getLectEmpCnt(List);
			if (iCnt == 0) {
				iResult = sqlService.insertLectEmp(List);
				assertTrue(iResult == 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetLectListByEmp() {
		try {

			List<LectApplyList> List = sqlService.getLectListByEmp("test");
			assertNotNull(List);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Rollback(true)
	@Test
	public void testDeleteLectEmp() {
		try {
			iResult = 0;
			LectApplyList List = new LectApplyList();
			List.setLecturerName("B");
			List.setEmpNo("12345");

			int iCnt = sqlService.getLectEmpCnt(List);
			if (iCnt > 0) {
				iResult = sqlService.deleteLectEmp(List);
				assertTrue(iResult == 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetLectRank() {
		try {

			List<LectApplyList> List = sqlService.getLectRank();
			assertNotNull(List);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
