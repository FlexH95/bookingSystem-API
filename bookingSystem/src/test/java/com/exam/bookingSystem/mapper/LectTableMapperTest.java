package com.exam.bookingSystem.mapper;

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
public class LectTableMapperTest {

	int iResult = 0;

	@Autowired
	LectTableMapper sqlMapper;

	@Test
	public void testGetLectAll() {
		try {

			List<LectList> List = sqlMapper.getLectAll("");
			assertNotNull(List);
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

			iResult = sqlMapper.insertLect(List);
			assertTrue(iResult == 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetLectEmpList() {
		try {

			List<LectApplyList> List = sqlMapper.getLectEmpList();
			assertNotNull(List);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetLectBefore7days() {
		try {

			List<LectList> List = sqlMapper.getLectBefore7days();
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

			int iCnt = sqlMapper.getLectEmpCnt(List);
			if (iCnt == 0) {
				iResult = sqlMapper.insertLectEmp(List);
				assertTrue(iResult == 1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetLectListByEmp() {
		try {

			List<LectApplyList> List = sqlMapper.getLectListByEmp("test");
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

			int iCnt = sqlMapper.getLectEmpCnt(List);
			if (iCnt > 0) {
				iResult = sqlMapper.deleteLectEmp(List);
				assertTrue(iResult == 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetLectRank() {
		try {

			List<LectApplyList> List = sqlMapper.getLectRank();
			assertNotNull(List);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
