package com.bairock.iot.hamalib.device.devswitch;

import com.bairock.iot.hamalib.device.Device;

public class DevSwitchTwoRoad extends DevSwitch {

	public DevSwitchTwoRoad() {
		this("", "");
	}

	public DevSwitchTwoRoad(String mcId, String sc) {
		super(mcId, sc);
		addChildDev(new SubDev("smc_w", "1"));
		addChildDev(new SubDev("smc_w", "3"));
	}
	
	@Override
	protected void handle8(String[] msgs) {
		if(msgs.length < 2) {
			return;
		}
		byte iHexState = Byte.parseByte(msgs[1], 16);
		SubDev sd1 = (SubDev) getSubDevBySc(String.valueOf("1"));
		String strState = getEnsureState(iHexState, 3);
		sd1.setStatus(strState);

		SubDev sd2 = (SubDev) getSubDevBySc(String.valueOf("3"));
		strState = getEnsureState(iHexState, 1);
		sd2.setStatus(strState);
	}
	
	@Override
	public String createStateStr() {
		int state = 0;
		Device subDev3 = getSubDevBySc("3");
		if (!subDev3.isKaiState()) {
			state |= 1;
			state <<= 2;
		}
		Device subDev1 = getSubDevBySc("1");
		if (!subDev1.isKaiState()) {
			state |= 1;
		}
		return String.valueOf(state);
	}
}
