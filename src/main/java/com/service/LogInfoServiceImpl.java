package com.service;



import com.dao.LogInfoDao;
import com.entity.LogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogInfoServiceImpl {

    @Autowired
    private LogInfoDao logInfoDao;


    public int insertLog(LogInfo logInfo) {
        return logInfoDao.insertLog(logInfo);
    }

}
