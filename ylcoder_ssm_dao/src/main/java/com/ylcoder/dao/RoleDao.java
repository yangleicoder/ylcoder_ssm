package com.ylcoder.dao;

import com.ylcdoer.domain.Permission;
import com.ylcdoer.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface RoleDao {
    /**
     * 两表连查
     * @param id
     * @return
     */
    @Select("select * from Role where id in(select roleid from USERS_ROLE where userid=#{id})")
    public List<Role> findByid(String id);


    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Select("select * from Role where id =#{id}")
    @Results(id = "ro",value = {
            @Result(id=true,column="id",property="id"),
            @Result(column="roleName",property="roleName"),
            @Result(column="roleDesc",property="roleDesc"),
            @Result(column="id",property="permissions",
                    javaType=List.class,many=@Many(select="com.ylcoder.dao.PermissionDao.findByid",fetchType = FetchType.EAGER))
    })
    public Role findByRoleId(String id);
    /**
     * 关联查询
     * @param id
     * @return
     */
    @Select("select * from Role where id in(select roleid from USERS_ROLE where userid=#{id})")
    @Results(id = "u",value = {
            @Result(id=true,column="id",property="id"),
            @Result(column="roleName",property="roleName"),
            @Result(column="roleDesc",property="roleDesc"),
            @Result(column="id",property="permissions",
                    javaType=List.class,many=@Many(select="com.ylcoder.dao.PermissionDao.findByid",fetchType = FetchType.EAGER))
    })
    public List<Role> findByUserid(String id);

    /**
     * 查询所有
     * @return
     */
    @Select("select * from role")
    List<Role> findAll();

    /**
     * 添加角色
     * @param role
     */
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    /**
     * 根据role查询角色还没有的权限
     * @param roleId
     * @return
     */
    @Select("select * from permission where id not in(select permissionId from ROLE_PERMISSION where roleId=#{roleId})")
    List<Permission> findOtherPermission(String roleId);

    /**
     * 为角色添加权限
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into ROLE_PERMISSION(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
