package com.dao.mapper;


import com.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

    @Select(value = "SELECT uid,uname,uage FROM userr WHERE uid=#{id}")
//    @Results(value = {
//            @Result(column = "id", property = "id"),
//            @Result(column = "name", property = "uName"),
//            @Result(column = "age", property = "uAge"),
//
//    })
    User findByUserId(@Param("id") int id);


    @Select(value = "SELECT uid,uname,uage FROM userr WHERE uname=#{name}")
    User findByName(@Param("name") String name);


    @Select(value = "SELECT uid,uname FROM userr WHERE uname=#{name} AND upasswd=#{password}")
    User findByNameAndPassword(@Param(value = "name") String name, @Param(value = "password") String password);

    @Insert(value = "INSERT INTO userr (name,age,password) VALUES (#{user.name},#{user.age},#{user.password})")
    int insertUser(@Param(value = "user") User user);

    @Update(value = "UPDATE userr SET uname=#{user.name} WHERE uid=#{user.id}")
    int updateUser(@Param(value = "user") User user);
}
