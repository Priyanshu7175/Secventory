package com.example.Secventory.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String productName;
    private int  productId;

    private int productQuantity;

    Product(String productName, int productId, int productQuantity) {
        this.productName = productName;
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    Product(){
        productName = "";
        productId = 0;
        productQuantity = 0;
    }

    public String getproductName() {
        return productName;
    }

    public void setproductName(String productName) {
        this.productName = productName;
    }

    public int getproductId() {
        return productId;
    }

    public void setproductId(int productId) {
        this.productId = productId;
    }

    public int getproductQuantity() {
        return productQuantity;
    }

    public void setproductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
