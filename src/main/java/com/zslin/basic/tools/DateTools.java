package com.zslin.basic.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * Created by zsl-pc on 2016/9/21.
 */
public class DateTools {

    public static Date plusDay(Integer days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    /**
     *
     * @param days
     * @param pattern
     * @return
     */
    public static String plusDay(Integer days, String pattern) {
        Date date = plusDay(days);
        if(pattern==null || "".equalsIgnoreCase(pattern)) {pattern = "yyyy-MM-dd";}
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Long plusDayByLong(Integer days) {
        Date date = plusDay(days);
        return date.getTime();
    }

    /**
     * 判断是否逾期
     * @param startDate 开始日期
     * @param amount 出租天数
     * @return
     */
    public static boolean isOverdue(Date startDate, Integer amount) {
        Calendar needCal = Calendar.getInstance();
        needCal.setTime(startDate);
        needCal.add(Calendar.DAY_OF_MONTH, amount);

        Calendar nowCal = Calendar.getInstance();

        return !needCal.getTime().after(nowCal.getTime());
    }

    public static String date2Str(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String date2Str(Date date) {
        return date2Str(date, "yyyy-MM-dd");
    }

    public static String formatDate(Date date) {
        return date2Str(date, "yyyy-MM-dd HH:mm:ss") ;
    }
}
