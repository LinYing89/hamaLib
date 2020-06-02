package com.bairock.iot.hamalib.linkage.guagua;

import java.util.ArrayList;
import java.util.List;

import com.bairock.iot.hamalib.device.GuaguaMouth;
import com.bairock.iot.hamalib.linkage.ChainHolder;
import com.bairock.iot.hamalib.linkage.Effect;
import com.bairock.iot.hamalib.linkage.Linkage;
import com.bairock.iot.hamalib.linkage.SubChain;

/**
 * 
 * @author LinQiang
 *
 */
public class GuaguaHolder extends ChainHolder{
	
	/**
	 * 
	 */
	public GuaguaHolder() {
		super();
	}

	/**
	 * 
	 */
	public void run(){
		if(isEnable()){
			for(Linkage linkageDevValue : getListLinkage()){
				runEffect((SubChain)linkageDevValue);
			}
		}
	}
	
	private void runEffect(SubChain linkageDevValue){
		if(!isEnable() || linkageDevValue.getListCondition().isEmpty() 
				|| linkageDevValue.getListEffect().isEmpty()){
			return;
		}
		if(linkageDevValue.getConditionResult()){
			if(!linkageDevValue.isTriggered()){
				linkageDevValue.setTriggered(true);
				List<Effect> list = new ArrayList<>(linkageDevValue.getListEffect());
				for(Effect effect : list){
					if(effect.getDevice() instanceof GuaguaMouth){
						GuaguaMouth guagua = (GuaguaMouth)(effect.getDevice());
						String order = guagua.getDevOrder(effect.getEffectCount(), effect.getEffectContent());
						GuaguaHelper.getIns().stateChangedListener(guagua, order, guagua.getCtrlModel());
					}
				}
			}
		}else{
			linkageDevValue.setTriggered(false);
		}
	}
}
