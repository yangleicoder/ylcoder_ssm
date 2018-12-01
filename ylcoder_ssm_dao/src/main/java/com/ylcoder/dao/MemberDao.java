package com.ylcoder.dao;

import com.ylcdoer.domain.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface MemberDao {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select * from member where id=#{id}")
    public Member findById(Insert id);
}
