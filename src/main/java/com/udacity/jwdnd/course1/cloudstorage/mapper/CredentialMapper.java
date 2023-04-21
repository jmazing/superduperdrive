package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.model.UserCredential;

@Mapper
public interface CredentialMapper {
    
    @Select("SELECT * FROM CREDENTIALS where credentialid = #{credentialid}")
    UserCredential getCredential(Integer credentialid);

    @Select("SELECT * FROM CREDENTIALS where userid = #{userid}")
    List<UserCredential> getCredentials(Integer userid);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid)" + 
    "VALUES(#{url}, #{username}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int insertCredential(UserCredential credential);

    @Delete("DELETE FROM CREDENTIALS where credentialid = #{credentialid}")
    void deleteCredential(Integer credentialid);
    
}
