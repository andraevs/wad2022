package com.wad.firstmvc.repositories;

import com.wad.firstmvc.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String s);
}
