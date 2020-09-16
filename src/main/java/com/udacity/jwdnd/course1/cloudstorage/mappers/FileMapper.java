package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.FileData;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE userid=#{userId}")
    public List<File> getFiles(Integer userId);

    @Insert("INSERT INTO FILES ( filename, contenttype, filesize, userid, filedata) " +
            "VALUES( #{fileName}, #{contentType}, #{fileSize}, #{userId}, #{file})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileId}")
    void delete(Integer fileId);

    @Update("UPDATE FILES SET filename=#{fileName}, contenttype=#{contentType}, filesize=#{fileSize}, filedata=#{file}, WHERE fileid=#{fileId}")
    void update(File file);

    @Select("SELECT * FROM FILES WHERE fileid=#{fileId}")
    File getFile(Integer fileId);

    @Select("SELECT COUNT(fileId) FROM FILES where userid=#{userId}")
    public int getFilesCount(Integer userId);

    @Select("SELECT fileid,fileName from FILES where userid=#{userId}")
    public List<FileData> getFileList(Integer userId);
}
