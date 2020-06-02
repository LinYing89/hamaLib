package com.bairock.iot.hamalib.device.devcollect;

/**
 * switch collect device, value is only 0 or 1
 * @author 44489
 *
 */
public class DevCollectSwitch extends DevCollect {

	public DevCollectSwitch() {
		init();
	}

	public DevCollectSwitch(String mcId, String sc) {
		super(mcId, sc);
		init();
	}

	private void init() {
		getCollectProperty().setCrestValue(1f);
		getCollectProperty().setLeastValue(0f);
		getCollectProperty().setCrestReferValue(1f);
		getCollectProperty().setLeastReferValue(0f);
	}

	@Override
	public void handleSingleMsg(String state) {
		if (null != state) {
			if (state.length() < 2) {
				return;
			}

			if (state.startsWith("8")) {
				try {
					String strState = state.substring(1);
					if(strState.equals("0")) {
						getCollectProperty().setCurrentValue(0f);
					}else {
						getCollectProperty().setCurrentValue(1f);
					}
				} catch (Exception e) {
				}
			}
		}
	}
}
