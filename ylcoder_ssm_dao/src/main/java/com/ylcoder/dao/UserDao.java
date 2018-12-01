package com.ylcoder.dao;

import com.ylcdoer.domain.Role;
import com.ylcdoer.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface UserDao {
    /**
     * 根据名字查询用户信息
     *
     * @param username
     * @return
     */
    @Select("select *from users where username=#{username}")
    @Results(id = "d",value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "statusStr", column = "statusStr"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.ylcoder.dao.RoleDao.findByid",fetchType = FetchType.EAGER))
    })
    public UserInfo findByUaerName(String username);

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from users")
    List<UserInfo> findAll();

    /**
     * 根据id查找
     * @param id
     * @return
     */
    @Select("select *from users where id=#{id}")
    @Results(id = "h",value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "statusStr", column = "statusStr"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.ylcoder.dao.RoleDao.findByUserid",fetchType = FetchType.EAGER))
    })
    UserInfo findById(String id);

    /**
     * 用户保存
     * @param user
     */
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo user);

    /**
     * 根据id查询用户还没有的角色
     * @param userId
     * @return
     */
    @Select("select * from role where id not in(select roleId from USERS_ROLE where userId=#{userId})")
    List<Role> findOtherRole(String userId);

    /**
     * 为用户添加角色
     * @param userId
     * @param roleId
     */
    @Insert("insert into USERS_ROLE(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
