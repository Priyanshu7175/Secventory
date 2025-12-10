package com.example.Secventory.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;
    @GetMapping("/api/public/product/getProduct")
    public ResponseEntity<Product> getProductDetails(){
        Product product = productService.getProductInfo();
        return new ResponseEntity(product, HttpStatus.OK);
    }

    @PostMapping("/api/admin/product/addProduct")
    public ResponseEntity<> addProduct(Product product){
        productService.addProductInfo(product);
        return null;
    }
}
