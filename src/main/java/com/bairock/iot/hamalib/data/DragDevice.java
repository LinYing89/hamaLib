package com.bairock.iot.hamalib.data;

import com.bairock.iot.hamalib.device.Device;

/**
 * 可拖拽设备
 * @author 44489
 *
 */
public class DragDevice {

	private String id;
	
	/**
	 * 设备图标类型,预定义图表
	 */
	public static String IMG_ICON = "icon";
	/**
	 * 设备图标类型,自选图片
	 */
	public static String IMG_PICTURE = "picture";
	
	private Device device;
	
	private String deviceId;
	private Integer layoutx = 0;
	private Integer layouty = 0;
	//图片类型, 预定于图标还是自选图片
	private String imageType = IMG_ICON;
	//图标名称, 如果时预定于图标, 则为图标名称, 否则为图片路径
	private String imageName = "";
	
	//图标宽度
	private Integer imageWidth = 50;
	//图标高度
	private Integer imageHeight = 50;
	//图标选择角度
	private Double rotate = (double) 0;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	public Integer getLayoutx() {
		return layoutx;
	}
	public void setLayoutx(Integer layoutx) {
		this.layoutx = layoutx;
	}
	public Integer getLayouty() {
		return layouty;
	}
	public void setLayouty(Integer layouty) {
		this.layouty = layouty;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
    public Integer getImageWidth() {
        return imageWidth;
    }
    public void setImageWidth(Integer imageWidth) {
        this.imageWidth = imageWidth;
    }
    public Integer getImageHeight() {
        return imageHeight;
    }
    public void setImageHeight(Integer imageHeight) {
        this.imageHeight = imageHeight;
    }
    public Double getRotate() {
        return rotate;
    }
    public void setRotate(Double rotate) {
        this.rotate = rotate;
    }
}
