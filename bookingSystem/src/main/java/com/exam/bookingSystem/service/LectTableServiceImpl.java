package com.exam.bookingSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.bookingSystem.mapper.LectTableMapper;
import com.exam.bookingSystem.model.LectApplyList;
import com.exam.bookingSystem.model.LectList;

@Service
public class LectTableServiceImpl implements LectTableService {
	@Autowired
	LectTableMapper sqlMapper;

	@Override
	public List<LectList> getLectAll() throws Exception {
		return sqlMapper.getLectAll();
	}

	@Override
	public void insertLect(LectList list) throws Exception {
		sqlMapper.insertLect(list);
	}

	@Override
	public List<LectApplyList> getLectEmpList() throws Exception {
		return sqlMapper.getLectEmpList();
	}

	@Override
	public List<LectList> getLectBefore7days() throws Exception {
		return sqlMapper.getLectBefore7days();
	}

	@Override
	public void insertLectEmp(LectApplyList list) throws Exception {
		int iCnt = 0;
		iCnt = sqlMapper.getLectEmpCnt();
		if (iCnt == 0) {
			if (list.getEmpNo().length() > 5) {
				throw new IllegalStateException("사번은 5자리 입니다.");
			} else {
				sqlMapper.insertLectEmp(list);
			}
		} else {
			throw new IllegalStateException("중복해서 신청할 수 없습니다.");
		}

	}

	@Override
	public List<LectApplyList> getLectListByEmp(String empNo) throws Exception {
		return sqlMapper.getLectListByEmp(empNo);
	}

	@Override
	public void deleteLectEmp(LectApplyList list) throws Exception {
		int iCnt = 0;
		iCnt = sqlMapper.getLectEmpCnt();
		if (iCnt > 0) {
			sqlMapper.deleteLectEmp(list);
		} else {
			throw new IllegalStateException("신청 내역이 존재하지 않습니다.");
		}
	}

	@Override
	public List<LectApplyList> getLectRank() throws Exception {
		return sqlMapper.getLectRank();
	}

	@Override
	public int getLectEmpCnt() throws Exception {
		return sqlMapper.getLectEmpCnt();
	}
}
