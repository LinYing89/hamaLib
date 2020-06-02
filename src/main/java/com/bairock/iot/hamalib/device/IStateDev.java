package com.bairock.iot.hamalib.device;

/**
 * state category device
 * @author LinQiang
 *
 */
public interface IStateDev{

	/**
	 * turn on device
	 */
	void turnOn();
	
	/**
	 * turn off device
	 */
	void turnOff();
	
	/**
	 * get order of turn on device
	 * @return
	 */
	String getTurnOnOrder();
	
	/**
	 * get order of turn off device
	 * @return
	 */
	String getTurnOffOrder();

	
	CtrlModel getCtrlModel();
}
