package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE userid=#{userId}")
    public List<Credential> getCredentials(Integer userId);

    @Insert("INSERT INTO CREDENTIALS ( url, username, key, password, userid) " +
            "VALUES( #{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    void delete(Integer credentialId);

    @Update("UPDATE CREDENTIALS SET url=#{url}, username=#{username}, password=#{password}, key=#{key} WHERE credentialid=#{credentialId}")
    void update(Credential credential);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid=#{credentialId}")
    Credential getCredential(Integer credentialId);

    @Select("SELECT userid FROM CREDENTIALS WHERE credentialid=#{credentialId}")
    public Integer getUserIdForCredential(Integer credentialId);
}
