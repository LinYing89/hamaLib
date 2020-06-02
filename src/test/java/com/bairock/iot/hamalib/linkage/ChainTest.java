package com.bairock.iot.hamalib.linkage;

import com.bairock.iot.hamalib.device.CompareSymbol;
import com.bairock.iot.hamalib.device.DevStateHelper;
import com.bairock.iot.hamalib.device.MainCodeHelper;
import com.bairock.iot.hamalib.device.devcollect.Pressure;
import com.bairock.iot.hamalib.device.devswitch.DevSwitchOneRoad;

import junit.framework.TestCase;

public class ChainTest extends TestCase {

	ChainHolder chain = new ChainHolder();
	Pressure pressure = new Pressure(MainCodeHelper.YE_WEI, "0001");
	DevSwitchOneRoad devSwitch = new DevSwitchOneRoad(MainCodeHelper.KG_1LU_2TAI, "0001");
	
	protected void setUp() throws Exception {
		pressure.getCollectProperty().setCrestValue(100f);
		pressure.getCollectProperty().setLeastValue(0f);
		pressure.getCollectProperty().setCrestReferValue(100f);
		pressure.getCollectProperty().setLeastReferValue(0f);
		
		SubChain subChain = new SubChain();
		LinkageCondition condition = new LinkageCondition();
		condition.setLogic(ZLogic.OR);
		condition.setDevice(pressure);
		condition.setCompareSymbol(CompareSymbol.GREAT);
		condition.setCompareValue(70);
		subChain.addCondition(condition);
		
		Effect effect = new Effect();
		effect.setDevice(devSwitch.getListDev().get(0));
		effect.setDsId(DevStateHelper.DS_KAI);
		subChain.addEffect(effect);
		
		chain.addLinkage(subChain);
		
		super.setUp();
	}

	public void testRun() {
		pressure.handle("p72.5");
		devSwitch.handle("87");
		chain.setEnable(true);
		Linkage subChain = chain.getListLinkage().get(0);
		subChain.setEnable(true);
		chain.run();
		//chain.run();
		assertEquals(true, subChain.getConditionResult());
		LinkageTabRow linkTR = LinkageTab.getIns().getEqLinkageTabRow(devSwitch.getListDev().get(0));
		assertEquals(1, linkTR.getChainTem());
		assertEquals(-1, linkTR.getTimingTem());
		assertEquals(-1, linkTR.getLoop());
	}

}
