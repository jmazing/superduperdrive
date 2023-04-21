package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.model.UserFile;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES where userid = #{userid}")
    List<UserFile> getFiles(Integer userId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata)" + 
    "VALUES(#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(UserFile userFile);
 
    @Select("SELECT * FROM FILES where fileId = #{fileId}")
    UserFile getFile(Integer fileId);

    @Delete("DELETE FROM FILES where fileId = #{fileId}")
    int deleteFile(Integer fileId);
}
