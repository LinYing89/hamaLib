package com.bairock.iot.hamalib.device;

import java.util.List;

import com.bairock.iot.hamalib.device.alarm.DevAlarm;
import com.bairock.iot.hamalib.device.devcollect.DevCollectClimateContainer;
import com.bairock.iot.hamalib.device.devcollect.DevCollectSignal;
import com.bairock.iot.hamalib.device.devcollect.DevCollectSignalContainer;
import com.bairock.iot.hamalib.device.devcollect.Formaldehyde;
import com.bairock.iot.hamalib.device.devcollect.Humidity;
import com.bairock.iot.hamalib.device.devcollect.Pressure;
import com.bairock.iot.hamalib.device.devcollect.Temperature;
import com.bairock.iot.hamalib.device.devswitch.DevSocket;
import com.bairock.iot.hamalib.device.devswitch.DevSwitchOneRoad;
import com.bairock.iot.hamalib.device.devswitch.DevSwitchThreeRoad;
import com.bairock.iot.hamalib.device.devswitch.DevSwitchThreeState;
import com.bairock.iot.hamalib.device.devswitch.DevSwitchTwoRoad;
import com.bairock.iot.hamalib.device.devswitch.DevSwitchXRoad;
import com.bairock.iot.hamalib.device.devswitch.SubDev;
import com.bairock.iot.hamalib.device.remoter.Curtain;
import com.bairock.iot.hamalib.device.remoter.CustomRemoter;
import com.bairock.iot.hamalib.device.remoter.Remoter;
import com.bairock.iot.hamalib.device.remoter.RemoterContainer;
import com.bairock.iot.hamalib.device.remoter.Television;
import com.bairock.iot.hamalib.device.virtual.Counter;
import com.bairock.iot.hamalib.device.virtual.DevParam;
import com.bairock.iot.hamalib.user.DevGroup;

/**
 * 
 * @author LinQiang
 *
 */
public class DeviceAssistent {

	public static Device createDeviceByMc(String mc, String sc, DevGroup devGroup){
		Device device = createDeviceByMc(mc, sc);
		devGroup.createDefaultDeviceName(device);
		return device;
	}

	public static Device createDeviceByMc(String mc, String sc){
		if(null == mc || null == sc){
			return null;
		}
		Device device = null;
		switch(mc){
		case MainCodeHelper.XIE_TIAO_QI:
		case MainCodeHelper.PLC:
			device = new Coordinator(mc, sc);
			break;
		case MainCodeHelper.KG_1LU_2TAI:
			device = new DevSwitchOneRoad(mc, sc);
			break;
		case MainCodeHelper.KG_2LU_2TAI:
			device = new DevSwitchTwoRoad(mc, sc);
			break;
		case MainCodeHelper.KG_3LU_2TAI:
			device = new DevSwitchThreeRoad(mc, sc);
			break;
		case MainCodeHelper.KG_XLU_2TAI:
			device = new DevSwitchXRoad(mc, sc);
			break;
		case MainCodeHelper.KG_3TAI:
			device = new DevSwitchThreeState(mc, sc);
			break;
		case MainCodeHelper.CHA_ZUO:
			device = new DevSocket(mc, sc);
			break;
		case MainCodeHelper.YAO_KONG:
			device = new RemoterContainer(mc, sc);
			break;
		case MainCodeHelper.VT_PARAM:
            device = new DevParam(mc, sc);
            break;
		case MainCodeHelper.VT_COUNTER:
            device = new Counter(mc, sc);
            break;
		case MainCodeHelper.YE_WEI:
			device = new Pressure(mc, sc);
			break;
		case MainCodeHelper.COLLECTOR_SIGNAL_CONTAINER:
			device = new DevCollectSignalContainer(mc, sc);
			break;
		case MainCodeHelper.COLLECTOR_CLIMATE_CONTAINER:
			device = new DevCollectClimateContainer(mc, sc);
			break;
		case MainCodeHelper.COLLECTOR_SIGNAL:
			device = new DevCollectSignal(mc, sc);
			break;
		case MainCodeHelper.GUAGUA_MOUTH:
			device = new GuaguaMouth(mc, sc);
			break;
		case MainCodeHelper.WEN_DU:
			device = new Temperature(mc, sc);
			break;
		case MainCodeHelper.SHI_DU:
			device = new Humidity(mc, sc);
			break;
		case MainCodeHelper.JIA_QUAN:
			device = new Formaldehyde(mc, sc);
			break;
		case MainCodeHelper.YAN_WU:
		case MainCodeHelper.MEN_JIN:
			device = new DevAlarm(mc, sc);
			break;
		case MainCodeHelper.SMC_REMOTER_CHUANG_LIAN:
			device = new Curtain(mc, sc);
			break;
		case MainCodeHelper.SMC_REMOTER_DIAN_SHI:
			device = new Television(mc, sc);
			break;
		case MainCodeHelper.SMC_REMOTER_ZI_DING_YI:
			device = new CustomRemoter(mc, sc);
			break;
		}
		if(null == device) {
			if(mc.startsWith("smc")) {
				device = new SubDev(mc, sc);
			}
		}
		DevGroup.createDefaultDeviceNameAddSubCode(device);
		return device;
	}
	
	/**
	 * create device by device coding
	 * @param coding coding is main code + sub code
	 * @return
	 */
	public static Device createDeviceByCoding(String coding) {
		if (null == coding) {
			return null;
		}
		if (null == coding || coding.length() < 3) {
			return null;
		}
		Device device = null;
		String mc = coding.substring(0, 2);
		String sc = coding.substring(2);
		device = createDeviceByMc(mc, sc);
		return device;
	}

	public static Device getDeviceWithMc(String mc, String sc, List<Device> listDevice){
		if(null == mc || null == sc){
			return null;
		}
		for(Device dev : listDevice){
			if(dev.getMainCode().equals(mc) && dev.getSubCode().equals(sc)){
				return dev;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param coding coding is main code + sub code
	 * @param listDevice device list for scan
	 * @return
	 */
	public static Device getDeviceWithCoding(String coding, List<Device> listDevice){
		if(null == coding || coding.length() < 3){
			return null;
		}
		Device dev = null;
		try{
			String mc = coding.substring(0, 2);
			String sc = coding.substring(2);
			dev = getDeviceWithMc(mc, sc, listDevice);
		}catch(Exception e){
			
		}
		return dev;
	}
}
