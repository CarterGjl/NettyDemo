package com.example.user.nettydemo.test;

/**
 * 加工上传成功服务器返回的数据、
 */
public class FileInfo {


    private Integer fileSize;

    private String rid;


    public Integer getFileSize() {

        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getRid() {


        return rid == null ? "" : rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }
}
