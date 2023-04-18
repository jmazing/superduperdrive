package com.udacity.jwdnd.course1.cloudstorage.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.model.UserNote;

@Mapper
public interface NoteMapper {
    
    @Select("SELECT * FROM NOTES notetitle = #{notetitle}")
    UserNote getNote(String notetitle);

    @Insert("INSERT INTO NOTES (notetitle, notedescriptions, userid)" + 
    "VALUES(#{notetitle}, #{notedescriptions}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int insertNote(UserNote note);

    @Delete("DELETE FROM NOTES where notetitle = #{notetitle}")
    void deleteNote(String notetitle);
    
}
