package com.udacity.jwdnd.course1.cloudstorage.models;

import org.springframework.web.multipart.MultipartFile;

public class File {
    public Integer fileId;
    private String fileName;
    private String contentType;
    private String fileSize;
    private Integer userId;
    private byte[] filedata;

    public File(Integer fileId,String fileName,String contentType, String fileSize,Integer userId,byte[] filedata) {
        this.fileId = fileId;
        this.filedata = filedata;
        this.userId = userId;
        this.fileName=fileName;
        this.contentType=contentType;
        this.fileSize=fileSize;
    }

    public File() {

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public byte[] getFile() {
        return filedata;
    }

    public void setFile(byte[] filedata) {
        this.filedata = filedata;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
