package com.bairock.iot.hamalib.test;

import com.bairock.iot.hamalib.communication.DevChannelBridge;
import com.bairock.iot.hamalib.communication.DevServer;

public class ConfigNewDevice {

	public static void main(String[] args) {
		try {
			DevServer.getIns().run();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DevChannelBridge.analysiserName = MyMessageA.class.getName();
	}

}
