package com.bairock.iot.hamalib.device.virtual;

import java.util.HashSet;
import java.util.Set;

import com.bairock.iot.hamalib.device.DevStateHelper;
import com.bairock.iot.hamalib.device.Device;
import com.bairock.iot.hamalib.device.MainCodeHelper;
import com.bairock.iot.hamalib.user.Util;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 参数设备
 * @author 44489
 * @version 2019年9月23日下午7:06:56
 */
public class DevParam extends Device implements VirTualDevice{

    @JsonIgnore
    private Set<OnValueChangedListener> setOnValueChanged = new HashSet<>();
    
    public DevParam() {
        this(MainCodeHelper.VT_PARAM, "");
    }

    public DevParam(String mcId, String sc) {
        super(mcId, sc);
    }
    
    @Override
    public void setStatus(String ds) {
        return;
    }

    @Override
    public boolean isNormal() {
        return true;
    }

    @Override
    public String getStatus() {
        return DevStateHelper.DS_ZHENG_CHANG;
    }

    @Override
    public String getValue() {
        if(this.value == null) {
            this.value = "";
        }
        return value;
    }

    @Override
    public void setValue(String value) {
        if(null == value) {
            value = "";
        }
        if(this.value == null) {
            this.value = "";
        }
        if(!this.value.equals(value)) {
            this.value = value;
            for(OnValueChangedListener onValueChanged : setOnValueChanged) {
                onValueChanged.onValueChanged(this, value);
            }
        }
    }
    
    @Override
    public void handleSingleMsg(String singleMsg) {
        if (null == singleMsg || singleMsg.isEmpty()) {
            return;
        }
        if(singleMsg.startsWith("8")) {
            try {
                String strState = singleMsg.substring(1);
                int iValue = Integer.parseInt(strState, 16);
                double srcValue = iValue / 100f;
                String strValue = Util.format2TwoScale(srcValue);
                setValue(strValue);
            } catch (Exception e) {
                return;
            }
        }
    }

    public Set<OnValueChangedListener> getSetOnValueChanged() {
        return setOnValueChanged;
    }

    public void setSetOnValueChanged(Set<OnValueChangedListener> setOnValueChanged) {
        this.setOnValueChanged = setOnValueChanged;
    }

    public void addOnValueChangedListener(OnValueChangedListener listener) {
        if(null != listener && !setOnValueChanged.contains(listener)) {
            setOnValueChanged.add(listener);
        }
    }
    
    public void removeOnValueChangedListener(OnValueChangedListener listener) {
        if(null != listener) {
            setOnValueChanged.remove(listener);
        }
    }
    
    public interface OnValueChangedListener {
        void onValueChanged(DevParam dev, String value);
    }
    
}
