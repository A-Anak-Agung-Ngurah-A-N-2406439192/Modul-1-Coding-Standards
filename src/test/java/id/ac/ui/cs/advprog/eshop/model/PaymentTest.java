package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order("order-1", null, 123456789L, "Safira");
    }

    @Test
    void testCreatePaymentBankTransferSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "1234567890");

        Payment payment = new Payment("pay-1", "BANK_TRANSFER", paymentData, order);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentBankTransferEmptyBankName() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "");
        paymentData.put("referenceCode", "1234567890");

        Payment payment = new Payment("pay-2", "BANK_TRANSFER", paymentData, order);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentBankTransferEmptyReferenceCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "");

        Payment payment = new Payment("pay-3", "BANK_TRANSFER", paymentData, order);
        assertEquals("REJECTED", payment.getStatus());
    }
}