package com.ylcoder.service.impl;

import com.ylcdoer.domain.Permission;
import com.ylcoder.dao.PermissionDao;
import com.ylcoder.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    @Override
    public void deleteById(String id) {
        //1.删除主表
        permissionDao.deleteRoleAndPerById(id);
        //2.删除从表
        permissionDao.deleteById(id);
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            this.deleteById(id);
        }
    }
}
