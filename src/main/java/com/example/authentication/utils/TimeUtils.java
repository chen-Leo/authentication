package com.example.authentication.utils;

import java.sql.Timestamp;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 时间戳工具类
 *
 * @author hello
 */
public class TimeUtils {


    public static Integer getTimestamp(Date date) {
        return dateToTimestamp(date);
    }

    public static String getDateString(Date date) {
        return dateToString(date);
    }

    public static Date getStringDate(Integer time) {
        return stringToDate(timestampToString(time));
    }

    /**
     * String(yyyy-MM-dd HH:mm:ss) 转 Date
     */
    private static Date stringToDate(String time) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = dateFormat.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Date转为String(yyyy-MM-dd HH:mm:ss)
     */
    private static String dateToString(Date time) {
        String dateStr = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            dateStr = dateFormat.format(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * 10位int型的时间戳转换为String(yyyy-MM-dd HH:mm:ss)
     */
    private static String timestampToString(Integer time) {
        long temp = (long) time * 1000;
        Timestamp ts = new Timestamp(temp);
        String tsStr = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            tsStr = dateFormat.format(ts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tsStr;
    }

    /**
     * Date类型转换为10位时间戳
     */
    private static Integer dateToTimestamp(Date time) {
        Timestamp ts = new Timestamp(time.getTime());
        return (int) ((ts.getTime()) / 1000);
    }
}
