package com.bairock.iot.hamalib.device;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity implementation class for Entity: DeviceStateNote
 *
 */
public class DeviceStateNote implements Serializable {

	private String id;

	private Device device;
	
	private int state;

	private Date noteTime;
	
	private static final long serialVersionUID = 1L;

	public DeviceStateNote() {
		super();
	}   
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}   
	public Device getDevice() {
		return this.device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}   
	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}
	public Date getNoteTime() {
		return noteTime;
	}
	public void setNoteTime(Date noteTime) {
		this.noteTime = noteTime;
	}
   
}
