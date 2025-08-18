package com.usabana.models.synchronous;

import lombok.Data;

@Data
public class AuthorizationRequest {

    private int id;
    private String productName;
    private double totalAmtt;
    private String paymentMethod;
    private int quantity;
    private String shipDate;
    private boolean complete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getTotalAmtt() {
        return totalAmtt;
    }

    public void setTotalAmtt(double totalAmtt) {
        this.totalAmtt = totalAmtt;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

}