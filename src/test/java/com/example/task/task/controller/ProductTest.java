package com.example.task.task.controller;

import com.example.task.task.model.Category;
import com.example.task.task.model.Product;
import com.example.task.task.repository.ProductRepo;
import com.example.task.task.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductTest {

  @InjectMocks Product product;
  @InjectMocks ProductService productService;

  @Mock ProductRepo productRepo;

  Category cat1 = new Category((long) 1, "Shoe");
  Category cat2 = new Category((long) 2, "Building");
  Category cat3 = new Category((long) 3, "Food");

  Product prod1 = new Product((long) 1, "Shoe", cat1);
  Product prod2 = new Product((long) 2, "Building", cat2);
  Product prod3 = new Product((long) 3, "Food", cat3);

  @Test
  void testGetAllProducts() {
    List<Product> list = new ArrayList<>(Arrays.asList(prod1, prod2, prod3));

    when(productRepo.findAll()).thenReturn(list);

    // when
    List<Product> result = productService.getAllProduct();

    // then
    assertThat(result.size()).isEqualTo(3);

    assertThat(result.get(0).equals(prod1));
    assertThat(result.get(1).equals(prod2));
    assertThat(result.get(2).equals(prod3));
  }

  @Test
  void testGetProductById() {

    // Given
    when(productRepo.findById(prod1.getId())).thenReturn(Optional.of(prod1));

    // when
    ResponseEntity<?> responseEntity = productService.getProductById(prod1.getId());

    // then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void testAddProduct() {
//    // Given
//
//    when(productRepo.save(any(Product.class))).thenReturn(prod1);
//    when(productRepo.findByName(product.getName())).thenReturn(Optional.ofNullable(prod1));
//
//    // when
//    ResponseEntity<?> responseEntity = productService.addProduct(prod1, (long) 1);
//
//    // then
//    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
  }

  @Test
  void testDeleteProduct() {}
}
