package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.FileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileMapper fileMapper;

    @Override
    public void save(MultipartFile file, Integer userId) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileSize=""+file.getSize();
        String contentType=file.getContentType();
        File newFile=new File(null,fileName,contentType,fileSize,userId,file.getBytes());
        System.out.println(newFile.getFileId()+" "+newFile.getFileName()+" "+newFile.getContentType()+" "+newFile.getFileSize()+" "+newFile.getUserId());
        int id=fileMapper.insert(newFile);
        System.out.println("After insertion fileId"+id);
    }

    @Override
    public int getFilesCount(Integer userId)
    {
        return fileMapper.getFilesCount(userId);
    }

    @Override
    public List<FileData> getFileList(Integer userId) {
        return fileMapper.getFileList(userId);
    }

    @Override
    public File getFile(Integer fileId) {
        return  fileMapper.getFile(fileId);

    }
}
