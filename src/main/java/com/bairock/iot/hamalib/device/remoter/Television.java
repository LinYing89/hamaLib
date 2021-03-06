package com.bairock.iot.hamalib.device.remoter;

/**
 * 电视
 * @author 44489
 *
 */
public class Television extends Remoter {

	public Television() {
		this("", "");
	}

	public Television(String mcId, String sc) {
		super(mcId, sc);
		initRemoteKeys();
	}

	/**
	 * 初始化电视按键集合
	 */
	public void initRemoteKeys() {
		RemoterKey rk = new RemoterKey("1", "静音");
		RemoterKey rk2 = new RemoterKey("2", "开关");
		RemoterKey rk3 = new RemoterKey("3", "1");
		RemoterKey rk4 = new RemoterKey("4", "2");
		RemoterKey rk5 = new RemoterKey("5", "3");
		RemoterKey rk6 = new RemoterKey("6", "4");
		RemoterKey rk7 = new RemoterKey("7", "5");
		RemoterKey rk8 = new RemoterKey("8", "6");
		RemoterKey rk9 = new RemoterKey("9", "7");
		RemoterKey rk0 = new RemoterKey("10", "8");
		RemoterKey rk11 = new RemoterKey("11", "9");
		RemoterKey rk12 = new RemoterKey("12", "0");
		RemoterKey rk13 = new RemoterKey("13", "返回");
		RemoterKey rk14 = new RemoterKey("14", "tvav");
		RemoterKey rk15 = new RemoterKey("15", "音量+");
		RemoterKey rk16 = new RemoterKey("16", "音量-");
		RemoterKey rk17 = new RemoterKey("17", "左");
		RemoterKey rk18 = new RemoterKey("18", "上");
		RemoterKey rk19 = new RemoterKey("19", "右");
		RemoterKey rk20 = new RemoterKey("20", "下");
		RemoterKey rk21 = new RemoterKey("21", "确定");
		RemoterKey rk22 = new RemoterKey("22", "扩展1");
		RemoterKey rk23 = new RemoterKey("23", "扩展2");
		RemoterKey rk24 = new RemoterKey("24", "扩展3");
		RemoterKey rk25 = new RemoterKey("25", "扩展4");
		RemoterKey rk26 = new RemoterKey("26", "扩展5");
		RemoterKey rk27 = new RemoterKey("27", "扩展6");
		RemoterKey rk28 = new RemoterKey("28", "扩展7");
		RemoterKey rk29 = new RemoterKey("29", "扩展8");
		
		addRemoterKey(rk);
		addRemoterKey(rk2);
		addRemoterKey(rk3);
		addRemoterKey(rk4);
		addRemoterKey(rk5);
		addRemoterKey(rk6);
		addRemoterKey(rk7);
		addRemoterKey(rk8);
		addRemoterKey(rk9);
		addRemoterKey(rk0);
		addRemoterKey(rk11);
		addRemoterKey(rk12);
		addRemoterKey(rk13);
		addRemoterKey(rk14);
		addRemoterKey(rk15);
		addRemoterKey(rk16);
		addRemoterKey(rk17);
		addRemoterKey(rk18);
		addRemoterKey(rk19);
		addRemoterKey(rk20);
		addRemoterKey(rk21);
		addRemoterKey(rk22);
		addRemoterKey(rk23);
		addRemoterKey(rk24);
		addRemoterKey(rk25);
		addRemoterKey(rk26);
		addRemoterKey(rk27);
		addRemoterKey(rk28);
		addRemoterKey(rk29);
	}
}
