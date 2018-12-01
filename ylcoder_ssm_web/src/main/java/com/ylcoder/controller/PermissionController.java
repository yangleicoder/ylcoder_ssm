package com.ylcoder.controller;

import com.ylcdoer.domain.Permission;
import com.ylcoder.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        List<Permission> permissionList = permissionService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("permissionList",permissionList );
        modelAndView.setViewName("permission-list");

        return modelAndView;
    }
    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception {
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

}
