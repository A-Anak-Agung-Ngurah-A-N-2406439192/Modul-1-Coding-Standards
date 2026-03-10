package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    private Payment payment;
    private Order order;

    @BeforeEach
    void setUp() {
        Product product = new Product();
        product.setProductId("prod-1");
        product.setProductQuantity(1);
        List<Product> products = new ArrayList<>();
        products.add(product);

        order = new Order("order-1", products, 12345L, "Test");
        Map<String, String> data = new HashMap<>();
        data.put("bankName", "BCA");
        data.put("referenceCode", "12345");
        payment = new Payment("pay-1", "BANK_TRANSFER", data, order);
    }

    @Test
    void testSetStatusPaymentSuccessAndOrderSuccess() {
        when(paymentRepository.findById("pay-1")).thenReturn(payment);
        Payment result = paymentService.setStatus(payment, "SUCCESS");
        assertEquals("SUCCESS", result.getStatus());
        assertEquals("SUCCESS", result.getOrder().getStatus());
    }

    @Test
    void testSetStatusPaymentRejectedAndOrderFailed() {
        when(paymentRepository.findById("pay-1")).thenReturn(payment);
        Payment result = paymentService.setStatus(payment, "REJECTED");
        assertEquals("REJECTED", result.getStatus());
        assertEquals("FAILED", result.getOrder().getStatus());
    }
}