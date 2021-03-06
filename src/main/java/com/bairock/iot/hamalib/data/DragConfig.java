package com.bairock.iot.hamalib.data;

public class DragConfig {

    // 组id
    private String devGroupId;
    // 组态页面背景图路径
    private String dragViewBackgroundImagePath = "";
    // 组态视图背景图的宽度和高度
    private int dragBackgroundWidth = 300;
    private int dragBackgroundHeight = 300;
    // 是否显示组态视图中设备的名称
    private boolean showDeviceName = true;
    // 是否显示组态视图中设备的图标
    private boolean showDeviceIcon = true;

    public String getDevGroupId() {
        return devGroupId;
    }

    public void setDevGroupId(String devGroupId) {
        this.devGroupId = devGroupId;
    }

    public String getDragViewBackgroundImagePath() {
        return dragViewBackgroundImagePath;
    }

    public void setDragViewBackgroundImagePath(String dragViewBackgroundImagePath) {
        this.dragViewBackgroundImagePath = dragViewBackgroundImagePath;
    }

    public int getDragBackgroundWidth() {
        return dragBackgroundWidth;
    }

    public void setDragBackgroundWidth(int dragBackgroundWidth) {
        this.dragBackgroundWidth = dragBackgroundWidth;
    }

    public int getDragBackgroundHeight() {
        return dragBackgroundHeight;
    }

    public void setDragBackgroundHeight(int dragBackgroundHeight) {
        this.dragBackgroundHeight = dragBackgroundHeight;
    }

    public boolean isShowDeviceName() {
        return showDeviceName;
    }

    public void setShowDeviceName(boolean showDeviceName) {
        this.showDeviceName = showDeviceName;
    }

    public boolean isShowDeviceIcon() {
        return showDeviceIcon;
    }

    public void setShowDeviceIcon(boolean showDeviceIcon) {
        this.showDeviceIcon = showDeviceIcon;
    }
}
