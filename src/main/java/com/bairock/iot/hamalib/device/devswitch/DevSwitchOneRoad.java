package com.bairock.iot.hamalib.device.devswitch;

import com.bairock.iot.hamalib.device.DevStateHelper;
import com.bairock.iot.hamalib.device.Device;

/**
 * two state one road switch
 * @author LinQiang
 *
 */
public class DevSwitchOneRoad extends DevSwitch {

	/**
	 * 
	 */
	public DevSwitchOneRoad(){
		this("","");
	}
	
	/**
	 * 
	 * @param mcId main code identify
	 * @param sc sub code
	 */
	public DevSwitchOneRoad(String mcId, String sc){
		super(mcId, sc);
		addChildDev(new SubDev("smc_w", "2"));
	}
	
//	protected void handle7(String[] msgs) {
//		if(msgs.length != 3) {
//			return;
//		}
//		byte iHexState = Byte.parseByte(msgs[2], 16);
//		SubDev sd1 = (SubDev) getSubDevBySc(String.valueOf("2"));
//		String strState = getEnsureState(iHexState, 1);
//		DevStateHelper.getIns().setDsId(sd1, strState);
//	}
	
	protected void handle8(String[] msgs) {
		if(msgs.length < 2) {
			return;
		}
		byte iHexState = Byte.parseByte(msgs[1], 16);
		SubDev sd1 = (SubDev) getSubDevBySc(String.valueOf("2"));
		String strState = getEnsureState(iHexState, 2);
		sd1.setStatus(strState);
	}
	
	@Override
	public String createStateStr() {
		int state = 0;
		Device subDev = getListDev().get(0);
		if (!subDev.isKaiState()) {
			state |= 1;
			state <<= 1;
		}
		return String.valueOf(state);
	}
}
