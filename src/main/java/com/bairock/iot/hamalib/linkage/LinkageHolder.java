package com.bairock.iot.hamalib.linkage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.bairock.iot.hamalib.user.DevGroup;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * 
 * @author LinQiang
 *
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "class")
public class LinkageHolder {

	private String id;
	
	private boolean enable;
	
	@JsonBackReference("group_linkage_holder")
	private DevGroup devGroup;
	
//	@JsonManagedReference("lh_linkage")
	private List<Linkage> listLinkage = Collections.synchronizedList(new ArrayList<>());
	
	public LinkageHolder() {
		id = UUID.randomUUID().toString();
		listLinkage = Collections.synchronizedList(new ArrayList<>());
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * is  enable
	 * @return
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * set enable
	 * @param enable
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
	public DevGroup getDevGroup() {
		return devGroup;
	}

	public void setDevGroup(DevGroup devGroup) {
		this.devGroup = devGroup;
	}

	public List<Linkage> getListLinkage() {
		return listLinkage;
	}

	public void setListLinkage(List<Linkage> listLinkage) {
		this.listLinkage = listLinkage;
		for(Linkage l : listLinkage) {
			l.setLinkageHolder(this);
		}
	}
	
	public void addLinkage(Linkage linkage) {
		if(null == linkage){
			return;
		}
		if(!listLinkage.contains(linkage)){
			listLinkage.add(linkage);
			linkage.setLinkageHolder(this);
		}
	}
	
	/**
	 * remove an sub chain
	 * @param linkage
	 * @return
	 */
	public boolean removeLinkage(Linkage linkage){
		if(null == linkage){
			return false;
		}
		linkage.setLinkageHolder(null);
		return listLinkage.remove(linkage);
	}
	
	
	/**
	 * remove an sub chain
	 * @param index
	 * @return
	 */
	public Linkage removeSubChain(int index){
		if(index >= 0){
			Linkage lv = listLinkage.remove(index);
			if(null != lv) {
				lv.setLinkageHolder(null);
				return lv;
			}
		}
		return null;
	}
	
}
