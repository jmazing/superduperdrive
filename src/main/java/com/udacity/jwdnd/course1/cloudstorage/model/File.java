package com.udacity.jwdnd.course1.cloudstorage.model;

import org.springframework.web.multipart.MultipartFile;

public class File {
    
    private int fileId;
    private String filename;
    private String contenttype;
    private String filesize;
    private int userid;
    private MultipartFile filedata;
    
    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getContenttype() {
        return contenttype;
    }

    public MultipartFile getFiledata() {
        return filedata;
    }

    public void setFiledata(MultipartFile filedata) {
        this.filedata = filedata;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
