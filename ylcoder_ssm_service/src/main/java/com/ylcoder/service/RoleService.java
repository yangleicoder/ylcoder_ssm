package com.ylcoder.service;

import com.ylcdoer.domain.Permission;
import com.ylcdoer.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    void save(Role role);

    Role findById(String roleId);

    List<Permission> findOtherPermission(String roleId);

    void addPermissionToRole(String roleId, String[] permissions);
}
