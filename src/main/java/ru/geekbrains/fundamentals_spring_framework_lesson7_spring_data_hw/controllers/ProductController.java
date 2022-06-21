package ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.controllers;
import ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.model.Product;
import ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping("/products")
    public Page<Product> findAll(@RequestParam(name = "page") int pageIndex){
        return productService.findAll(pageIndex - 1, 10);
    }

    @GetMapping("/products/{id}")
    public Product findById(@PathVariable Long id){
        return productService.findById(id).get();
    }

    @GetMapping("/products/delete/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

    @PostMapping("/products")
    @ResponseBody
    public void save(@RequestBody Product product ){
        productService.save(product);
    }

    @GetMapping("/products/price")
    public List<Product> findPrice(@RequestParam(name = "min", defaultValue = "-1") int minPrice, @RequestParam(name = "max", defaultValue = "-1") int maxPrice){
        if (minPrice == -1) {
            return productService.findByPriceLessThanEqual(maxPrice);
        }
        if (maxPrice == -1){
            return productService.findByPriceGreaterThanEqual(minPrice);
        }
        return productService.findByPriceBetween(minPrice, maxPrice);
    }
}
