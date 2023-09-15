package com.example.productrepository;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getAllProducts(){
      return productService.findAllProducts();
    }
    @PostMapping
    public Product addProduct(@RequestBody NewProduct newProduct) {
        return productService.saveProduct(newProduct);
    }

}
