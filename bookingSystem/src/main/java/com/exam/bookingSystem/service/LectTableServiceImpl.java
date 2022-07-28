package com.exam.bookingSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.exam.bookingSystem.mapper.LectTableMapper;
import com.exam.bookingSystem.model.LectApplyList;
import com.exam.bookingSystem.model.LectList;

@Transactional(readOnly = true)
@Service
public class LectTableServiceImpl implements LectTableService {

	@Autowired
	LectTableMapper sqlMapper;

	@Override
	public List<LectList> getLectAll() throws Exception {
		return sqlMapper.getLectAll();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public int insertLect(LectList list) throws Exception {
		return sqlMapper.insertLect(list);
	}

	@Override
	public List<LectApplyList> getLectEmpList() throws Exception {
		return sqlMapper.getLectEmpList();
	}

	@Override
	public List<LectList> getLectBefore7days() throws Exception {
		return sqlMapper.getLectBefore7days();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public int insertLectEmp(LectApplyList list) throws Exception {
		int iCnt = 0;
		iCnt = sqlMapper.getLectEmpCnt(list);
		if (iCnt == 0) {
			if (list.getEmpNo().length() > 5) {
				throw new IllegalStateException("사번은 5자리 입니다.");
			} else {
				return sqlMapper.insertLectEmp(list);
			}
		} else {
			throw new IllegalStateException("중복해서 신청할 수 없습니다.");
		}

	}

	@Override
	public List<LectApplyList> getLectListByEmp(String empNo) throws Exception {
		return sqlMapper.getLectListByEmp(empNo);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public int deleteLectEmp(LectApplyList list) throws Exception {
		int iCnt = 0;
		iCnt = sqlMapper.getLectEmpCnt(list);
		if (iCnt > 0) {
			return sqlMapper.deleteLectEmp(list);
		} else {
			throw new IllegalStateException("신청 내역이 존재하지 않습니다.");
		}
	}

	@Override
	public List<LectApplyList> getLectRank() throws Exception {
		return sqlMapper.getLectRank();
	}

	@Override
	public int getLectEmpCnt(LectApplyList list) throws Exception {
		return sqlMapper.getLectEmpCnt(list);
	}
}
