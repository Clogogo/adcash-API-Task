package com.example.task.task.service;

import com.example.task.task.model.Category;
import com.example.task.task.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

  @Autowired private CategoryRepo catRepo;

  // Getting the list of all categories;
  public List<Category> getAllCategories() {
    return new ArrayList<>(catRepo.findAll());
  }

  // Getting the list of One categories;
  public ResponseEntity<?> getCategoryById(Long categoryId) {
    Optional<Category> category = catRepo.findById(categoryId);
    return category
        .map(response -> ResponseEntity.ok().body(response))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  // Create category;
  public ResponseEntity<?> addCategory(Category category) {
    if (catRepo.findByName(category.getName()).isPresent()) {
      return new ResponseEntity<>("Category Already Added", HttpStatus.CONFLICT);
    } else {
      catRepo.save(category);
      return new ResponseEntity<>(HttpStatus.CREATED);
    }
  }

  // update category;
  public ResponseEntity<?> updateCategory(Category category) {

    if (catRepo.findById(category.getId()).isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      if (catRepo.findByName(category.getName()).isPresent()) {
        return new ResponseEntity<>(HttpStatus.CONFLICT);
      } else {
        catRepo.save(category);
        return new ResponseEntity<>(HttpStatus.OK);
      }
    }
  }

  // delete category;
  public ResponseEntity<?> deleteCategory(Long categoryId) {

    if (catRepo.findById(categoryId).isPresent()) {
      catRepo.deleteById(categoryId);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
