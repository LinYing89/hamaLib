package com.bairock.iot.hamalib.device;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class DevHaveChild extends Device {

	@JsonManagedReference("devparent_child")
	private List<Device> listDev = Collections.synchronizedList(new ArrayList<>());

	@JsonIgnore
	private Set<OnDeviceCollectionChangedListener> stOnDeviceCollectionChangedListener = new HashSet<>();
	/**
	 * 
	 */
	public DevHaveChild() {
		super();
	}

	/**
	 * 
	 * @param mcId
	 *            main code identify
	 * @param sc
	 *            sub code
	 */
	public DevHaveChild(String mcId, String sc) {
		super(mcId, sc);
	}

	public List<Device> getListDev() {
		return listDev;
	}

	public void setListDev(List<Device> listDev) {
		if(null == listDev) {
			return;
		}
		this.listDev.clear();
		for(Device dev : listDev) {
			addChildDev(dev);
		}
	}

	@Override
	public void setStatus(String ds) {
		super.setStatus(ds);
		if (ds.equals(DevStateHelper.DS_YI_CHANG) || ds.equals(DevStateHelper.DS_UNKNOW)) {
			for (Device dev : listDev) {
				dev.setStatus(ds);
			}
		}
	}

	@Override
	public void setCtrlModel(CtrlModel ctrlModel) {
		super.setCtrlModel(ctrlModel);
		for (Device dev : listDev) {
			dev.setCtrlModel(ctrlModel);
		}
	}

	@Override
	public void setDeleted(boolean deleted) {
		super.setDeleted(deleted);
		for (Device dev : listDev) {
			dev.setDeleted(deleted);
		}
	}

	@Override
	public void setLinkType(LinkType linkType) {
		for (Device dev : listDev) {
			dev.setLinkType(linkType);
		}
		super.setLinkType(linkType);
	}

	public Device findDevByCoding(String coding){
		if(null == coding){
			return null;
		}
		for(Device dev : listDev){
			if(dev.getCoding().equals(coding)){
				return dev;
			}else if(dev instanceof DevHaveChild) {
				Device dd = ((DevHaveChild)dev).findDevByCoding(coding);
				if(null != dd) {
					return dd;
				}
			}
		}
		return null;
	}
	
	/**
     * 
     * @param sc sub code
     * @return
     */
    public Device getSubDevBySc(String sc) {
        for (Device dev : getListDev()) {
            if (dev.getSubCode().equals(sc)) {
                return dev;
            }
        }
        return null;
    }
	
	public Device findDeviceByMainCodeAndSubCode(String mainCode, String subCode){
		if(null == mainCode || null == subCode) {
			return null;
		}
		for(Device dev : listDev){
			if(dev.getMainCode().equals(mainCode) && dev.getSubCode().equals(subCode)){
				return dev;
			}
		}
		return null;
	}

	public void addChildDev(Device device) {
		if (device != null) {
			device.setParent(this);
			device.setPid(this.getId());
			listDev.add(device);
			for(OnDeviceCollectionChangedListener listener : stOnDeviceCollectionChangedListener) {
				listener.onAdded(device);
			}
		}
	}

	public void removeChildDev(Device device) {
		listDev.remove(device);
		if (null != device) {
			for(OnDeviceCollectionChangedListener listener : stOnDeviceCollectionChangedListener) {
				listener.onRemoved(device);
			}
			device.setParent(null);
			device.setPid(null);
		}
	}
	
	public Set<OnDeviceCollectionChangedListener> getStOnDeviceCollectionChangedListener() {
        return stOnDeviceCollectionChangedListener;
    }

    public void addOnDeviceCollectionChangedListener(OnDeviceCollectionChangedListener listener) {
		stOnDeviceCollectionChangedListener.add(listener);
	}
	
	public void removeOnDeviceCollectionChangedListener(OnDeviceCollectionChangedListener listener) {
		stOnDeviceCollectionChangedListener.remove(listener);
	}
	
	public interface OnDeviceCollectionChangedListener{
		void onAdded(Device device);
		void onRemoved(Device device);
	}

//	public static void main(String[] args) {
//		Coordinator coor = (Coordinator)DeviceAssistent.createDeviceByMcId(MainCodeHelper.XIE_TIAO_QI, "1");
//		DevSwitchThreeRoad dstr = (DevSwitchThreeRoad)DeviceAssistent.createDeviceByMcId(MainCodeHelper.KG_3LU_2TAI, "2");
//		coor.addChildDev(dstr);
//		System.out.println(coor.getDevByCoding(dstr.getCoding()));
//		System.out.println(dstr.getDevByCoding(dstr.getSubDevBySc("1").getCoding()));
//		System.out.println(coor.getDevByCoding(dstr.getSubDevBySc("1").getCoding()));
//	}
}
