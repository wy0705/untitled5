package com.dao;

import com.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;



    public User findByUserId(int uid) {
        User user = new User();
        String sql = "SELECT uid,upasswd,uname,uage,uaddr FROM userr WHERE uid=?";
        jdbcTemplate.query(sql, new Object[]{uid}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUid(resultSet.getInt(1));
                user.setUpasswd(resultSet.getString(2));
                user.setUname(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));
                user.setUaddr(resultSet.getString(5));
            }
        });
//        List list = jdbcTemplate.queryForObject(sql, new Object[]{id}, List.class);
        return user;
    }


    public User findByName(String uname) {


        final User user = new User();
        String sql = "SELECT uid,upasswd,uname,uage,uaddr FROM userr WHERE uname=?";
        jdbcTemplate.query(sql, new Object[]{uname}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUid(resultSet.getInt(1));
                user.setUpasswd(resultSet.getString(2));
                user.setUname(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));
                user.setUaddr(resultSet.getString(5));
            }
        });
        return user;
    }

    public User findByNameAndPassword(String name, String password) {

        final User user = new User();
        String sql = "SELECT uid,uname,uage,uaddr FROM userr WHERE uname=? AND upasswd=?";
        jdbcTemplate.query(sql, new Object[]{name, password}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUid(resultSet.getInt(1));
                user.setUname(resultSet.getString(2));
                user.setAge(resultSet.getInt(3));
                user.setUaddr(resultSet.getString(4));
            }
        });
        return user;
    }
    public int insertUser(User user) {
        String sql = "INSERT INTO userr (upasswd,uname,uage,uaddr) VALUES (?,?,?)";
        return jdbcTemplate.update(sql, user.getUpasswd(),user.getUname(), user.getAge(), user.getUaddr());
    }


    public int updateUser(User user) {
        String sql = "UPDATE userr SET uname=? WHERE uid=?";
        return jdbcTemplate.update(sql, user.getUname(), user.getUid());
    }
    public int deleteUser(int uid) {
        String sql = "EDLETE FROM userr WHERE uid=?";
        return jdbcTemplate.update(sql,uid);
    }

}
