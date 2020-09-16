package com.udacity.jwdnd.course1.cloudstorage.models;

public class FileData {
    public Integer fileId;
    private String fileName;

    public FileData(Integer fileId, String fileName) {
        this.fileId = fileId;
        this.fileName = fileName;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
