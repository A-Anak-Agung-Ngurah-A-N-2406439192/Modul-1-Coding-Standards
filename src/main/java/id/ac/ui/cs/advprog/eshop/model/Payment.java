package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
public class Payment {
    private String id;
    private String method;
    private Map<String, String> paymentData;
    private Order order;
    @Setter
    private String status;

    public Payment(String id, String method, Map<String, String> paymentData, Order order) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.order = order;

        if ("BANK_TRANSFER".equals(method)) {
            if (paymentData == null || paymentData.isEmpty() ||
                    paymentData.getOrDefault("bankName", "").isEmpty() ||
                    paymentData.getOrDefault("referenceCode", "").isEmpty()) {
                this.status = "REJECTED";
            } else {
                this.status = "SUCCESS";
            }
        } else {
            this.status = "REJECTED";
        }
    }
}