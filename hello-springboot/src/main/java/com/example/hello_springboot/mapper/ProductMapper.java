package com.example.hello_springboot.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.hello_springboot.entity.Product;

import java.util.List;

@Mapper
public interface ProductMapper {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    void insertProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Long id);
}