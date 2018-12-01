package com.ylcoder.controller;

import com.ylcdoer.domain.Product;
import com.ylcoder.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
@RolesAllowed("USER")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<Product> productList = productService.findAll();
        ModelAndView  mv=new ModelAndView();
        mv.addObject("productList",productList);
        mv.setViewName("product-list");
        return mv;
    }
    @RequestMapping("/save.do")
    public String save(Product product){
        productService.save(product);
        return "redirect:findAll.do";
    }
}
