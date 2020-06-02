package com.bairock.iot.hamalib.device;

/**
 * device state helper
 * @author LinQiang
 *
 */
public class DevStateHelper {

	private static DevStateHelper ins;
	
	/**
	 * 开
	 */
	public static final String DS_KAI = "1";
	
	/**
	 * 关
	 */
	public static final String DS_GUAN = "0";
	
	/**
	 * 停
	 */
	public static final String DS_TING = "2";
	
	/**
	 * 正常
	 */
	public static final String DS_ZHENG_CHANG = "3";
	
	/**
	 * 异常
	 */
	public static final String DS_YI_CHANG = "4";

	/**
	 * 配置中
	 */
	public static final String CONFIGING = "5";

	/**
	 * 配置成功
	 */
	public static final String CONFIG_OK = "6";
	/**
	 * 未知状态
	 */
	public static final String DS_UNKNOW = "7";

	/**
	 * 获取开关状态编码, 0或1
	 */
	public static int getStateCode(String stateId) {
		if(stateId.equals(DS_KAI)) {
			return 1;
		}else {
			return 0;
		}
	}
	
	/**
	 * get instance of DevStateHelper
	 * @return ins
	 */
	public static DevStateHelper getIns(){
		if(null == ins){
			ins = new DevStateHelper();
		}
		return ins;
	}
}
