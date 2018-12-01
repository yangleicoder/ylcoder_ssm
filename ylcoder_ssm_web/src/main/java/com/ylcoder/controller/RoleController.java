package com.ylcoder.controller;

import com.ylcdoer.domain.Permission;
import com.ylcdoer.domain.Role;
import com.ylcdoer.domain.UserInfo;
import com.ylcoder.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询所哟角色
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        List<Role> roleList = roleService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roleList", roleList);
        modelAndView.setViewName("role-list");

        return modelAndView;
    }

    /**
     * 保存角色
     * @param role
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    /**
     * 角色详情
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) {
        Role role= roleService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("role", role);
        modelAndView.setViewName("role-show");

        return modelAndView;
    }
    /**
     * 查询角色与权限
     * @param roleId
     * @return
     */
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) String roleId) {
        //根据id查询角色信息
        Role role=roleService.findById(roleId);
        //根据id查询用户权限信息
        List<Permission> permissionList=roleService.findOtherPermission(roleId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("role",role );
        mv.addObject("permissionList",permissionList);
        mv.setViewName("role-permission-add");
        return mv;
    }

    /**
     * 为角色添加权限
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) String roleId, @RequestParam(name = "ids", required = true) String[] permissions) throws Exception {
        roleService.addPermissionToRole(roleId,permissions);
        return "redirect:findAll.do";
    }




}
