package com.exam.bookingSystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.exam.bookingSystem.model.LectApplyList;
import com.exam.bookingSystem.model.LectList;

@Mapper
public interface LectTableMapper {

	public List<LectList> getLectAll() throws Exception;

	void insertLect(LectList list) throws Exception;

	public List<LectApplyList> getLectEmpList() throws Exception;

	public List<LectList> getLectBefore7days() throws Exception;

	void insertLectEmp(LectApplyList list) throws Exception;

	public List<LectApplyList> getLectListByEmp(String empNo) throws Exception;

	void deleteLectEmp(LectApplyList list) throws Exception;

	public List<LectApplyList> getLectRank() throws Exception;

	public int getLectEmpCnt() throws Exception;

}
