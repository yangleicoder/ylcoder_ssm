package com.ylcoder.controller;

import com.ylcdoer.domain.Product;
import com.ylcdoer.domain.Role;
import com.ylcdoer.domain.UserInfo;
import com.ylcoder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 根据id查询用户还没有权限
     *
     * @param userId
     * @return
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) String userId) {
        //根据id查询用户信息
        UserInfo userInfo = userService.findById(userId);
        //根据id查询用户角色信息
        List<Role> rolelist = userService.findOtherRole(userId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", userInfo);
        mv.addObject("roleList", rolelist);
        mv.setViewName("user-role-add");
        return mv;
    }

    /**
     * 为用户添加角色
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) String userId, @RequestParam(name = "ids", required = true) String[] roleIds) throws Exception {
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }

    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        List<UserInfo> userInfoList = userService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList", userInfoList);
        modelAndView.setViewName("user-list");

        return modelAndView;
    }

    /**
     * 根据id查询用户用于用户详情
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) {
        UserInfo user = userService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("user-show");

        return modelAndView;
    }

    /**
     * 保存用户
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(UserInfo user) throws Exception {
        //密码加密
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userService.save(user);
        return "redirect:findAll.do";
    }

}
