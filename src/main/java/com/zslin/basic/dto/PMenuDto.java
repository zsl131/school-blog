package com.zslin.basic.dto;

import com.zslin.basic.model.Menu;

import java.util.List;

/**
 * 父级菜单DTO对象
 * 
 * @author zslin.com 20160514
 *
 */
public class PMenuDto implements Comparable<PMenuDto> {

	/**
	 * 父节点Id
	 */
	private Integer pid;
	
	/**
	 * 父菜单节点
	 */
	private Menu pm;
	
	/** 对应的子菜单 */
	private List<MenuDto> children;

	/**
	 * 父菜单节点
	 */
	public Menu getPm() {
		return pm;
	}

	/**
	 * 父菜单节点
	 */
	public void setPm(Menu pm) {
		this.pm = pm;
	}

	/** 对应的子菜单 */
	public List<MenuDto> getChildren() {
		return children;
	}

	/** 对应的子菜单 */
	public void setChildren(List<MenuDto> children) {
		this.children = children;
	}
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	@Override
	public boolean equals(Object obj) {
		PMenuDto md = (PMenuDto)obj;
		return md.getPid()==this.getPid();
	}
	
	public int compareTo(PMenuDto o) {
		int tp = this.pm.getOrderNum();
		int op = o.getPm().getOrderNum();
		
		return tp>op?1:(tp==op?0:-1);
	}
	
	public PMenuDto(Integer pid, Menu pm, List<MenuDto> children) {
		this.pid = pid; this.pm = pm; this.children = children;
	}
}
