package com.example.productrepository;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Test
    void findAllProducts() {

        ProductRepository productRepo = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepo);

        Product p1 = new Product("1", "Smartphone", 200);
        List<Product> productList = List.of(p1);

        when(productRepo.findAll()).thenReturn(productList);

        List<Product> actual = productService.findAllProducts();

        List<Product> expected = List.of(p1);

        assertEquals(expected, actual);
    }

    @Test
    void findProductByID() {

        ProductRepository productRepo = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepo);

        Product p1 = new Product("1", "Smartphone", 200);

        when(productRepo.findProductBy(p1.id())).thenReturn(p1);

        Product actual = productService.findProductByID(p1.id());

        Product expected = p1;

        assertEquals(expected, actual);
    }

    @Test
    void deleteProduct() {

        ProductRepository productRepo = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepo);

        Product p1 = new Product("1", "Smartphone", 200);

        doThrow(NullPointerException.class).when(productRepo).removeProductBy(p1.id());

        assertThrows(NullPointerException.class, () -> productRepo.removeProductBy(p1.id()));
    }

}