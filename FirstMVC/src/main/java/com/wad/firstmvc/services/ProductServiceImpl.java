package com.wad.firstmvc.services;

import com.wad.firstmvc.domain.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    List<Product> products = new ArrayList(List.of(
            new Product(13L, "ice cream"),
            new Product(15L, "bike"),
            new Product(19L, "car"))
    );


    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void save(Product p) {
        products.add(p);
    }
}
