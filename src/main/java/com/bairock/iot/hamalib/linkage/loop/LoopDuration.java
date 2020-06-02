package com.bairock.iot.hamalib.linkage.loop;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.bairock.iot.hamalib.linkage.timing.MyTime;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class LoopDuration {

	private String id;
	
	@JsonBackReference("zloop_loopduration")
	private ZLoop zLoop;
	
	//@JoinColumn(name = "timer_id")
	private List<MyTime> listTimes = new ArrayList<>(2);
	
	private boolean deleted;
	
	public LoopDuration() {
		id = UUID.randomUUID().toString();
		MyTime t1 = new MyTime();
		t1.setType(0);
		MyTime t2 = new MyTime();
		t2.setType(1);
		listTimes.add(t1);
		listTimes.add(t2);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	public ZLoop getzLoop() {
		return zLoop;
	}

	public void setzLoop(ZLoop zLoop) {
		this.zLoop = zLoop;
	}

	public List<MyTime> getListTimes() {
		return listTimes;
	}

	public void setListTimes(List<MyTime> listTimes) {
		this.listTimes = listTimes;
	}

	@JsonIgnore
	public MyTime getOnKeepTime() {
		for(MyTime myTime : listTimes) {
			if(myTime.getType() == 1) {
				return myTime;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param onKeepTime
	 */
	public void setOnKeepTime(MyTime onKeepTime) {
		for(MyTime myTime : listTimes) {
			if(myTime.getType() == 1) {
				myTime = onKeepTime;
				return;
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	@JsonIgnore
	public MyTime getOffKeepTime() {
		for(MyTime myTime : listTimes) {
			if(myTime.getType() == 0) {
				return myTime;
			}
		}
		return null;
	}

	/**
	 * 
	 */
	public void setOffKeepTime(MyTime offKeepTime) {
		for(MyTime myTime : listTimes) {
			if(myTime.getType() == 0) {
				myTime = offKeepTime;
				return;
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * 
	 * @param deleted
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
