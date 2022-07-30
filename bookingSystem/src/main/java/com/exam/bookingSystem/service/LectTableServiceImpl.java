package com.exam.bookingSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.exam.bookingSystem.mapper.LectTableMapper;
import com.exam.bookingSystem.model.LectApplyList;
import com.exam.bookingSystem.model.LectList;

/** Transactional : 데이터 일관성 및 동시성 이슈 고려 **/
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
@Service
public class LectTableServiceImpl implements LectTableService {

	@Autowired
	LectTableMapper sqlMapper;

	@Override
	public List<LectList> getLectAll(String lectName) throws Exception {
		return sqlMapper.getLectAll("");
	}

	@Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int insertLect(LectList list) throws Exception {
		/** 강연 중복 등록되어 있는지 확인 **/
		int iCnt = 0;
		iCnt = sqlMapper.getLectCnt(list);
		if (iCnt == 0) {
			return sqlMapper.insertLect(list);
		} else {
			throw new IllegalStateException("중복해서 등록할 수 없습니다.");
		}

	}

	@Override
	public List<LectApplyList> getLectEmpList(String lectName) throws Exception {
		return sqlMapper.getLectEmpList(lectName);
	}

	@Override
	public List<LectList> getLectBefore7days() throws Exception {
		return sqlMapper.getLectBefore7days();
	}

	@Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int insertLectEmp(LectApplyList list) throws Exception {

		String lectName = list.getLecturerName();
		/** 강연이 등록되어 있는지 확인 **/
		List<LectList> temp = sqlMapper.getLectAll(lectName);
		if (temp.size() == 0) {
			throw new IllegalStateException("강연이 등록되어 있지 않습니다.");
		}

		/** 중복 신청인지 확인 **/
		int iCnt = 0;
		iCnt = sqlMapper.getLectEmpCnt(list);
		if (iCnt == 0) {
			/** 사번이 5자리인지 확인 **/
			if (list.getEmpNo().length() == 5) {
				return sqlMapper.insertLectEmp(list);
			} else {
				throw new IllegalStateException("사번은 5자리 입니다.");
			}
		} else {
			throw new IllegalStateException("중복해서 신청할 수 없습니다.");
		}

	}

	@Override
	public List<LectApplyList> getLectListByEmp(String empNo) throws Exception {
		return sqlMapper.getLectListByEmp(empNo);
	}

	@Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int deleteLectEmp(LectApplyList list) throws Exception {
		int iCnt = 0;
		/** 강연 신청 내역이 존재하는지 확인 **/
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
	public int getLectCnt(LectList list) throws Exception {
		return sqlMapper.getLectCnt(list);
	}

	@Override
	public int getLectEmpCnt(LectApplyList list) throws Exception {
		return sqlMapper.getLectEmpCnt(list);
	}
}
