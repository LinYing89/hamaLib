package com.bairock.iot.hamalib.device.devcollect;

import com.bairock.iot.hamalib.device.CtrlCodeHelper;

/**
 * 
 * @author LinQiang
 *
 */
public class Pressure extends DevCollect {

	/**
	 * 
	 */
	public Pressure() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param mcId
	 * @param sc
	 */
	public Pressure(String mcId, String sc) {
		super(mcId, sc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleSingleMsg(String state) {
		if (null != state) {
			if (state.length() < 2) {
				return;
			}

			super.handleSingleMsg(state);
			if (state.startsWith(CtrlCodeHelper.getIns().getDct(CtrlCodeHelper.DCT_PRESSURE_PER_VALUE))) {
				String strState = state.substring(1);
				getCollectProperty().setPercent(Float.valueOf(strState));
				setRatio();
			}
		}
	}

}
