package com.example.hello_springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hello_springboot.entity.Product;
import com.example.hello_springboot.mapper.ProductMapper;

import java.util.List;

@Service
public class ProductService {
    private final ProductMapper productMapper;
@Autowired
    public ProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public List<Product> getAllProducts() { return productMapper.getAllProducts(); }
    public Product getProductById(Long id) { return productMapper.getProductById(id); }
    public void createProduct(Product product) { productMapper.insertProduct(product); }
    public void updateProduct(Product product) { productMapper.updateProduct(product); }
    public void deleteProduct(Long id) { productMapper.deleteProduct(id); }
}