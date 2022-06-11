package ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.repositories;
import ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByPriceBetween(int minPrice, int maxPrice);
    List<Product> findByPriceLessThanEqual(int maxPrice);
    List<Product> findByPriceGreaterThanEqual(int minPrice);
}