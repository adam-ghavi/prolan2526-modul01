package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceImplTest {
    PaymentService paymentService;
    Order order;
    Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentService = new PaymentServiceImpl();

        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("prod-1");
        product.setProductName("Sabun Cap Usep");
        product.setProductQuantity(1);
        products.add(product);

        order = new Order(
                "order-1",
                products,
                1708560000L,
                "Safira Sudrajat"
        );

        paymentData = new HashMap<>();
        paymentData.put("bank", "BCA");
        paymentData.put("accountNumber", "1234567890");
    }

    @Test
    void testAddPayment() {
        Payment payment = paymentService.addPayment(order, "BANK_TRANSFER", paymentData);

        assertNotNull(payment);
        assertEquals("BANK_TRANSFER", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testGetPaymentById() {
        Payment payment = paymentService.addPayment(order, "BANK_TRANSFER", paymentData);
        Payment foundPayment = paymentService.getPayment(payment.getId());

        assertNotNull(foundPayment);
        assertEquals(payment.getId(), foundPayment.getId());
    }

    @Test
    void testGetAllPayments() {
        paymentService.addPayment(order, "BANK_TRANSFER", paymentData);
        paymentService.addPayment(order, "EWALLET", paymentData);

        List<Payment> payments = paymentService.getAllPayments();

        assertEquals(2, payments.size());
    }

    @Test
    void testSetStatusSuccessAlsoUpdatesOrder() {
        Payment payment = paymentService.addPayment(order, "BANK_TRANSFER", paymentData);

        paymentService.setStatus(payment, "SUCCESS");

        assertEquals("SUCCESS", payment.getStatus());
        assertEquals("SUCCESS", order.getStatus());
    }

    @Test
    void testSetStatusRejectedAlsoUpdatesOrder() {
        Payment payment = paymentService.addPayment(order, "BANK_TRANSFER", paymentData);

        paymentService.setStatus(payment, "REJECTED");

        assertEquals("REJECTED", payment.getStatus());
        assertEquals("FAILED", order.getStatus());
    }

    @Test
    void testBankTransferSuccess() {

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "INV-123");

        Payment payment = paymentService.addPayment(order, "BANK_TRANSFER", paymentData);

        assertEquals("PENDING", payment.getStatus());
    }

    @Test
    void testBankTransferRejectedIfBankNameEmpty() {

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "");
        paymentData.put("referenceCode", "INV-123");

        Payment payment = paymentService.addPayment(order, "BANK_TRANSFER", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testBankTransferRejectedIfReferenceCodeEmpty() {

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "");

        Payment payment = paymentService.addPayment(order, "BANK_TRANSFER", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }
}