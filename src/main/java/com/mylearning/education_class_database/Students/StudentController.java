package com.mylearning.education_class_database.Students;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    @GetMapping
    public List<String> getProducts() {
        List<String> productsList = new ArrayList<>();
        productsList.add("Honey");
        productsList.add("Almond");
        return productsList;
    }
    @PostMapping
    public String createProduct() {
        return "Product is saved successfully";
    }

}