package com.zslin.web.tools;

/**
 * 随机工具类
 * Created by 钟述林 393156105@qq.com on 2016/10/30 11:41.
 */
public class RandomTools {

    /** 随机6位数 */
    public static String randomCode() {
        Integer res = (int)(Math.random()*1000000);
        return res+"";
    }
}
