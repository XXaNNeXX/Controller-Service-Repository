package com.example.productrepository;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /*@GetMapping()
    public List<Product> getAllProducts(){
      return productService.findAllProducts();
    }*/
    @PostMapping
    public Product addProduct(@RequestBody NewProduct newProduct) {
        return productService.saveProduct(newProduct);
    }
    @GetMapping("/{id}")
    public Product findProductByID(@PathVariable String id) {
        return productService.findProductByID(id);
    }
    @DeleteMapping
    public void removeProduct(@RequestBody String id) {
        productService.deleteProduct(id);
    }

}
