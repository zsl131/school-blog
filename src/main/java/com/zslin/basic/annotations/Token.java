package com.zslin.basic.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 防止重复提示导致数据重复录入
 * - 主要用于数据添加
 * - 创建标记Annotation
 * @author zslin.com 20160513
 *
 */
@Target(ElementType.METHOD) //指定该注释只能应用于方法名上
@Retention(RetentionPolicy.RUNTIME) //指定运行时保留
public @interface Token {

	/** 1：为添加数据前 */
	public static final String READY = "1";
	
	/** 2：为添加数据时，需要进行验证 */
	public static final String CHECK = "2";
	
	/** 指定验证标记，如果为1：保存防重标记； 2：检查是否为重复提交 */
	public String flag();
}
