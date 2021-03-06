package com.bairock.iot.hamalib.device.devcollect;

import com.bairock.iot.hamalib.device.DevStateHelper;
import com.bairock.iot.hamalib.device.Device;
import com.bairock.iot.hamalib.device.DeviceAssistent;
import com.bairock.iot.hamalib.device.MainCodeHelper;
import com.bairock.iot.hamalib.device.OrderHelper;

public class DevCollectClimateContainer extends DevCollectSignalContainer {

	public DevCollectClimateContainer() {
		this("", "");
	}

	public DevCollectClimateContainer(String mcId, String sc) {
		super(mcId, sc);
	}

	@Override
	protected void initChildDevices() {
		// temperature
		Temperature temperature = (Temperature) DeviceAssistent.createDeviceByMc(MainCodeHelper.WEN_DU, "1");
		//temperature.setName(this.getCoding() + "_" + temperature.getCoding());
		addChildDev(temperature);

		// humidity
		Humidity humidity = (Humidity) DeviceAssistent.createDeviceByMc(MainCodeHelper.SHI_DU, "1");
		//humidity.setName(this.getCoding() + "_" + humidity.getCoding());
		addChildDev(humidity);

		// formaldehyde
		Formaldehyde formaldehyde = (Formaldehyde) DeviceAssistent.createDeviceByMc(MainCodeHelper.JIA_QUAN, "1");
		//formaldehyde.setName(this.getCoding() + "_" + formaldehyde.getCoding());
		addChildDev(formaldehyde);
	}

	public Temperature findTemperatureDev() {
		for(Device dev : getListDev()) {
			if(dev instanceof Temperature) {
				return (Temperature)dev;
			}
		}
		return null;
	}

	public Humidity findHumidityDev() {
		for(Device dev : getListDev()) {
			if(dev instanceof Humidity) {
				return (Humidity)dev;
			}
		}
		return null;
	}

	public Formaldehyde findFormaldehydeDev() {
		for(Device dev : getListDev()) {
			if(dev instanceof Formaldehyde) {
				return (Formaldehyde)dev;
			}
		}
		return null;
	}

	@Override
	public String createQueryOrder() {
		return OrderHelper.getOrderMsg(OrderHelper.QUERY_HEAD + getCoding() + OrderHelper.SEPARATOR + "3:4:5");
	}

	@Override
	public void removeChildDev(Device device) {
		return;
	}

	@Override
	protected void analysisMsgUnit(String msgUnit) {
		if (null == msgUnit || msgUnit.isEmpty()) {
			return;
		}

		String msgNoHead = msgUnit.substring(1);
		if (msgUnit.startsWith("3")) {
			Temperature t = findTemperatureDev();
			t.setStatus(DevStateHelper.DS_ZHENG_CHANG);
			t.handleSingleMsg("8" + msgNoHead);
		} else if (msgUnit.startsWith("4")) {
			Humidity h = findHumidityDev();
			h.setStatus(DevStateHelper.DS_ZHENG_CHANG);
			findHumidityDev().handleSingleMsg("8" + msgNoHead);
		} else if (msgUnit.startsWith("5")) {
			Formaldehyde f = findFormaldehydeDev();
			f.setStatus(DevStateHelper.DS_ZHENG_CHANG);
			f.handleSingleMsg("8" + msgNoHead);
		}
	}
}
