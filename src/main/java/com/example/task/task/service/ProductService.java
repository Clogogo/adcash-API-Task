package com.example.task.task.service;

import com.example.task.task.model.Category;
import com.example.task.task.model.Product;
import com.example.task.task.repository.CategoryRepo;
import com.example.task.task.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

  @Autowired private ProductRepo productRepo;

  @Autowired private CategoryRepo categoryRepo;

  // Getting the list of product in a  category;
  public List<Product> getAllProduct() {
    return new ArrayList<>(productRepo.findAll());
  }

  // Getting the list of product in a category;
  public ResponseEntity<?> getProductById(Long productId) {
    Optional<Product> category = productRepo.findById(productId);
    return category
        .map(response -> ResponseEntity.ok().body(response))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  // add product
  public ResponseEntity<?> addProduct(Product product, Long categoryId) {

    if (categoryRepo.findById(categoryId).isEmpty()) {
      return new ResponseEntity<>("Category Not Found", HttpStatus.NOT_FOUND);
    } else if (productRepo.findCategoryIdByName(product.getName()).isPresent()) {
      return new ResponseEntity<>("Product Already Added", HttpStatus.CONFLICT);
    } else {
      product.setCategory(new Category(categoryId, ""));
      productRepo.save(product);
      return new ResponseEntity<>(product.getName() + " Added", HttpStatus.CREATED);
    }
  }

  // update product;
  public void updateProduct(Product product) {

    productRepo
        .findById(product.getId())
        .ifPresentOrElse(
            updatedProduct -> {
              updatedProduct.setName(product.getName());
              productRepo.save(updatedProduct);
            },
            () -> {
              throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            });
      }


  // delete Product
  public boolean deleteProduct(Long productId) {

    if (productRepo.findById(productId).isEmpty()) {
      return false;
    } else {
      productRepo.deleteById(productId);
      return true;
    }
  }
}
