package com.example.task.task.controller;

import com.example.task.task.model.Category;
import com.example.task.task.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class CategoryController {

  @Autowired private CategoryService categoryService;

  // Get all categories
  @GetMapping(value = "/categories/")
  public ResponseEntity<Collection<Category>> getCategories() {
    List<Category> categories = categoryService.getAllCategories();
    if (categories.isEmpty()) {
      return new ResponseEntity<>(categories, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(categories, HttpStatus.OK);
  }

  // Get list of product of a particular category
  @GetMapping(value = "/category/{categoryId}")
  public ResponseEntity<?> getCategory(@PathVariable Long categoryId) {
    return categoryService.getCategoryById(categoryId);
  }

  //
  @PostMapping(value = "/category")
  public ResponseEntity<?> addCategory(@RequestBody Category category) {
    return categoryService.addCategory(category);
  }

  @PutMapping(value = "/category/")
  public ResponseEntity<?> updateCategory(@RequestBody Category category) {
    return categoryService.updateCategory(category);
  }

  @DeleteMapping(value = "/category/delete/{id}")
  public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
    return categoryService.deleteCategory(id);
  }
}
