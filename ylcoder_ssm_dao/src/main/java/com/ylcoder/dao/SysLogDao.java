package com.ylcoder.dao;

import com.ylcdoer.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysLogDao {

    /**
     * 查询所有的日志
     * @return
     */
    @Select("select * from SysLog")
    List<SysLog> findAll();
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog);
}
