package com.bairock.iot.hamalib.device;

public class DevContainer extends DevHaveChild {

	public DevContainer() {
		this("","");
	}

	public DevContainer(String mcId, String sc) {
		super(mcId, sc);
	}

}
