package com.example.mvcproducts.restcontrollers;


import com.example.mvcproducts.domain.User;
import com.example.mvcproducts.dto.UserDTO;
import com.example.mvcproducts.security.JwtUtil;
import com.example.mvcproducts.security.UserRepoUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/users")
public class UserController {

    private final JwtUtil jwtUtil;
    private final UserRepoUserDetailsService userDetailsService;

    public UserController(JwtUtil jwtUtil, UserRepoUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO user){
         User authenticated = userDetailsService.checkUserCredentials(user.getUsername(), user.getPassword());
         if (authenticated != null){
             String jwtToken = jwtUtil.generateToken(user.getUsername());
             System.out.println(jwtToken);
             return new ResponseEntity<>(jwtToken, HttpStatus.OK);
         } else {
             return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
         }
    }
}