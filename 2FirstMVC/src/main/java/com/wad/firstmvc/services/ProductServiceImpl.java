package com.wad.firstmvc.services;

import com.wad.firstmvc.domain.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {
    List<Product> products = new ArrayList<>(List.<Product>of(
            new Product(13L,"icecream"),
            new Product(25L,"car")
    ));

    public List<Product> findAll(){
        return products;
    }

    @Override
    public void save(Product p) {
        if(p.getId()==null)
            p.setId(new Random().nextLong());
        products.add(p);  }}
