package com.huazhao.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-01-24
 * Time : 15:09
 */
public class OutputUtil {
    public static String formatLength(Long length) {
        if(length < 1024){
            return length + "Byte";
        }
        if(length < 1024 * 1024){
            return (length / 1024) + "KB";
        }
        if(length < 1024 * 1024 * 1024){
            return (length / 1024 / 1024) + "MB";
        }
        return (length / 1024 / 1024 / 1024) + "GB";
    }
    public static String formatTimestamp(Long timestamp){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date date = new Date(timestamp);
        return dateFormat.format(date);
    }

    public static void main(String[] args) {
        /*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       // format.setTimeZone(TimeZone.getTimeZone("GMT + 8:00"));
        Date date = new Date();
        System.out.println(format.format(date));
        System.out.println(format1.format(date));*/
        DecimalFormat format = new DecimalFormat("#.0");
        double x = 100.040004;
        System.out.println(format.format(x));

    }
}
