package com.huazhao.model;

import com.huazhao.util.OutputUtil;
import com.huazhao.util.PinYinUtil;

import java.io.File;
import java.util.Objects;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-01-24
 * Time : 15:10
 */
public class FileMeta {
    private final Integer id;
    private final String name;
    private final String pinyin;
    private final String pinyinFirst;
    private final String path;
    private final Boolean directory;
    private final Long length;
    private final Long lastModifiedTimeStamp;



    //提供给数据库使用
    public FileMeta(Integer id, String name,String pinyin, String pinyinFirst, String path, Boolean directory,
                    Long length, Long lastModifiedTimeStamp) {
        this.id = id;
        this.name = name;
        this.pinyin = pinyin;
        this.pinyinFirst = pinyinFirst;
        this.path = path;
        this.directory = directory;
        this.length = length;
        this.lastModifiedTimeStamp = lastModifiedTimeStamp;
    }
    //扫描使用
    public FileMeta(File file){
        this.id = null;
        this.name = file.getName();
        this.pinyin = PinYinUtil.getPinYin(name);
        this.pinyinFirst = PinYinUtil.getPinYinFirst(name);
        this.path = file.getParent();
        this.directory = file.isDirectory();
        this.length = file.length();
        this.lastModifiedTimeStamp = file.lastModified();

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPinyin() {

        return pinyin;
    }
    public String getPinyinFirst() {
        return pinyinFirst;
    }

    public String getPath() {
        return path;
    }

    public Boolean getDirectory() {

        return directory;
    }

    public Long getLength() {

        return length;
    }

    public  String getLengthUI(){

        return OutputUtil.formatLength(length);
    }

    public long getLastModified() {

        return lastModifiedTimeStamp;
    }

    public String getLastModifiedUI() {

        return OutputUtil.formatTimestamp(lastModifiedTimeStamp);
    }
    @Override
    public String toString() {
        return "FileMeta{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", pinyinFirst='" + pinyinFirst + '\'' +
                ", path='" + path + '\'' +
                ", directory=" + directory +
                ", length=" + length +
                ", lastModifiedTimestamp=" + lastModifiedTimeStamp +
                '}';
    }
    //只要路径一样，就默认相同；
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileMeta fileMeta = (FileMeta) o;
        return path.equals(fileMeta.path);
    }

    @Override
    public int hashCode() {

        return Objects.hash(path);
    }
}
