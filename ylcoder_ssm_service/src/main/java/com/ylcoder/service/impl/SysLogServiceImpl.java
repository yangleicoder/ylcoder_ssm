package com.ylcoder.service.impl;

import com.ylcdoer.domain.SysLog;
import com.ylcoder.dao.SysLogDao;
import com.ylcoder.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService{
    @Autowired
    private SysLogDao sysLogDao;
    /**
     * 查询所有日志
     * @return
     */
    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }

    /**
     * 保存
     * @param sysLog
     */
    @Override
    public void save(SysLog sysLog) {
       sysLogDao.save(sysLog);
    }
}
