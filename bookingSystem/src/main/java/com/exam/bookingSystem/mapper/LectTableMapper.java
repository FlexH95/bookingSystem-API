package com.exam.bookingSystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.exam.bookingSystem.model.LectList;

@Mapper
public interface LectTableMapper {

	public List<LectList> SelectList();

}
