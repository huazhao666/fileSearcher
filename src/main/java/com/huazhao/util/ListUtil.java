package com.huazhao.util;

import com.huazhao.model.FileMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//将list1中含有的，而list2中没有添加进去；
public class ListUtil {

    public static <E> List<E> differenceSet(List<E> list1, List<E> list2) {
        List<E> resultList = new ArrayList<>();
        for(E item : list1){
            if(!list2.contains(item)){ //必须支持equals;
                resultList.add(item);
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        List<FileMeta> fileList1 = Arrays.asList(
                new FileMeta(new File("D:\\java课件\\java板书\\EE\\20201218.png")),
                new FileMeta(new File("D:\\java课件\\java板书\\EE\\20201219.png")),
                new FileMeta(new File("D:\\java课件\\java板书\\EE\\20201226.png"))

        );
        List<FileMeta> fileList2 = Arrays.asList(
                new FileMeta(new File("D:\\java课件\\java板书\\EE\\20201219.png")),
                new FileMeta(new File("D:\\java课件\\java板书\\EE\\20201226.png")),
                new FileMeta(new File("D:\\java课件\\java板书\\EE\\20210101.png"))
        );
        System.out.println(differenceSet(fileList1,fileList2));
        System.out.println(differenceSet(fileList2,fileList1));
    }
}

