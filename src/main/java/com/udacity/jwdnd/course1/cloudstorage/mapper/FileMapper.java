package com.udacity.jwdnd.course1.cloudstorage.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.multipart.MultipartFile;

@Mapper
public interface FileMapper {
    
    @Select("SELECT * FROM FILES where filename = #{filename}")
    MultipartFile getFile(String filename);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata)" + 
    "VALUES(#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileid")
    int insertFile(MultipartFile file);
 
    @Delete("DELETE FROM FILES where filename = #{filename}")
    void deleteFile(String filename);
}