package com.huazhao.dao;

import com.huazhao.util.DBUtil;
import com.huazhao.util.LogUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


public class DeleteDAO {
    public void delete(List<Integer> idList) {
        try {
            Connection c = DBUtil.getConnection();//此时一个线程只有一个connection对象，不需要关闭
            //jdk8的stream的用发
            List<String> placeholderList = idList.stream()
                    .map(id -> "?")
                    .collect(Collectors.toList());
            //等同于以上操作
            /*List<String> placeholderList  = new ArrayList<>();
            for(Integer id: idList){
                placeholderList.add("?");
            }*/
            String placeHolder = String.join(",", placeholderList);
            String sql = String.format("DELETE FROM file_meta WHERE id IN (%s)", placeHolder);
            try (PreparedStatement ps = c.prepareStatement(sql)) {
                for (int i = 0; i < idList.size(); i++) {
                    int id = idList.get(i);
                    ps.setInt(i + 1, id);
                }
                int i = ps.executeUpdate();
                LogUtil.log("执行 SQL: %s, %s, 收到影响的行数是: %d", sql, idList, i);
            }
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }

//    public static void main(String[] args) throws SQLException {
//        List<Integer> idList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
//        DeleteDAO deleteDAO = new DeleteDAO();
//        deleteDAO.delete(idList);
//    }
}
