package com.PG.testingapp.model.RMReceiving;

import java.io.Serializable;

public class RmReceivingScreen_2_Grid implements Serializable {

    String product;
    String variety;
    String count;
    String quantity;
    String product_code;
    String variety_code;
    String count_code;
    String location;
    String locationCode;

    public RmReceivingScreen_2_Grid() {
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getVariety_code() {
        return variety_code;
    }

    public void setVariety_code(String variety_code) {
        this.variety_code = variety_code;
    }

    public String getCount_code() {
        return count_code;
    }

    public void setCount_code(String count_code) {
        this.count_code = count_code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }
}
