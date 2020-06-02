package com.bairock.iot.hamalib.linkage.loop;

import com.bairock.iot.hamalib.linkage.Linkage;
import com.bairock.iot.hamalib.linkage.LinkageHolder;
import com.bairock.iot.hamalib.linkage.LinkageTab;
import com.bairock.iot.hamalib.linkage.LinkageTabRow;

public class LoopHolder extends LinkageHolder{
	
	/**
	 * 
	 */
	public LoopHolder() {
		super();
	}
	
	/**
	 * 
	 */
	public void run(){
		if(!isEnable()){
			for (LinkageTabRow linkageTabRow : LinkageTab.getIns().getListLinkageTabRow()) {
				linkageTabRow.setLoop(-1);
			}
		}else{
			for(Linkage subLoop : getListLinkage()){
				subLoop.run();
			}
		}
	}
}
