package com.huazhao.service;

import com.huazhao.dao.InitDAO;

//用户在搜索框中输入带查询关键字之后进行结果查询的
public class DBService {
    private final InitDAO initDAO = new InitDAO();

    public  void  init(){

        initDAO.init();
    }
}
