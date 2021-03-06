package com.bairock.iot.hamalib.device.devcollect;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.bairock.iot.hamalib.device.CompareSymbol;
import com.bairock.iot.hamalib.device.DeviceAssistent;
import com.bairock.iot.hamalib.device.MainCodeHelper;

public class TemperatureTest {

	private Temperature temperature;
	
	@Before
	public void setUp() throws Exception {
		temperature = (Temperature) DeviceAssistent.createDeviceByMc(MainCodeHelper.WEN_DU, "1");
		CollectProperty cp = temperature.getCollectProperty();
		cp.setOnValueTriggedListener(new CollectProperty.OnValueTriggedListener() {
			
			@Override
			public void onValueTrigged(ValueTrigger trigger, float value) {
				System.out.println("触发事件" + trigger + " value - " + value);
				
			}

			@Override
			public void onValueTriggedRelieve(ValueTrigger trigger, float value) {
				System.out.println("解除事件" + trigger + " value - " + value);
			}
		});
		
		ValueTrigger trigger = new ValueTrigger();
		trigger.setCompareSymbol(CompareSymbol.EQUAL);
		trigger.setEnable(true);
		trigger.setMessage("等于");
		trigger.setTriggerValue(33.03f);
		cp.addValueTrigger(trigger);
		
		ValueTrigger trigger1 = new ValueTrigger();
		trigger1.setCompareSymbol(CompareSymbol.LESS);
		trigger1.setEnable(true);
		trigger1.setMessage("小于");
		trigger1.setTriggerValue(32f);
		cp.addValueTrigger(trigger1);
		
		ValueTrigger trigger2 = new ValueTrigger();
		trigger2.setCompareSymbol(CompareSymbol.GREAT);
		trigger2.setEnable(true);
		trigger2.setMessage("大于");
		trigger2.setTriggerValue(34f);
		cp.addValueTrigger(trigger2);
	}

	@Test
	public void test() {
		temperature.handle("80ce7");
		assertEquals(33.03f, temperature.getCollectProperty().getCurrentValue(), 0.01);
		
		temperature.handle("80c01");
		assertEquals(30.73f, temperature.getCollectProperty().getCurrentValue(), 0.01);
		temperature.handle("80001");
		temperature.handle("80ce5");
		temperature.handle("80001");
		
		temperature.handle("80fe7");
		assertEquals(40.71f, temperature.getCollectProperty().getCurrentValue(), 0.01);
		
		temperature.handle("80ff7");
		temperature.handle("80ce8");
		temperature.handle("80ff7");
		temperature.handle("8007");
	}

}
