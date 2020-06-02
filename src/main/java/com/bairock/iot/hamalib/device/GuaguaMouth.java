package com.bairock.iot.hamalib.device;

/**
 * 
 * @author LinQiang
 *
 */
public class GuaguaMouth extends Device {

	/**
	 * 
	 */
	public GuaguaMouth(){
		this("","");
	}
	
	/**
	 * 
	 * @param mcId
	 * @param sc
	 */
	public GuaguaMouth(String mcId, String sc) {
		super(mcId, sc);
	}

	/**
	 * 
	 */
	public String getDevOrder(int speakCount, String speakContent) {
		//String order = "C" + getCoding() + ":" + speakCount + ":" + speakContent;
		String order = "C" + getCoding() + ":" + speakContent;
		return OrderHelper.getOrderMsg(order);
	}
}
