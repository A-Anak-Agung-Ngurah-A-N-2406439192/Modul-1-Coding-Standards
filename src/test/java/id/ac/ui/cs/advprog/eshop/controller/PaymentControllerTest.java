package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PaymentController.class)
class PaymentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PaymentService paymentService;

    @Test
    void testPaymentDetailForm() throws Exception {
        mockMvc.perform(get("/payment/detail"))
                .andExpect(status().isOk())
                .andExpect(view().name("payment/detailForm"));
    }

    @Test
    void testPaymentDetailById() throws Exception {
        when(paymentService.getPayment(anyString())).thenReturn(null); // Dummy
        mockMvc.perform(get("/payment/detail/pay-1"))
                .andExpect(status().isOk())
                .andExpect(view().name("payment/detail"));
    }

    @Test
    void testPaymentAdminList() throws Exception {
        when(paymentService.getAllPayment()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/payment/admin/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("payment/adminList"));
    }

    @Test
    void testPaymentAdminDetail() throws Exception {
        mockMvc.perform(get("/payment/admin/detail/pay-1"))
                .andExpect(status().isOk())
                .andExpect(view().name("payment/adminDetail"));
    }

    @Test
    void testPaymentAdminSetStatus() throws Exception {
        mockMvc.perform(post("/payment/admin/set-status/pay-1").param("status", "SUCCESS"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/payment/admin/list"));
    }
}