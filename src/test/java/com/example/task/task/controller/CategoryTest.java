package com.example.task.task.controller;

import com.example.task.task.model.Category;
import com.example.task.task.repository.CategoryRepo;
import com.example.task.task.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryTest {

  @InjectMocks CategoryService categoryService;

  @InjectMocks CategoryController categoryController;

  @Mock CategoryRepo categoryRepo;

  Category cat1 = new Category((long) 1, "Shoe");
  Category cat2 = new Category((long) 2, "Building");
  Category cat3 = new Category((long) 3, "Food");

  @Test
  public void getAllCategories() {
    // Given
    List<Category> list = new ArrayList<>(Arrays.asList(cat1, cat2, cat3));

    when(categoryRepo.findAll()).thenReturn(list);

    // when
    List<Category> result = categoryService.getAllCategories();

    // then
    assertThat(result.size()).isEqualTo(3);

    assertThat(result.get(0).equals(cat1));
    assertThat(result.get(1).equals(cat2));
    assertThat(result.get(2).equals(cat3));
  }

  @Test
  public void testGetOneCategory() {
    // Given
    when(categoryRepo.findById(cat1.getId())).thenReturn(Optional.of(cat1));

    // when
    ResponseEntity<?> responseEntity = categoryService.getCategoryById(cat1.getId());

    // then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  public void testAddCategory() {

    // Given
    when(categoryRepo.save(any(Category.class))).thenReturn(cat1);

    // when
    ResponseEntity<?> responseEntity = categoryService.addCategory(cat1);

    // then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
  }

  @Test
  public void testUpdateCategory() {

    // Given
    when(categoryRepo.save(any(Category.class))).thenReturn(cat1);
    when(categoryRepo.findById(cat1.getId())).thenReturn(Optional.of(cat1));

    // when
    ResponseEntity<?> responseEntity = categoryService.updateCategory(cat1);

    // then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  public void testDeleteCategory() {

    // Given
    when(categoryRepo.findById(cat1.getId())).thenReturn(Optional.of(cat1));

    // when
    ResponseEntity<?> responseEntity = categoryService.deleteCategory(cat1.getId());

    // then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
  }
}
