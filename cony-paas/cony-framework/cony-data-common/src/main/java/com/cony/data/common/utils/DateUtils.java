package com.cony.data.common.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 */
public class DateUtils {


    //相差天数的0点时间
    public static Date getDayBeginOfDiffer(int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DATE, i);
        return cal.getTime();
    }

    //相差天数的24点时间
    public static Date getDayEndOfDiffer(int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DATE, i);
        return cal.getTime();
    }


    // 获得当天0点时间
    public static Date getDayBegin() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();


    }

    // 获得当天24点时间
    public static Date getDayEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


    // 获得本周一0点时间
    public static Date getDayBeginOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    // 获得本周日24点时间
    public static Date getDayEndOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDayBeginOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 7);
        return cal.getTime();
    }


    // 获得本月第一天0点时间
    public static Date getDayBeginOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    // 获得本月最后一天24点时间
    public static Date getDayEndOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return cal.getTime();
    }

}