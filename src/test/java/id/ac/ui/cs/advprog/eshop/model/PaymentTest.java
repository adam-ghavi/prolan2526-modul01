package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    @Test
    void testCreatePayment() {
        Payment payment = new Payment();

        payment.setId("payment-1");
        payment.setMethod("BANK_TRANSFER");
        payment.setStatus("PENDING");

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bank", "BCA");
        paymentData.put("accountNumber", "123456789");

        payment.setPaymentData(paymentData);

        assertEquals("payment-1", payment.getId());
        assertEquals("BANK_TRANSFER", payment.getMethod());
        assertEquals("PENDING", payment.getStatus());
        assertEquals("BCA", payment.getPaymentData().get("bank"));
        assertEquals("123456789", payment.getPaymentData().get("accountNumber"));
    }

    @Test
    void testPaymentDataCanStoreMultipleValues() {
        Payment payment = new Payment();

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bank", "BNI");
        paymentData.put("accountNumber", "987654321");
        paymentData.put("holderName", "Safira Sudrajat");

        payment.setPaymentData(paymentData);

        assertEquals(3, payment.getPaymentData().size());
        assertTrue(payment.getPaymentData().containsKey("holderName"));
    }

    @Test
    void testUpdatePaymentStatus() {
        Payment payment = new Payment();

        payment.setStatus("PENDING");
        payment.setStatus("SUCCESS");

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testUpdatePaymentMethod() {
        Payment payment = new Payment();

        payment.setMethod("BANK_TRANSFER");
        payment.setMethod("EWALLET");

        assertEquals("EWALLET", payment.getMethod());
    }

    @Test
    void testPaymentDataInitialization() {
        Payment payment = new Payment();

        Map<String, String> paymentData = new HashMap<>();
        payment.setPaymentData(paymentData);

        assertNotNull(payment.getPaymentData());
        assertTrue(payment.getPaymentData().isEmpty());
    }
}