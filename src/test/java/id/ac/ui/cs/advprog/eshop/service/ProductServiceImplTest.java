package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductReadRepository;
import id.ac.ui.cs.advprog.eshop.repository.ProductWriteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    // DIPISAH SESUAI ISP
    @Mock
    private ProductWriteRepository productWriteRepository;

    @Mock
    private ProductReadRepository productReadRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
    }

    @Test
    void testCreate() {
        when(productWriteRepository.create(product)).thenReturn(product);
        Product created = productService.create(product);
        assertEquals(product, created);
        verify(productWriteRepository, times(1)).create(product);
    }

    @Test
    void testFindAll() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Iterator<Product> iterator = productList.iterator();

        when(productReadRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();
        assertEquals(1, result.size());
        assertEquals(product, result.get(0));
    }

    @Test
    void testFindById() {
        when(productReadRepository.findById(product.getProductId())).thenReturn(product);
        Product result = productService.findById(product.getProductId());
        assertEquals(product, result);
    }

    @Test
    void testEdit() {
        when(productWriteRepository.edit(product)).thenReturn(product);
        Product result = productService.edit(product);
        assertEquals(product, result);
    }

    @Test
    void testDelete() {
        doNothing().when(productWriteRepository).delete(product.getProductId());
        productService.delete(product.getProductId());
        verify(productWriteRepository, times(1)).delete(product.getProductId());
    }
}