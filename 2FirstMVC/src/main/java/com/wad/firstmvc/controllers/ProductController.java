package com.wad.firstmvc.controllers;


import com.wad.firstmvc.domain.Product;
import com.wad.firstmvc.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/products")
public class ProductController {
    //constructor injection of the service
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public String listProducts(Model model){
        model.addAttribute("products",productService.findAll());
        return "products";
    }
    @GetMapping("/new")
    public String showAddProductForm(Model model){
        model.addAttribute("product",new Product());
        return "addproducts";
    }

    @PostMapping("/new")
    public String addProduct(Product product){
        productService.save(product);
        return "redirect:/products";
    }
}
