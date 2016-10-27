package com.zslin.basic.tools;

import javax.servlet.http.HttpServletRequest;

/**
 * 防重复提交的工具类
 * @author zslin.com 20160520
 *
 */
public class TokenTools {
	public static final String REP_SESSION_NAME = "isRepeat";

	/** 
	 * 检查是否为重复提交
	 *  - 返回true表示没有重复提交
	 * @param request
	 * @return
	 */
	public static boolean isNoRepeat(HttpServletRequest request) {
		Boolean isRepeat = (Boolean) request.getSession().getAttribute(REP_SESSION_NAME);
		return (isRepeat==null || !isRepeat);
	}
}
