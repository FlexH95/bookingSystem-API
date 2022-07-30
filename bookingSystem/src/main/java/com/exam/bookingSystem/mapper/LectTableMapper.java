package com.exam.bookingSystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.exam.bookingSystem.model.LectApplyList;
import com.exam.bookingSystem.model.LectList;

@Mapper
public interface LectTableMapper {

	public List<LectList> getLectAll(String lectName) throws Exception;

	public int insertLect(LectList list) throws Exception;

	public List<LectApplyList> getLectEmpList(String lectName) throws Exception;

	public List<LectList> getLectBefore7days() throws Exception;

	public int insertLectEmp(LectApplyList list) throws Exception;

	public List<LectApplyList> getLectListByEmp(String empNo) throws Exception;

	public int deleteLectEmp(LectApplyList list) throws Exception;

	public List<LectApplyList> getLectRank() throws Exception;

	public int getLectCnt(LectList list) throws Exception;

	public int getLectEmpCnt(LectApplyList list) throws Exception;

}
