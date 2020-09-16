package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.FileData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileService {
    public void save(MultipartFile file,Integer userId) throws IOException;

    //void save(MultipartFile file);
    public int getFilesCount(Integer userId);
    public List<FileData> getFileList(Integer userId);
    File getFile(Integer fileId);
}
