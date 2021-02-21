package com.huazhao.util;

import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;


//使用单例 --- 懒汉模式
public class DBUtil {
    public static volatile DataSource dataSource = null;

    private static String getDatabasePath(){
        try {
            String classPath = DBUtil.class.getProtectionDomain()
                    .getCodeSource().getLocation().getFile();
            File classDir = new File(URLDecoder.decode(classPath,"UTF-8"));
            String path = classDir.getParent() + File.separator + "searcher.db";
            LogUtil.log("数据库文件路径为: %s",path);
            return path;
        }catch (UnsupportedEncodingException e){
            throw new RuntimeException(e);
        }
    }
    private static void initDataSource() {
        SQLiteDataSource sqLiteDataSource = new SQLiteDataSource();
        String url = "jdbc:sqlite://"+ getDatabasePath();
        sqLiteDataSource.setUrl(url);
        dataSource = sqLiteDataSource;
    }
    public static Connection initConnection() {
        if(dataSource == null){
            synchronized (DBUtil.class){
                if(dataSource == null){
                    initDataSource();
                }
            }
        }
        //connection 对象线程不安全，每个线程必须要有自己的connection对象；
        //使用ThreadLocal实现，每个线程有自己的connection对象;
        try {
            return dataSource.getConnection();
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }

    }
    /*private static final ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>(){
        @Override
        protected Connection  initialValue() {
            return initConnection();
        }
    };*/
    //兰姆达
    private static final ThreadLocal<Connection> connectionThreadLocal =
            ThreadLocal.withInitial(DBUtil::initConnection);

    public static Connection getConnection(){

        return connectionThreadLocal.get();
    }
}
