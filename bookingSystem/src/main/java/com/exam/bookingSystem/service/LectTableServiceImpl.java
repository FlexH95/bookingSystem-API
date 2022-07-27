package com.exam.bookingSystem.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.bookingSystem.mapper.LectTableMapper;

@Service
public class LectTableServiceImpl implements LectTableService {
	@Autowired
	LectTableMapper sqlMapper;

	@Override
	public List<Map<String, Object>> SelectAllList() throws Exception {
		return sqlMapper.SelectAllList();
	}
}
