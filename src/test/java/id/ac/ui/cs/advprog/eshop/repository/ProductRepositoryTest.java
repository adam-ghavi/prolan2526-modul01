package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;
    Product product;

    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);
    }

    @Test
    void testCreateAndFind() {
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindById() {
        productRepository.create(product);
        Product repoProduct = productRepository.findById(product.getProductId());
        assertNotNull(repoProduct);
    }

    @Test
    void testNotFoundById() {
        productRepository.create(product);
        Product repoProduct = productRepository.findById("abc");
        assertNull(repoProduct);
    }

    @Test
    void testRemoveById() {
        productRepository.create(product);
        productRepository.deleteById(product.getProductId());
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testCantRemoveById() {
        productRepository.create(product);
        productRepository.deleteById("abc");
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
    }

    @Test
    void testUpdate() {
        int quantityPrevious = product.getProductQuantity();
        productRepository.create(product);

        Product newProduct = new Product();
        newProduct.setProductId(product.getProductId());
        newProduct.setProductQuantity(product.getProductQuantity()-1);
        productRepository.update(newProduct);

        Product repoProduct = productRepository.findById(product.getProductId());
        assertNotEquals(quantityPrevious, repoProduct.getProductQuantity());
    }

    @Test
    void testUpdateNotFound() {
        int quantityPrevious = product.getProductQuantity();
        productRepository.create(product);

        Product newProduct = new Product();
        newProduct.setProductId("abc");
        newProduct.setProductQuantity(product.getProductQuantity()-1);
        productRepository.update(newProduct);

        Product repoProduct = productRepository.findById(product.getProductId());
        assertEquals(quantityPrevious, repoProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }
}
