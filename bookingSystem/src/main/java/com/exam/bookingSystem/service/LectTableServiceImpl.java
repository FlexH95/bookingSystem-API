package com.exam.bookingSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.bookingSystem.mapper.LectTableMapper;
import com.exam.bookingSystem.model.LectList;

@Service
public class LectTableServiceImpl implements LectTableService {
	@Autowired
	LectTableMapper sqlMapper;

	@Override
	public List<LectList> SelectList(){
		return sqlMapper.SelectList();
	}
}
