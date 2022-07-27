package com.exam.bookingSystem.model;

import lombok.Data;

@Data
public class LectApplyList {

	private String lecturerName;
	private String empNo;

	public String getLecturerName() {
		return lecturerName;
	}

	public void setLecturerName(String lecturerName) {
		this.lecturerName = lecturerName;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
}
