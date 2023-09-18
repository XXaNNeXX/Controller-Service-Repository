package com.example.productrepository;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

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

        Product p1 = new Product("1", "Smartphone", 200);

        doThrow(NullPointerException.class).when(productRepo).removeProductBy(p1.id());

        assertThrows(NullPointerException.class, () -> productRepo.removeProductBy(p1.id()));
    }

    /*@Test
    void addProduct() {

        ProductRepository productRepo = mock(ProductRepository.class);
        IdService idService = mock(IdService.class);
        ProductService productService = new ProductService(productRepo);

        NewProduct p2 = new NewProduct("Smartphone", 200);
        Product p1 = new Product(idService.randomUUID(), p2.title(), p2.price());

        when(productRepo.save(p1)).thenReturn(p1);
        when(idService.randomUUID()).thenReturn(UUID.randomUUID().toString());        //???

        //[...]

        Product actual = productService.saveProduct(p2);
        Product expected = p1;

        assertEquals(expected, actual);
    }*/

}