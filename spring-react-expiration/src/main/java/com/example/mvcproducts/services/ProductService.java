package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Product;

import java.util.List;

public interface ProductService {
  void saveAll(Iterable<Product> products);
  List<Product> findAll();
  Product save(Product product);
}
