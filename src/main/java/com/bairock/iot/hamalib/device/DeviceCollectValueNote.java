package com.bairock.iot.hamalib.device;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity implementation class for Entity: DeviceCollectValueNote
 *
 */
public class DeviceCollectValueNote implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private String id;

	private Device device;
	
	private float value;
	
	private Date noteTime;

	public DeviceCollectValueNote() {
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
	
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public Date getNoteTime() {
		return noteTime;
	}
	public void setNoteTime(Date noteTime) {
		this.noteTime = noteTime;
	}
}
