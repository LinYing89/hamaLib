package com.bairock.iot.hamalib.device.alarm;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 报警设备触发器
 * @author 44489
 *
 */
public class AlarmTrigger {

	private String id;

	@JsonBackReference("devalarm_trigger")
	private DevAlarm devAlarm;
	
	private boolean enable = true;
	
	private String message = "";

	public AlarmTrigger() {
		this(true);
	}
	
	public AlarmTrigger(boolean enable) {
		this(enable, "");
	}
	
	public AlarmTrigger(boolean enable, String message) {
		this.enable = enable;
		this.message = message;
		setId(UUID.randomUUID().toString());
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DevAlarm getDevAlarm() {
		return devAlarm;
	}

	public void setDevAlarm(DevAlarm devAlarm) {
		this.devAlarm = devAlarm;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getMessage() {
		if(null == message || message.isEmpty()) {
			message = createDefaultMessage();
		}
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	private String createDefaultMessage() {
		return devAlarm.getName() + " alarm";
	}
}
