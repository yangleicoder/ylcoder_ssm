package com.ylcoder.controller;

import com.ylcdoer.domain.Permission;
import com.ylcoder.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        List<Permission> permissionList = permissionService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("permissionList", permissionList);
        modelAndView.setViewName("permission-list");

        return modelAndView;
    }

    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception {
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public String deleteById(String id) {
        permissionService.deleteById(id);
        return "redirect:findAll.do";
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("/deleteByIds")
    public String deleteByIds(@RequestParam(name = "ids", required = true) String[] ids) {
        permissionService.deleteByIds(ids);
        return "redirect:findAll.do";
    }

}
