package com.example.bigevent.mapper;

import com.example.bigevent.dto.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

@Service
@Mapper
public interface UserRepository {
    @Select("select * from user where username=#{username}")
    User findByUsername(String username);

    @Insert("insert into user (username, password, create_time, update_time)" +
            " values (#{username},#{password},now(),now())")
    void addUser(String username, String password);

    @Update("update user set nickname=#{nickname}, email=#{email},update_time=#{updateTime} where id = #{id}")
    void updateUser(User user);

    @Update("update user set user_pic=#{avatar},update_time=now() where id = #{id}")
    void updateAvatar(String avatar, Integer id);

    @Update("update user set password=#{password},update_time=now() where username = #{username}")
    void updatePassword(String password, String username);
}
