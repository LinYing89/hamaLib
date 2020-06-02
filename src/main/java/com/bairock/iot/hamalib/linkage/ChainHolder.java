package com.bairock.iot.hamalib.linkage;

/**
 * chain
 * @author LinQiang
 *
 */
public class ChainHolder extends LinkageHolder{
	
	/**
	 * 
	 */
	public ChainHolder() {
		super();
	}

	/**
	 * 
	 */
	public void run(){
		if(!isEnable()){
			for (LinkageTabRow linkageTabRow : LinkageTab.getIns().getListLinkageTabRow()) {
				linkageTabRow.setChain(-1);
			}
		}else{
			for(Linkage subChain : getListLinkage()){
				subChain.run();
			}
		}
	}
}
