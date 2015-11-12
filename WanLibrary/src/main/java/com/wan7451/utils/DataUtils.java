package com.wan7451.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2015/11/6.
 */
public class DataUtils {

    public static String getAge(Date dateOfBirth) {
        int age = 0;
        int month = 0;
        Calendar born = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        if (dateOfBirth != null) {
            now.setTime(new Date());
            born.setTime(dateOfBirth);
            if (born.after(now)) {
                return "生日有错误，请修改";
            }
            age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
            month = now.get(Calendar.MONTH) - born.get(Calendar.MONTH);
            if (now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR)) {
                age -= 1;
            }
            if (age < 2) {
                if (age == 0) {
                    return month + "个月";
                }
                return age + "岁" + month + "个月";
            } else {
                return age + "岁";
            }
        }
        return "";
    }


    public static String getAge(String dateOfBirth) {
        return getAge(StrToDate(dateOfBirth));
    }

    /**
     * 日期转换成字符串
     *
     * @param date
     * @return str
     */
    public static String DateToStr(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    /**
     * 字符串转换成日期
     *
     * @param str
     * @return date
     */
    public static Date StrToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
