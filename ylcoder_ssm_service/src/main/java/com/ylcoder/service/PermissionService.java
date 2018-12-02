package com.ylcoder.service;

import com.ylcdoer.domain.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();

    void save(Permission permission);

    void deleteById(String id);

    void deleteByIds(String[] ids);
}
