package com.udacity.jwdnd.course1.cloudstorage.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;

@Mapper
public interface CredentialMapper {
    
    @Select("SELECT * FROM CREDENTIALS where url = #{url}")
    Credential getCredential(String url);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid)" + 
    "VALUES(#{url}, #{username}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int insertCredential(Credential credential);

    @Delete("DELETE FROM CREDENTIALS where url = #{url}")
    void deleteCredential(String url);
    
}
