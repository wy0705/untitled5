package com.dao;


import com.entity.LogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LogInfoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public int insertLog(LogInfo logInfo) {
        String sql = "INSERT INTO logInfo (logOp,logType,userId,createtime,url) VALUES (?,?,?,?,?)";
        return jdbcTemplate.update(sql, logInfo.getLogOp(), logInfo.getLogType(), logInfo.getUserId(), logInfo.getCreateTime(), logInfo.getUrl());
    }
}
