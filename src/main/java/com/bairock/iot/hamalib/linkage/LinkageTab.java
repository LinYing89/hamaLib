package com.bairock.iot.hamalib.linkage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bairock.iot.hamalib.device.CtrlCodeHelper;
import com.bairock.iot.hamalib.device.CtrlModel;
import com.bairock.iot.hamalib.device.DevStateHelper;
import com.bairock.iot.hamalib.device.Device;
import com.bairock.iot.hamalib.device.Gear;
import com.bairock.iot.hamalib.device.OrderHelper;
import com.bairock.iot.hamalib.device.virtual.VirTualDevice;

/**
 * 
 * @author LinQiang
 *
 */
public class LinkageTab {

	private static LinkageTab ins = new LinkageTab();
	
	private OnOrderSendListener onOrderSendListener;
	private OnCheckTableListener onCheckTableListener;

	public static final int CHAIN = 1;
	public static final int TIMING = 2;
	public static final int LOOP = 3;

	private List<LinkageTabRow> listLinkageTabRow;
	
	private LinkageTab() {
		listLinkageTabRow = Collections.synchronizedList(new ArrayList<>());
	}

	public static LinkageTab getIns(){
		return ins;
	}

	/**
	 * get linkage table row list
	 * @return
	 */
	public List<LinkageTabRow> getListLinkageTabRow() {
		return listLinkageTabRow;
	}

	/**
	 * set linkage table row list
	 * @param listLinkageTabRow
	 */
	public void setListLinkageTabRow(List<LinkageTabRow> listLinkageTabRow) {
		if(null != listLinkageTabRow){
			this.listLinkageTabRow = listLinkageTabRow;
		}
	}
	
	public void init(){
		for(LinkageTabRow deviceChain : listLinkageTabRow){
			deviceChain.init();
		}
	}
	
	/**
	 * set linkage mark
	 * @param device device
	 * @param which which linkage CHAIN/TIMING/LOOP
	 * @param event target state,1 or 0
	 */
	public void setChain(Device device, int which, int event){
		LinkageTabRow eqTabRow = getEqLinkageTabRow(device);

		if(null != eqTabRow){
			switch (which){
				case CHAIN :
					if(eqTabRow.getChainTem() == -1){
						eqTabRow.setChainTem(event);
					}else{
						eqTabRow.setChainTem(eqTabRow.getChainTem() * event);
					}
//					eqDevice.setChain(event);
					break;
				case TIMING :
					if(eqTabRow.getTimingTem() == -1){
						eqTabRow.setTimingTem(event);
					}else{
						eqTabRow.setTimingTem(eqTabRow.getTimingTem() * event);
					}
					//eqDevice.setTiming(event);
					break;
				case LOOP :
					eqTabRow.setLoop(event);
					break;
			}
			//System.out.println("LinkageTab setChain: " + eqTabRow.getITemString());
		}else{
			LinkageTabRow tabRow = new LinkageTabRow();
			tabRow.setDevice(device);
			listLinkageTabRow.add(tabRow);
			setChain(device, which, event);
		}
	}
	
	/**
	 * add an device row to linkage table
	 * @param device
	 */
	public void addTabRow(Device device) {
		LinkageTabRow eqTabRow = getEqLinkageTabRow(device);
		if(null == eqTabRow) {
			LinkageTabRow tabRow = new LinkageTabRow();
			tabRow.setDevice(device);
			listLinkageTabRow.add(tabRow);
		}
	}
	
	/**
	 * 
	 * @param device
	 * @param which
	 * @param event
	 */
	public void setChain(Device device, int which, String event){
		if(event == null){
			setChain(device, which, -1);
		}else if(event.equals(DevStateHelper.DS_KAI)){
			setChain(device, which, 1);
		}else{
			setChain(device, which, 0);
		}
	}
	
	/**
	 * get linkage table row which device coding is equal
	 * @param device
	 * @return
	 */
	public LinkageTabRow getEqLinkageTabRow(Device device){
		LinkageTabRow eqDevice = null;
		if(null == eqDevice){
			for(LinkageTabRow tabRow : listLinkageTabRow){
				if(tabRow.getDevice().equals(device)){
					eqDevice = tabRow;
					break;
				}
			}
		}
		return eqDevice;
	}
	
	/**
	 * 
	 * @throws InterruptedException
	 */
	public void checkTabRows() throws InterruptedException{
		List<LinkageTabRow> list = new ArrayList<>(listLinkageTabRow);
		if(null != onCheckTableListener) {
			onCheckTableListener.onBeforCheck(list);
		}
		for(LinkageTabRow tabRow : list){
		    Device device = tabRow.getDevice();
		    //虚拟设备不发送报文, 计数器在Linkage-run中已经处理了
		    if(device instanceof VirTualDevice) {
		        continue;
		    }
			tabRow.analysisLinkageResult();
			if(!device.isNormal()) {
				continue;
			}
			//check if device's gear is consistent with linkage  
			if(tabRow.haveLinkage()) {
				if(device.getGear() != Gear.ZIDONG && device.getGear() != Gear.UNKNOW){
					device.setGearNeedToAuto(true);
				}else {
					device.setGearNeedToAuto(false);
				}
			}else {
				device.setGearNeedToAuto(false);
			}
			
			if(device.getGear() == Gear.ZIDONG || device.getGear() == Gear.UNKNOW){
				//zi dong
				String order = tabRow.createOrder();
				stateChangedListener(device, order, device.getCtrlModel());
				Thread.sleep(200);
			}else{
				//shou dong
				if(device.getGear() == Gear.KAI && 
						!device.getStatus().equals(DevStateHelper.DS_KAI)){
					String order = device.getDevOrder(OrderHelper.CTRL_HEAD, CtrlCodeHelper.DCT_KAIGUAN_KAI);
					stateChangedListener(device, order, device.getCtrlModel());
				}else if(device.getGear() == Gear.GUAN && 
						!device.getStatus().equals(DevStateHelper.DS_GUAN)){
					String order = device.getDevOrder(OrderHelper.CTRL_HEAD, CtrlCodeHelper.DCT_KAIGUAN_GUAN);
					stateChangedListener(device, order, device.getCtrlModel());
				}
				Thread.sleep(200);
			}
		}
		if(null != onCheckTableListener) {
			onCheckTableListener.onAfterCheck(list);
		}
	}
	
	/**
	 * register a callback to be invoked when an order need send
	 * @param onOrderSendListener the callback that will run
	 */
	public void SetOnOrderSendListener(OnOrderSendListener onOrderSendListener){
		this.onOrderSendListener = onOrderSendListener;
	}
	
	public OnCheckTableListener getOnCheckTableListener() {
		return onCheckTableListener;
	}

	public void setOnCheckTableListener(OnCheckTableListener onCheckTableListener) {
		this.onCheckTableListener = onCheckTableListener;
	}

	/**
	 * interface definition for a callback to be invoked when an order need send
	 * @author LinQiang
	 *
	 */
	public interface OnOrderSendListener{
		void onChanged(Device device, String order, CtrlModel ctrlModel);
	}
	
	public interface OnCheckTableListener{
		void onBeforCheck(List<LinkageTabRow> list);
		void onAfterCheck(List<LinkageTabRow> list);
	}
	
	private void stateChangedListener(Device device, String order, CtrlModel ctrlModel){
		if(null != order && null != onOrderSendListener){
			onOrderSendListener.onChanged(device, order, ctrlModel);
		}
//		if(ctrlModel == CtrlModel.LOCAL) {
//			//local
//			//SendMsgHelper.sendMessage(order);
//		}else{
//			//remote
//			//WebClient.getInstance().sendMsg(order);
//		}
	}
}
