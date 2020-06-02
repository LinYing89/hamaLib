package com.bairock.iot.hamalib.device.devswitch;

import com.bairock.iot.hamalib.device.CtrlCodeHelper;
import com.bairock.iot.hamalib.device.DevStateHelper;
import com.bairock.iot.hamalib.device.IThreeStateDev;
import com.bairock.iot.hamalib.device.OrderHelper;

public class SubDevThreeState extends SubDev implements IThreeStateDev {

	public SubDevThreeState() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param mcId main code identify
	 * @param sc sub code
	 */
	public SubDevThreeState(String mcId, String sc){
		super(mcId, sc);
	}
	
	@Override
	public void turnStop() {
		setStatus(DevStateHelper.DS_TING);
	}

	@Override
	public String getStopOrder() {
		return getDevOrder(OrderHelper.CTRL_HEAD, CtrlCodeHelper.DCT_KAIGUAN_TING);
	}
}
