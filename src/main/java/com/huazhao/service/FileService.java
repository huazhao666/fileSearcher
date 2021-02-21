package com.huazhao.service;

import com.huazhao.dao.DeleteDAO;
import com.huazhao.dao.QueryDAO;
import com.huazhao.dao.SaveDAO;
import com.huazhao.model.FileMeta;
import com.huazhao.util.ListUtil;

import java.util.List;
import java.util.stream.Collectors;


//主要提供给文件扫描时，处理结果扫描和数据库中结果差时使用
public class FileService {
    private final SaveDAO saveDAO = new SaveDAO();
    private final DeleteDAO deleteDAO = new DeleteDAO();
    private final QueryDAO queryDAO = new QueryDAO();

    private void saveFileList (List<FileMeta> fileList){
        saveDAO.save(fileList);
    }
    private void deleteFileList (List<FileMeta> fileList) {
        List<Integer> idList = fileList.stream().map(FileMeta::getId).collect(Collectors.toList());
        deleteDAO.delete(idList);
    }
    public void service(String path,List<FileMeta> scanResultList){
        List<FileMeta> queryResultList = queryDAO.queryByPath(path);

        //删除数据库存在的，但是扫描结果不存在的
        //queryResultList - scanResultList;
        List<FileMeta> ds = ListUtil.differenceSet(queryResultList,scanResultList);
        deleteFileList(ds);
        //添加数据库没有的，但是扫描结果存在的
        List<FileMeta> ss = ListUtil.differenceSet(scanResultList,queryResultList);
        saveFileList(ss);
    }
    public List<FileMeta> queryName(String keyword){

        return queryDAO.query(keyword);
    }
}

