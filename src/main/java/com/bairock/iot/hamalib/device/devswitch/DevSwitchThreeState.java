package com.bairock.iot.hamalib.device.devswitch;

/**
 * 
 * @author LinQiang
 *
 */
public class DevSwitchThreeState extends DevSwitch {

	/**
	 * 
	 */
	public DevSwitchThreeState() {
		this("","");
	}

	/**
	 * 
	 * @param mcId
	 * @param sc
	 */
	public DevSwitchThreeState(String mcId, String sc) {
		super(mcId, sc);
		addChildDev(new SubDevThreeState("smc_w", "1"));
	}
	
}
