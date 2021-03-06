package com.bairock.iot.hamalib.device;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IDevice {

	/**
	 * get super parent
	 * if the device have no parent, return this;
	 * if the device have parent, and the parent also have parent, 
	 * return parent's parent till the root parent
	 * @return
	 */
	@JsonIgnore
	Device findSuperParent();
	
	/**
	 * get main code and sub code
	 * if device is main device,return main code + sub code of main device
	 * then if device is sub device, return main code + sub code of main device + 
	 * "_" + main code + sub code of sub device
	 * @return main code + cub code, like B10001 of main device or B10001_101 of sub device
	 */
	@JsonIgnore
	String getCoding();
	
	/**
	 * get main code and sub code contains parent's coding
	 * if device is main device,return main code + sub code of main device
	 * then if device have parent, return parent's main code + sub code of + 
	 * "_" + main code + sub code of this device
	 * @return main code + cub code, like B10001 of main device or B10001_101 of sub device
	 * 	and like bx0001_b11 or A10001_B10001
	 */
	@JsonIgnore
	String getLongCoding();
	
	void turnOn();
	
	void turnOff();
	/**
	 * turn device gear to on
	 */
	void turnGearKai();
	
	/**
	 * turn device gear to off
	 */
	void turnGearGuan();
	
	/**
	 * turn device gear to auto
	 */
	void turnGearAuto();
	
	/**
	 * get device order by device control code identity
	 * @param dctId device control code identity
	 * @return order of device
	 */
	String getDevOrder(String orderHead,String dctId);
	
	String createQueryOrder();
	String createTurnLocalModelOrder(String ip, int port);
	String createTurnRemoteModelOrder(String ip, int port);
	String createAbnormalOrder();
	String createInitOrder();
	/**
	 * get device state
	 * @return
	 */
	@JsonIgnore
	String getStatus();
	
	/**
	 * if the state is kai
	 * @return
	 */
	@JsonIgnore
	boolean isKaiState();
	
	/**
	 * is the device is normal
	 * @return
	 */
	@JsonIgnore
	boolean isNormal();
	
	/**
	 * handle state
	 * @param state
	 * @return
	 */
	boolean handle(String state);
	
	/**
	 * handle singleMsg
	 * @param singleMsg
	 * @return
	 */
	void handleSingleMsg(String singleMsg);
}

