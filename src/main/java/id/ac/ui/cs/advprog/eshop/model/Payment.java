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

        if ("VOUCHER".equals(method)) {
            if (paymentData != null && paymentData.containsKey("voucherCode") &&
                    paymentData.get("voucherCode").startsWith("ESHOP") &&
                    paymentData.get("voucherCode").length() == 16) {
                this.status = "SUCCESS";
            } else {
                this.status = "REJECTED";
            }
        } else if ("BANK_TRANSFER".equals(method)) {
            validateBankTransfer();
        } else {
            this.status = "REJECTED";
        }
    }

    private void validateBankTransfer() {
        if (this.paymentData == null || this.paymentData.isEmpty() ||
                this.paymentData.getOrDefault("bankName", "").isEmpty() ||
                this.paymentData.getOrDefault("referenceCode", "").isEmpty()) {
            this.status = "REJECTED";
        } else {
            this.status = "SUCCESS";
        }
    }
}