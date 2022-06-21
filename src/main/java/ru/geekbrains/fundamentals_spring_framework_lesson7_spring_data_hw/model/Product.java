package ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.model;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

   // public Product(Product product) {    }
}
