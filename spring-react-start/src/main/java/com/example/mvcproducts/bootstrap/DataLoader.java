package com.example.mvcproducts.bootstrap;

import com.example.mvcproducts.domain.Product;
import com.example.mvcproducts.domain.ProductType;
import com.example.mvcproducts.services.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DataLoader implements CommandLineRunner {
  private final ProductService productService;

  public DataLoader(ProductService productService) {
    this.productService = productService;
  }

  @Override
  public void run(String... args) throws Exception {
    List<Product> productList = List.of(
            new Product("Car", ProductType.HARDWARE, "Nice car",134000 ),
            new Product("Computer", ProductType.HARDWARE, "Lenovo",2500 ),
            new Product("Office", ProductType.SOFTWARE, "Office",670 ),
            new Product("Windows", ProductType.SOFTWARE, "win",500 ),
            new Product("Cake", ProductType.HARDWARE, "Sweet",20 )
    );
    productService.saveAll(productList);
  }
}
