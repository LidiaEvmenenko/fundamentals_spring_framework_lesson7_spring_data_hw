package ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
//    @Override
//    Page<Product> findAll(Pageable pageable);
}
