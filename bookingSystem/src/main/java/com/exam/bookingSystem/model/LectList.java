package com.exam.bookingSystem.model;

import lombok.Data;

@Data
public class LectList {
	private String lecturerName;
	private String placeName;
	private int capCnt;
	private String dateTimeStamp;
	private String lectDesc;

	public String getLecturerName() {
		return lecturerName;
	}

	public void setLecturerName(String lecturerName) {
		this.lecturerName = lecturerName;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public int getCapCnt() {
		return capCnt;
	}

	public void setCapCnt(int capCnt) {
		this.capCnt = capCnt;
	}

	public String getDateTimeStamp() {
		return dateTimeStamp;
	}

	public void setDateTimeStamp(String dateTimeStamp) {
		this.dateTimeStamp = dateTimeStamp;
	}

	public String getLectDesc() {
		return lectDesc;
	}

	public void setLectDesc(String desc) {
		this.lectDesc = desc;
	}
}
