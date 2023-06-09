package com.udacity.jwdnd.course1.cloudstorage.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.model.User;

@Mapper
public interface UserMapper {
    
    @Select("SELECT * FROM USERS where username = #{username}")
    User getUser(String username);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname)" + 
    "VALUES(#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    int insertUser(User user);

}
