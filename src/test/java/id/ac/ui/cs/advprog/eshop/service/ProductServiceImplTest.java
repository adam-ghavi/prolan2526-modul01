package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductName("Test Product");
        product.setProductQuantity(10);
    }

    @Test
    void testCreateProduct() {
        // Act
        Product createdProduct = productService.create(product);

        // Assert
        assertNotNull(createdProduct.getProductId());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindById() {
        when(productRepository.findById("1")).thenReturn(product);

        Product found = productService.findById("1");

        assertEquals(product, found);
        verify(productRepository, times(1)).findById("1");
    }

    @Test
    void testFindAll() {
        Product p1 = new Product();
        Product p2 = new Product();

        Iterator<Product> iterator = Arrays.asList(p1, p2).iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> products = productService.findAll();

        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testUpdateProduct() {
        productService.update(product);

        verify(productRepository, times(1)).update(product);
    }

    @Test
    void testDeleteById() {
        productService.deleteById("1");

        verify(productRepository, times(1)).deleteById("1");
    }
}