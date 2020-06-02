package com.bairock.iot.hamalib.device.remoter;

import com.bairock.iot.hamalib.device.DevHaveChild;
import com.bairock.iot.hamalib.device.DevStateHelper;
import com.bairock.iot.hamalib.device.Device;
import com.bairock.iot.hamalib.device.DeviceAssistent;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class RemoterContainer extends DevHaveChild {

	@JsonIgnore
	private RemoterKey studingKey;
	
	@JsonIgnore
	private OnRemoterOrderSuccessListener onRemoterOrderSuccessListener;
	
	public RemoterContainer() {
		this("", "");
	}

	public RemoterContainer(String mcId, String sc) {
		super(mcId, sc);
	}

	public OnRemoterOrderSuccessListener getOnRemoterOrderSuccessListener() {
		return onRemoterOrderSuccessListener;
	}

	public void setOnRemoterOrderSuccessListener(OnRemoterOrderSuccessListener onRemoterOrderSuccessListener) {
		this.onRemoterOrderSuccessListener = onRemoterOrderSuccessListener;
	}

	public String createSubCode(String mainCode) {
		int subCode = 1;
		boolean haved = false;
		for(int i = 1; i < 100; i++) {
			haved = false;
			subCode = i;
			for(Device r : getListDev()) {
				if(r.getMainCode().equals(mainCode)) {
					if(r.getSubCode().equals(String.valueOf(subCode))) {
						haved = true;
						break;
					}
				}
			}
			if(!haved) {
				break;
			}
		}
		return String.valueOf(subCode);
	}
	
	public Remoter createRemoter(String mainCodeId) {
		String subCode = createSubCode(mainCodeId);
		Remoter r = (Remoter) DeviceAssistent.createDeviceByMc(mainCodeId, subCode);
		return r;
	}
	
	@Override
	public void setStatus(String ds) {
		super.setStatus(ds);
		if (ds.equals(DevStateHelper.DS_ZHENG_CHANG)) {
			for (Device dev : getListDev()) {
				dev.setStatus(ds);
			}
		}
	}

	@Override
	public void handleSingleMsg(String singleMsg) {
		if(null == singleMsg || singleMsg.length() < 2) {
			return;
		}
		String head = singleMsg.substring(0, 1);
		switch(head) {
		case "3":
			String remoterCoding = singleMsg.substring(1, 3);
			Remoter remoter = (Remoter) findDevByCoding(remoterCoding);
			if(null != remoter) {
				String keyNumber = singleMsg.substring(3);
				studingKey = remoter.findKeyByNumber(keyNumber);
			}
			break;
		case "9":
			if(null != studingKey) {
				if(null != onRemoterOrderSuccessListener) {
					onRemoterOrderSuccessListener.onRemoterOrderSuccess(studingKey);
				}
			}
			break;
		}
	}
	
	/**
	 * 按键命令反馈接口
	 * @author 44489
	 *
	 */
	public interface OnRemoterOrderSuccessListener{
		/**
		 * 按键命令收到反馈
		 * @param studingKey
		 */
		void onRemoterOrderSuccess(RemoterKey studingKey);
	}
}
