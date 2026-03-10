package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class Payment {
    String id;
    String method;
    String status;
    Order order;
    Map<String, String> paymentData;
}