package com.example.mvcproducts.repositories;

import com.example.mvcproducts.domain.ProductOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<ProductOrder,Long> {
}
