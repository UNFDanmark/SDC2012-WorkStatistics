package dk.unf.sdc.gruppeh;

import java.util.Calendar;
import java.util.Date;

public class WorkTime {
	private int id;
	private long day;
	private long lenghtInMin;
	
	WorkTime() {
		id = 0;
		day = System.currentTimeMillis()/1000;
	}
	
	WorkTime(int lenghtInMin) {
		this();
		this.lenghtInMin = lenghtInMin;
	}
	
	public long returnLenghtWorkTime(long day) {
		lenghtInMin = (System.currentTimeMillis()/1000 - day);
		lenghtInMin /= 60; 
		return lenghtInMin;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getDay() {
		return day;
	}

	public void setDay(long day) {
		this.day = day;
	}

	public long getLenghtInMin() {
		return lenghtInMin;
	}

	public void setLenghtInMin(int lenghtInMin) {
		this.lenghtInMin = lenghtInMin;
	}

}
