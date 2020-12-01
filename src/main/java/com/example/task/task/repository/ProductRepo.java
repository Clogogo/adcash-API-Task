package com.example.task.task.repository;


import com.example.task.task.model.Category;
import com.example.task.task.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long > {
    List<Product> findByCategoryId(Long categoryId);

    Optional<Product> findNameByCategoryId(Long categoryId);
    Optional<Product> findIdByCategoryId(Long categoryId);
    Optional<Product> findCategoryIdByName (String productName);
    Optional<Product> findByName(String name);


}
