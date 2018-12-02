package com.ylcoder.service;


import com.ylcdoer.domain.SysLog;

import java.util.List;

public interface SysLogService {
    List<SysLog> findAll();
    public void save(SysLog sysLog);
}
