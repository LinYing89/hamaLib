package com.bairock.iot.hamalib.device.virtual;

import com.bairock.iot.hamalib.device.IValueDevice;

/**
 * virtual device interface
 * @author 44489
 *
 */
public interface VirTualDevice extends IValueDevice{
    String getValue();
    void setValue(String value);
}
