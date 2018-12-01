package com.ylcoder.service;

import com.ylcdoer.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll() throws Exception;

    void save(Product product);
}
