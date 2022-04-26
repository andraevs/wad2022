package com.example.mvcproducts.repositories;

import com.example.mvcproducts.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
  User findByUsername(String username);
}
