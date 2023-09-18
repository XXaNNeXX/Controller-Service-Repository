package com.example.productrepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/{id}")
    public Product findProductByID(@PathVariable String id) {
        return productService.findProductByID(id);
    }
    @DeleteMapping("/{id}")
    public void removeProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product product) {
        if(!id.equals(product.id())) {
            throw new IllegalArgumentException("Product ID does not match");
        }
        return productService.updateProduct(id, product);
    }

}
