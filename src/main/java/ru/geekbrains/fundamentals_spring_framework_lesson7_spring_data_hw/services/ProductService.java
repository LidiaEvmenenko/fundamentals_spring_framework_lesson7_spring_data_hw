package ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.services;
import ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.model.Product;
import ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public void save(Product product){
        productRepository.save(product);
    }

    public List<Product> findByPriceBetween(int minPrice, int maxPrice){
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> findByPriceLessThanEqual(int maxPrice){
        return productRepository.findByPriceLessThanEqual(maxPrice);
    }

    public List<Product> findByPriceGreaterThanEqual(int minPrice){
        return productRepository.findByPriceGreaterThanEqual(minPrice);
    }
}
