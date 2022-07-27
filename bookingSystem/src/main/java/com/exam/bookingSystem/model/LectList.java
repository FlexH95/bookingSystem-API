package com.exam.bookingSystem.model;

public class LectList {
	private String lecturerName;
	private String placeName;
	private int capCount;
	private String dateTime;
	private String desc;

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

	public int getCapCount() {
		return capCount;
	}

	public void setCapCount(int capCount) {
		this.capCount = capCount;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
