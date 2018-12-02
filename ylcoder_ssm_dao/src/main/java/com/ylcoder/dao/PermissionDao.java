package com.ylcoder.dao;

import com.ylcdoer.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {
    @Select("select * from Permission where id in(select permissionid from ROLE_PERMISSION where roleid=#{id})")
    public List<Permission> findByid(String id);

    /**
     * 保存权限
     * @param permission
     */
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);

    /**
     *查询所有权限
     * @return
     */
    @Select("select * from permission")
    List<Permission> findAll();

    /**
     * 删除权限表数据
     * @param id
     */
    @Delete("delete from PERMISSION where id=#{id} ")
    void deleteById(String id);

    /**
     * 删除中间表数据
     *  @param id
     */
    @Delete("delete from ROLE_PERMISSION where permissionId=#{id}")
    void deleteRoleAndPerById(String id);
}
