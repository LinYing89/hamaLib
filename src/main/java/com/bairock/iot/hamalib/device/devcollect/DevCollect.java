package com.bairock.iot.hamalib.device.devcollect;

import com.bairock.iot.hamalib.device.CtrlCodeHelper;
import com.bairock.iot.hamalib.device.DevStateHelper;
import com.bairock.iot.hamalib.device.Device;
import com.bairock.iot.hamalib.device.IValueDevice;
import com.bairock.iot.hamalib.device.MainCodeHelper;
import com.bairock.iot.hamalib.device.OrderHelper;
import com.bairock.iot.hamalib.user.IntelDevHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * collect device
 * @author LinQiang
 *
 */
public class DevCollect extends Device implements IValueDevice{

	@JsonManagedReference("devcollect_property")
	private CollectProperty collectProperty;
	
	@JsonIgnore
	private CalibrationnListener calibrationnListener;
	
	/**
	 * 
	 */
	public DevCollect() {
		this(MainCodeHelper.SMC_WU, "");
	}

	/**
	 * 
	 * @param mcId
	 * @param sc
	 */
	public DevCollect(String mcId, String sc) {
		super(mcId, sc);
		collectProperty  = new CollectProperty();
		collectProperty.setDevCollect(this);
		collectProperty.setDevCollectId(this.getId());
	}
	
	public CollectProperty getCollectProperty() {
		if(null == collectProperty) {
			collectProperty = new CollectProperty();
			collectProperty.setDevCollect(this);
			collectProperty.setDevCollectId(this.getId());
		}
		return collectProperty;
	}

	public void setCollectProperty(CollectProperty collectProperty) {
		if(null != collectProperty){
			this.collectProperty = collectProperty;
			this.collectProperty.setDevCollect(this);
			collectProperty.setDevCollectId(this.getId());
		}
	}
	
	public CalibrationnListener getCalibrationnListener() {
		return calibrationnListener;
	}

	public void setCalibrationnListener(CalibrationnListener calibrationnListener) {
		this.calibrationnListener = calibrationnListener;
	}

	@Override
	public void setStatus(String ds) {
		super.setStatus(ds);
		if(ds.equals(DevStateHelper.DS_YI_CHANG)) {
			getCollectProperty().setCurrentValueExceptListener(null);
		}
	}

	protected void setRatio() {
		CollectProperty cp = getCollectProperty();
		if (null == cp.getCrestValue() || null == cp.getLeastValue() || null == cp.getPercent()) {
			return;
		}
		float ratio = cp.getPercent() / 100;
		ratio = ratio * (cp.getCrestReferValue() - cp.getLeastReferValue()) + cp.getLeastValue();
		cp.setCurrentValue(IntelDevHelper.scale(ratio));
	}
	
	@Override
	public String createQueryOrder() {
		return OrderHelper.getOrderMsg(OrderHelper.QUERY_HEAD + getCoding() + OrderHelper.SEPARATOR + "8");
	}
	
	/**
	 * 创建标定报文, 精度0.01, 传入参数为原始数据, 方法内会乘以100
	 * @param order
	 * @return
	 */
	public String createCalibrationOrder(float order) {
		int iOrder = (int) (order * 100);
		String strHex = Integer.toHexString(iOrder);
		return OrderHelper.getOrderMsg(OrderHelper.SET_HEAD + getCoding() + OrderHelper.SEPARATOR + "B" + strHex);
	}
	
	@Override
	public String createInitOrder() {
		return createQueryOrder();
	}

	public String createPrecentOrder() {
		String order = OrderHelper.FEEDBACK_HEAD + getCoding() + OrderHelper.SEPARATOR 
				+ CtrlCodeHelper.getIns().getDct(CtrlCodeHelper.DCT_PRESSURE_PER_VALUE)
				+ collectProperty.getPercent();
		return OrderHelper.getOrderMsg(order);
	}
	
	@Override
	public void handleSingleMsg(String state) {
		if (null != state) {
			if (state.length() < 2) {
				return;
			}
			if(state.startsWith("C")) {
				//标定返回
				if(null != calibrationnListener) {
					calibrationnListener.calibration(true);
				}
			}
		}
	}
	
	/**
	 * 标定监听器
	 * @author 44489
	 *
	 */
	public interface CalibrationnListener{
		/**
		 * 标定结果返回
		 * @param result true为成功, false为失败
		 */
		void calibration(boolean result);
	}
}
