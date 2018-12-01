package com.ylcoder.service.impl;

import com.ylcdoer.domain.Permission;
import com.ylcdoer.domain.Role;
import com.ylcoder.dao.RoleDao;
import com.ylcoder.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);

    }

    /**
     * 根据roleId查询一个用户
     * @param roleId
     * @return
     */
    @Override
    public Role findById(String roleId) {
        return roleDao.findByRoleId(roleId);
    }

    /**
     * 根据role查询角色还没有的权限
     * @param roleId
     * @return
     */
    @Override
    public List<Permission> findOtherPermission(String roleId) {
        return roleDao.findOtherPermission(roleId);
    }

    /**
     * 为角色添加权限
     * @param roleId
     * @param permissions
     */
    @Override
    public void addPermissionToRole(String roleId, String[] permissions) {
        for (String permission : permissions) {
            roleDao.addPermissionToRole(roleId,permission);
        }
    }
}
