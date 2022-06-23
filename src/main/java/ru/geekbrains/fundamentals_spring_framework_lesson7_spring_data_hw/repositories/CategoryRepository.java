package ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.model.Category;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByTitle(String title);
}
