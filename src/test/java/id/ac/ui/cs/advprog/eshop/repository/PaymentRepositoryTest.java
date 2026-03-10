package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {

    PaymentRepository paymentRepository;
    Order order;
    Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("prod-1");
        product.setProductName("Sabun Cap Usep");
        product.setProductQuantity(2);
        products.add(product);

        order = new Order(
                "order-1",
                products,
                1708560000L,
                "Safira Sudrajat"
        );

        paymentData = new HashMap<>();
        paymentData.put("bank", "BCA");
        paymentData.put("accountNumber", "123456789");
    }

    @Test
    void testSaveCreatePayment() {
        Payment payment = new Payment();
        payment.setId("payment-1");
        payment.setOrder(order);
        payment.setMethod("BANK_TRANSFER");
        payment.setStatus("PENDING");
        payment.setPaymentData(paymentData);

        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById("payment-1");

        assertNotNull(result);
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testFindByIdIfIdFound() {
        Payment payment = new Payment();
        payment.setId("payment-2");
        payment.setOrder(order);
        payment.setMethod("EWALLET");
        payment.setStatus("PENDING");
        payment.setPaymentData(paymentData);

        paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById("payment-2");

        assertNotNull(findResult);
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        Payment findResult = paymentRepository.findById("unknown-id");

        assertNull(findResult);
    }

    @Test
    void testFindAllPayments() {
        Payment payment1 = new Payment();
        payment1.setId("payment-1");
        payment1.setOrder(order);
        payment1.setMethod("BANK_TRANSFER");
        payment1.setStatus("PENDING");
        payment1.setPaymentData(paymentData);

        Payment payment2 = new Payment();
        payment2.setId("payment-2");
        payment2.setOrder(order);
        payment2.setMethod("EWALLET");
        payment2.setStatus("PENDING");
        payment2.setPaymentData(paymentData);

        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        List<Payment> payments = paymentRepository.findAll();

        assertEquals(2, payments.size());
    }
}