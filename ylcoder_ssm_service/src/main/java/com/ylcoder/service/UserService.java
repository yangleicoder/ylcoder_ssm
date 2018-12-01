package com.ylcoder.service;
import com.ylcdoer.domain.Role;
import com.ylcdoer.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{

    List<UserInfo> findAll();

    UserInfo findById(String id);

    void save(UserInfo user);

    List<Role> findOtherRole(String userId);

    void addRoleToUser(String userId, String[] roles);
}
