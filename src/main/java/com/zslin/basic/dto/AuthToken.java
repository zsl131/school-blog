package com.zslin.basic.dto;

import com.zslin.basic.model.User;

import java.util.Date;
import java.util.List;

/**
 * 登陆令牌对象DTO
 * @author zslin.com 20160514
 *
 */
public class AuthToken {

	//登陆放在Session中的名称
	public static final String SESSION_NAME = "LOGIN_USER";
	
	//登陆用户
	private User user;
	
	//用户权限范围内的菜单
//	private Map<Menu, Map<Menu, List<Menu>>> authMenu;
	private List<MenuDto> authMenu;
	
	//用户权限范围内的sn列表
	private List<String> authList;

	//登录时间 
	private Date login_time;
	
	//登录IP 
	private String login_ip;

	public List<String> getAuthList() {
		return authList;
	}

	public void setAuthList(List<String> authList) {
		this.authList = authList;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<MenuDto> getAuthMenu() {
		return authMenu;
	}

	public void setAuthMenu(List<MenuDto> authMenu) {
		this.authMenu = authMenu;
	}

	public Date getLogin_time() {
		return login_time;
	}

	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}

	public String getLogin_ip() {
		return login_ip;
	}

	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}
}
