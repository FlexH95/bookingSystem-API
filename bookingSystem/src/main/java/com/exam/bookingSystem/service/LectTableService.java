package com.exam.bookingSystem.service;

import java.util.List;

import com.exam.bookingSystem.model.LectApplyList;
import com.exam.bookingSystem.model.LectList;

public interface LectTableService {

	public List<LectList> getLectAll(String lectName) throws Exception;

	public int insertLect(LectList list) throws Exception;

	public List<LectApplyList> getLectEmpList() throws Exception;

	public List<LectList> getLectBefore7days() throws Exception;

	public int insertLectEmp(LectApplyList list) throws Exception;

	public List<LectApplyList> getLectListByEmp(String empNo) throws Exception;

	public int deleteLectEmp(LectApplyList list) throws Exception;

	public List<LectApplyList> getLectRank() throws Exception;

	public int getLectCnt(LectList list) throws Exception;

	public int getLectEmpCnt(LectApplyList list) throws Exception;
}
