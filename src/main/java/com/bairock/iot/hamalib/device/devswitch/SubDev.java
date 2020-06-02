package com.bairock.iot.hamalib.device.devswitch;

import com.bairock.iot.hamalib.device.CtrlCodeHelper;
import com.bairock.iot.hamalib.device.CtrlModel;
import com.bairock.iot.hamalib.device.DevStateHelper;
import com.bairock.iot.hamalib.device.Device;
import com.bairock.iot.hamalib.device.IStateDev;
import com.bairock.iot.hamalib.device.OrderHelper;
import com.bairock.iot.hamalib.user.DevGroup;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class SubDev extends Device implements IStateDev{

	public SubDev() {
		super();
	}

	public SubDev(String mcId, String sc) {
		super(mcId, sc);
	}

	@Override
	public String getCoding() {
		return getMainCode() + "_" + getSubCode();
	}

	@Override
	public DevGroup getDevGroup() {
		return getParent().getDevGroup();
	}

	@Override
	public CtrlModel getCtrlModel() {
		return getParent().getCtrlModel();
	}

	@Override
	public void turnOn() {
		setStatus(DevStateHelper.DS_KAI);
	}

	@Override
	public void turnOff() {
		setStatus(DevStateHelper.DS_GUAN);
	}

	@Override
	@JsonIgnore
	public String getTurnOnOrder() {
		return getDevOrder(OrderHelper.CTRL_HEAD, CtrlCodeHelper.DCT_KAIGUAN_KAI);
	}

	@Override
	@JsonIgnore
	public String getTurnOffOrder() {
		return getDevOrder(OrderHelper.CTRL_HEAD, CtrlCodeHelper.DCT_KAIGUAN_GUAN);
	}

	@Override
	@JsonIgnore
	public String getDevOrder(String orderHead, String dctId) {
		if (null == orderHead) {
			orderHead = "";
		}
		int road = Integer.parseInt(getSubCode());
		String order = orderHead + getParent().getCoding() + ":" + CtrlCodeHelper.getIns().getDct(dctId)
				+ Integer.toHexString(road);
		return OrderHelper.getOrderMsg(order);
	}

    @Override
    public int compareTo(Device o) {
        if (o == null) {
            return -1;
        }else if(this.getSort() == o.getSort()) {
            return Integer.parseInt(this.getSubCode()) - Integer.parseInt(o.getSubCode());
        }else {
            return this.getSort() - o.getSort();
        }
    }
	
}
