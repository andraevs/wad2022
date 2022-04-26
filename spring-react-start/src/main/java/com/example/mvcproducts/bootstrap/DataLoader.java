package com.example.mvcproducts.bootstrap;

import com.example.mvcproducts.domain.Product;
import com.example.mvcproducts.domain.ProductType;
import com.example.mvcproducts.domain.Role;
import com.example.mvcproducts.domain.User;
import com.example.mvcproducts.services.ProductService;
import com.example.mvcproducts.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DataLoader implements CommandLineRunner {
  private final ProductService productService;
  private final UserService userService;

  public DataLoader(ProductService productService, UserService userService) {
    this.productService = productService;
    this.userService = userService;
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

    PasswordEncoder bcrypt = new BCryptPasswordEncoder();
    User user1=new User("user1",bcrypt.encode("user1"));
    user1.getRoles().add(Role.ROLE_USER);
    User user2=new User("user2",bcrypt.encode("user2"));
    user2.getRoles().add(Role.ROLE_ADMIN);
    userService.save(user1);
    userService.save(user2);
  }
}
