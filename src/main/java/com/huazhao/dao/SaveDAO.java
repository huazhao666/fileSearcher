package com.huazhao.dao;

import com.huazhao.model.FileMeta;
import com.huazhao.util.DBUtil;
import com.huazhao.util.LogUtil;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

//保存扫描出的文件列表
public class SaveDAO {
    public void save(List<FileMeta> fileList){
        try {
            String sql = "INSERT INTO file_meta(name,path,is_directory,pinyin,pinyin_first" +
                    ",size,last_modified) VALUES (?,?,?,?,?,?,?)";
            Connection c = DBUtil.getConnection();
            try(PreparedStatement ps = c.prepareStatement(sql)) {
                for (FileMeta fileMeta : fileList) {
                    ps.setString(1,fileMeta.getName());
                    ps.setString(2,fileMeta.getPath());
                    ps.setBoolean(3,fileMeta.getDirectory());
                    ps.setString(4,fileMeta.getPinyin());
                    ps.setString(5,fileMeta.getPinyinFirst());
                    ps.setLong(6,fileMeta.getLength());
                    ps.setLong(7,fileMeta.getLastModified());
                    int i = ps.executeUpdate();
                    LogUtil.log("执行SQL:%s, %s,收到影响的行数是 %d",sql,fileMeta,i);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        List<FileMeta> fileList = Arrays.asList(
                new FileMeta(new File("D:\\java课件\\java板书\\EE\\20201218.png")),
                new FileMeta(new File("D:\\java课件\\java板书\\EE\\20201219.png")),
                new FileMeta(new File("D:\\java课件\\java板书\\EE\\20201226.png")),
                new FileMeta(new File("D:\\java课件\\java板书\\EE\\20210101.png"))
        );
            SaveDAO saveDAO = new SaveDAO();
            saveDAO.save(fileList);
    }
}
