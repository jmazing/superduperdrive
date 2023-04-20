package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.model.UserNote;

@Mapper
public interface NoteMapper {
    
    @Select("SELECT * FROM NOTES noteid = #{noteid}")
    UserNote getNote(Integer noteid);

    @Select("SELECT * FROM NOTES where userid = #{userid}")
    List<UserNote> getNotes(Integer userid);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid)" + 
    "VALUES(#{notetitle}, #{notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int insertNote(UserNote note);

    @Delete("DELETE FROM NOTES where noteid = #{noteid}")
    void deleteNote(Integer noteid);
    
}
