package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    private PaymentRepository paymentRepository;
    private Payment payment;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        Product product = new Product();
        product.setProductId("prod-1");
        product.setProductQuantity(1);
        List<Product> products = new ArrayList<>();
        products.add(product);

        Order order = new Order("order-1", products, 12345L, "Test");
        payment = new Payment("pay-1", "BANK_TRANSFER", new HashMap<>(), order);
    }

    @Test
    void testSaveAndFindById() {
        paymentRepository.save(payment);
        Payment found = paymentRepository.findById("pay-1");
        assertNotNull(found);
        assertEquals(payment.getId(), found.getId());
    }

    @Test
    void testFindAll() {
        paymentRepository.save(payment);
        List<Payment> allPayments = paymentRepository.findAll();
        assertEquals(1, allPayments.size());
    }
}