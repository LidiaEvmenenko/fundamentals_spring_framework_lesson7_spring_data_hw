package ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<Product> findAll(int pageIndex, int pageSize) {
     //   if (pageIndex < 0 ){ return null;}
        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
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

    public void deleteAll(){
        productRepository.deleteAll();
    }
    public Product findByIdFromUpdate(Long id){
        return productRepository.getReferenceById(id);
    }
}
