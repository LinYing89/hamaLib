package com.bairock.iot.hamalib.device;

/**
 * 
 * @author LinQiang
 *
 */
public interface IThreeStateDev extends IStateDev {

	/**
	 * stop the device
	 */
	void turnStop();
	
	/**
	 * 
	 * @return
	 */
	String getStopOrder();
}
