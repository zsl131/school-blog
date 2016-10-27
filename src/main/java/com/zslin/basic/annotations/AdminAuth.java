package com.zslin.basic.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 后台管理的Annotation对象
 * @author zslin.com 20160513
 *
 */
//RetentionPolicy.RUNTIME 表示运行时保留
@Retention(RetentionPolicy.RUNTIME)
public @interface AdminAuth {

	/** 资源名称 */
	public String sn() default "";
	/** 父节点SN */
	public String psn() default "";
	/** 资源图标，使用bootstrap */
	public String icon() default "glyphicon glyphicon-tags";
	/** 是否显示 */
	public int display() default 1;
	/** 连接地址 */
	public String url() default "#";
	/** 类型，1：导航菜单；2：权限菜单 */
	public String type() default "2";
	/** 资源名称 */
	public String name();
	/** 序号 */
	public int orderNum() ;
	
	/** 父节点是否是实体，1：是；其他：否，如果不是实体则需要检测是否存在，不存在则添加 */
	public int pentity() default 1;
	
	/** 父节点的序号 */
	public int porderNum() default 1;
}
