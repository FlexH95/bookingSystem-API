package com.exam.bookingSystem.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LectTableMapper {

	public List<Map<String, Object>> SelectAllList() throws Exception;

}
