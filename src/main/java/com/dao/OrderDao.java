package com.dao;

import com.entity.Orderr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Cacheable(cacheNames = "oreder",key ="#oid")
    public Orderr findByOredrrId(int oid) {
        final Orderr order=new Orderr();
        String sql = "SELECT otime,uid,pid,state FROM orderr WHERE oid=?";
        jdbcTemplate.query(sql, new Object[]{oid}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                order.setOtime(resultSet.getString(1));
                order.setUid(resultSet.getInt(2));
                order.setPid(resultSet.getInt(3));
                order.setState(resultSet.getString(4));
            }
        });
        if (order.getState().equals("F")){
            return null;
        }
        return order;
    }

    public List<Orderr> findByUserId(int uid) {
        String sql = "SELECT oid,otime,pid,state *FROM orderr WHERE uid=?";
        return jdbcTemplate.query(sql, new Object[]{uid}, new RowMapper<Orderr>() {
            final Orderr orderr=new Orderr();
            @Override
            public Orderr mapRow(ResultSet resultSet, int i) throws SQLException {
                orderr.setOid(resultSet.getInt(1));
                orderr.setOtime(resultSet.getString(2));
                orderr.setPid(resultSet.getInt(3));
                orderr.setState(resultSet.getString(4));
                if (orderr.getState().equals("F")){
                    return null;
                }
                return orderr;
            }
        });
    }
    public int insertOrderr(Orderr orderr){
        String sql="INSERT INTO orderr (otime,uid,pid,state) VALUES (?,?,?,?)";
        return jdbcTemplate.update(sql,orderr.getOtime(),orderr.getUid(),orderr.getPid(),orderr.getState());
    }
    public int delectOrderr(Orderr orderr){
        String sql = "UPDATE orderr SET state=? WHERE oid=?";
        return jdbcTemplate.update(sql, orderr.getState(), orderr.getOid());
    }
    public int deleteOrderbyUid(int uid) {
        String sql = "EDLETE FROM orderr WHERE uid=?";
        return jdbcTemplate.update(sql,uid);
    }

}
