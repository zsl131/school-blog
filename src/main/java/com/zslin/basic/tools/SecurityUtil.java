package com.zslin.basic.tools;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 安全工具类，可以进行数据的加密和解密，并且可以完成对象的加密和解密
 * @author KongHao
 *
 */
@SuppressWarnings("rawtypes")
public class SecurityUtil {
	public static String md5(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		return new BigInteger(1,md.digest()).toString(16);
	}
	
	public static String md5(String username,String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(username.getBytes());
		md.update(password.getBytes());
		return new BigInteger(1,md.digest()).toString(16);
	}
}
