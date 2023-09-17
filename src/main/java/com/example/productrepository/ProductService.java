package com.example.productrepository;

import org.springframework.stereotype.Service;
import java.util.UUID;
@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /*public List<Product> findAllProducts(){
        return productRepository.findAll();
    }*/

    public Product saveProduct(NewProduct newProduct) {
        Product product = new Product(UUID.randomUUID().toString(), newProduct.title(), newProduct.price());
        return productRepository.save(product);
    }

    public Product findProductByID(String id) {
        return productRepository.findProductBy(id);
    }

    public void deleteProduct(String id) {
        productRepository.removeProductBy(id);
    }
}