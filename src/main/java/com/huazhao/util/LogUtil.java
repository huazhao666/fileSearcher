package com.huazhao.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-01-24
 * Time : 16:49
 */
public class LogUtil {
    //类型可变参数列表
    //object代表任意长度任意类型；
    public static void log(String format,Object... args){
        String message = String.format(format, args);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String now = dateFormat.format(date);
        System.out.printf("%s: %s\n",now,message);
    }

    public static void main(String[] args) {
        log("你好");
        log("hello",22);
    }
}
