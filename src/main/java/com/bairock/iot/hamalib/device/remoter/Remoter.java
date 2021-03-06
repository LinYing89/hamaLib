package com.bairock.iot.hamalib.device.remoter;

import java.util.ArrayList;
import java.util.List;

import com.bairock.iot.hamalib.device.Device;
import com.bairock.iot.hamalib.device.IStateDev;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class Remoter extends Device implements IStateDev{

	@JsonManagedReference("remoter_remoterkey")
	private List<RemoterKey> listRemoterKey = new ArrayList<>();
	
	public Remoter() {
		this("", "");
	}

	public Remoter(String mcId, String sc) {
		super(mcId, sc);
	}
	
	public List<RemoterKey> getListRemoterKey() {
		return listRemoterKey;
	}

	public void setListRemoterKey(List<RemoterKey> listRemoterKey) {
		this.listRemoterKey = listRemoterKey;
	}

	public void addRemoterKey(RemoterKey key) {
		if(null == key) {
			return;
		}
		for(RemoterKey rk : listRemoterKey) {
			if(rk.getNumber().equals(key.getNumber())) {
				return;
			}
		}
		listRemoterKey.add(key);
		key.setRemoter(this);
	}
	
	public void removeRemoterKey(RemoterKey key) {
		key.setRemoter(null);
		listRemoterKey.remove(key);
	}
	
	public void removeRemoterKeyByNumber(String keyNumber) {
		List<RemoterKey> list = new ArrayList<>(listRemoterKey);
		for(RemoterKey rk : list) {
			if(rk.getNumber().equals(keyNumber)) {
				rk.setRemoter(null);
				listRemoterKey.remove(rk);
			}
		}
	}
	
	public RemoterKey findKeyByNumber(String keyNumber) {
		for(RemoterKey rk : listRemoterKey) {
			if(rk.getNumber().equals(keyNumber)) {
				return rk;
			}
		}
		return null;
	}
	
	public String nextNumber() {
		String num = "0";
		for(int i = 0; i < 100; i++) {
			boolean haved = false;
			num = i > 9 ? String.valueOf(i) : "0" + i;
			for(RemoterKey rk : listRemoterKey) {
				if(rk.getNumber().equals(num)) {
					haved = true;
					break;
				}
			}
			if(!haved) {
				return num;
			}
		}
		return null;
	}
	
	public boolean keyNameIsExists(String name) {
		if(null == name) {
			return false;
		}
		for(RemoterKey rk : listRemoterKey) {
			if(rk.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	@Override
	@JsonIgnore
	public String getTurnOnOrder() {
		return null;
	}

	@Override
	@JsonIgnore
	public String getTurnOffOrder() {
		return null;
	}
	
}
