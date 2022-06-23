package ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.model.Category;
import ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.repositories.CategoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }
}
