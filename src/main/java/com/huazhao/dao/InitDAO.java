package com.huazhao.dao;

import com.huazhao.util.DBUtil;
import com.huazhao.util.LogUtil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Scanner;


public class InitDAO {
    //找到init.sql文件，并且读取其内容；
    //得到一组String[] sql;
    //是一组以分号分割的多个sql语句；
    private String[] getSQLs(){
        try (InputStream is = InitDAO.class.getClassLoader().getResourceAsStream("init.sql")){
            StringBuffer sb = new StringBuffer();
            Scanner sc = new Scanner(is,"UTF-8");
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                sb.append(line);
            }
            return sb.toString().split(";");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public void init(){
        try {
            Connection c = DBUtil.getConnection();
            String[] sqls = getSQLs();
            for (String sql : sqls) {
                LogUtil.log("执行SQL: " + sql);
                try (PreparedStatement ps = c.prepareStatement(sql)) {
                    if(sql.toUpperCase().startsWith("SELECT ")){
                        try(ResultSet rs = ps.executeQuery()){
                            ResultSetMetaData metaData = rs.getMetaData();
                            int columnCount = metaData.getColumnCount();
                            int rowCount = 0;
                            while (rs.next()){
                                StringBuffer message = new StringBuffer("|");
                                for (int i = 1; i <= columnCount ; i++) {
                                    String value = rs.getString(i);
                                    message.append(value).append("|");
                                }
                                LogUtil.log(message.toString());
                                rowCount++;
                            }
                            LogUtil.log("查询出 %d 行",rowCount);
                        }

                    }else {
                        int i = ps.executeUpdate();
                        LogUtil.log("收到影响的行一共有 %d 行: ", i);
                    }
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        InitDAO dao = new InitDAO();
        dao.init();
    }

}
