package com.example.hello_springboot.controller;

import org.springframework.web.bind.annotation.*;

import com.example.hello_springboot.entity.Product;
import com.example.hello_springboot.service.ProductService;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:5173") // 允许前端访问
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(  ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public List<Product> getAllProducts() { return productService.getAllProducts(); }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) { return productService.getProductById(id); }

    @PostMapping
    public void createProduct(@RequestBody Product product) { productService.createProduct(product); }

    @PutMapping
    public void updateProduct(@RequestBody Product product) { productService.updateProduct(product); }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) { productService.deleteProduct(id); }
}