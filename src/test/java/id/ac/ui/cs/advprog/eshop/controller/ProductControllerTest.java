package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void createProductPage_shouldReturnCreateView() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void createProductPost_shouldRedirectToList() throws Exception {
        mockMvc.perform(post("/product/create")
                        .param("productName", "Laptop")
                        .param("productQuantity", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(productService).create(any(Product.class));
    }

    @Test
    void productListPage_shouldReturnProductListView() throws Exception {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Mouse");
        product.setProductQuantity(5);

        when(productService.findAll())
                .thenReturn(List.of(product));

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("productList"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attribute("products", hasSize(1)));
    }

    @Test
    void deleteProduct_shouldRedirectToList() throws Exception {
        mockMvc.perform(get("/product/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService).deleteById("1");
    }

    @Test
    void editProductPage_shouldReturnEditView() throws Exception {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Keyboard");
        product.setProductQuantity(3);

        when(productService.findById("1"))
                .thenReturn(product);

        mockMvc.perform(get("/product/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("editProduct"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attribute("product",
                        hasProperty("productName", is("Keyboard"))));
    }

    @Test
    void editProductPost_shouldRedirectToList() throws Exception {
        mockMvc.perform(post("/product/edit")
                        .param("productId", "1")
                        .param("productName", "Updated")
                        .param("productQuantity", "20"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService).update(any(Product.class));
    }
}