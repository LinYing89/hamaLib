package com.bairock.iot.hamalib.device.devcollect;

public class Temperature extends DevCollectSignal {

	public Temperature() {
		this("", "");
	}

	public Temperature(String mcId, String sc) {
		super(mcId, sc);
		getCollectProperty().setCollectSrc(CollectSignalSource.DIGIT);
		if(getCollectProperty().getUnitSymbol().equals("")) {
			getCollectProperty().setUnitSymbol("℃");
		}
	}

}
