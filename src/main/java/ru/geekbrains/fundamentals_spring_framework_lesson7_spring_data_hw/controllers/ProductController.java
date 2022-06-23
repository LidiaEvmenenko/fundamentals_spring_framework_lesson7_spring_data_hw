package ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.controllers;
import org.springframework.http.HttpStatus;
import ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.dto.ProductDto;
import ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.exceptions.ResourceNotFoundException;
import ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.model.Category;
import ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.model.Product;
import ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.services.CategoryService;
import ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {
    private ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/products")
    public Page<ProductDto> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex){
        System.out.println("pageindex = "+pageIndex);
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, 5).map(ProductDto::new);
    }

    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto findById(@PathVariable Long id){
        System.out.println("id = "+id);
        return new ProductDto(productService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product id = "+ id +" not found")));
    }

    @DeleteMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAll(){
        productService.deleteAll();
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto save(@RequestBody ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByTitle(productDto
                .getCategoryTitle())
                .orElseThrow(()-> new ResourceNotFoundException("Category title = "+ productDto.getCategoryTitle() +" not found"));
        product.setCategory(category);
        productService.save(product);
        return new ProductDto(product);
    }

    @PutMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto updateProduct( @RequestBody ProductDto productDto) {
        Product product = productService.findByIdFromUpdate(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByTitle(productDto
                .getCategoryTitle())
                .orElseThrow(()-> new ResourceNotFoundException("Category title = "+ productDto.getCategoryTitle() +" not found"));
        product.setCategory(category);
        productService.save(product);
        return new ProductDto(product);
    }
}
