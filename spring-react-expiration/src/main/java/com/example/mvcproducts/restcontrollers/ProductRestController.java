package com.example.mvcproducts.restcontrollers;

import com.example.mvcproducts.domain.Product;
import com.example.mvcproducts.services.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    //add and also return the location of the new resource
    @PostMapping("")
    public ResponseEntity<?> postProduct(@RequestBody Product p){
        Product savedProduct = productService.save(p);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/products/"+ savedProduct.getId().toString());
        System.out.println(headers);
        return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }

    //TODO 8 Delete mapping
    //TODO 9 Put mapping

}
