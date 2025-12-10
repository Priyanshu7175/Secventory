package com.example.Secventory.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
      ProductRepository productRepository;
    public Product getProductInfo() {

        return null;
    }

    public void addProductInfo(Product product) {
        productRepository.save(product);
    }
}
