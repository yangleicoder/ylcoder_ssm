package com.ylcoder.service.impl;

import com.ylcdoer.domain.Role;
import com.ylcdoer.domain.UserInfo;
import com.ylcoder.dao.UserDao;
import com.ylcoder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 登录验证
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findByUaerName(username);
        //取出权限
        List<Role> roles = userInfo.getRoles();
        List<SimpleGrantedAuthority> getAu = getAu(roles);
        //数据库查询的数据和前端的进行验证
        User user=new User(userInfo.getUsername(),userInfo.getPassword(),getAu);

        return user;
    }

    private List<SimpleGrantedAuthority> getAu(List<Role> roles){

        List<SimpleGrantedAuthority> Authority=new ArrayList<>();
        for(Role roles1:roles){
            Authority.add(new SimpleGrantedAuthority("ROLE_"+roles1.getRoleName()));
        }

        return Authority;
    }

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }

    /**
     * 根据ID查找
     *
     * @return
     */
    @Override
    public UserInfo findById(String id) {
        return userDao.findById(id);
    }

    /**
     * 保存用户
     * @param user
     */
    @Override
    public void save(UserInfo user) {
        userDao.save(user);
    }

    /**
     * 根据id查询用户还没有的角色
     * @param userId
     * @return
     */
    @Override
    public List<Role> findOtherRole(String userId) {
        return userDao.findOtherRole(userId);
    }

    /**
     * 为用户添加角色
     * @param userId
     * @param roles
     */
    @Override
    public void addRoleToUser(String userId, String[] roles) {
        for (String roleId : roles) {
            userDao.addRoleToUser(userId,roleId);
        }
    }
}
