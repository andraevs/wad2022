package com.example.mvcproducts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordTests {
    static final String  PASSWORD = "password";

    @Test
    void testNoOp(){
        PasswordEncoder noOp = NoOpPasswordEncoder.getInstance();
        System.out.println(noOp.encode(PASSWORD));
    }

    @Test
    void testBCrypt(){
        PasswordEncoder bcrypt = new BCryptPasswordEncoder(12); // by default 10
        String s= bcrypt.encode(PASSWORD);
        String t= bcrypt.encode(PASSWORD);
        System.out.println(s);
        System.out.println(t);
        Assertions.assertTrue(bcrypt.matches(PASSWORD,s));
        Assertions.assertTrue(bcrypt.matches(PASSWORD,t));
    }
}
