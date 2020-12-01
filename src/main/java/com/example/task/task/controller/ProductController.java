package com.example.task.task.controller;


import com.example.task.task.model.Product;
import com.example.task.task.repository.ProductRepo;
import com.example.task.task.service.ProductService;
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
public class ProductController {

  @Autowired private ProductService productService;

  @Autowired
  ProductRepo productRepo;

  @GetMapping(value = "/products")
  public ResponseEntity<Collection<Product>> listAllProducts() {
    List<Product> products = productService.getAllProduct();
    if (products.isEmpty()) {
      return new ResponseEntity<>(products, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(products, HttpStatus.OK);
  }

  @GetMapping("/{productId}")
  public ResponseEntity<?> getProductById(@PathVariable("productId") Long productId) {
      return productService.getProductById(productId);
    }


  @PostMapping("/{categoryId}/product")
  public ResponseEntity<?> addProduct(
      @RequestBody Product product, @PathVariable("categoryId") Long categoryId) {
     return productService.addProduct(product, categoryId);
  }

  @PutMapping("/product")
  public void updateProduct(
      @RequestBody Product product) {
      productService.updateProduct(product);
  }

  @DeleteMapping("/delete/{productId}")
  public ResponseEntity<?> deleteProduct(@PathVariable("productId") Long productId) {
    if (!productService.deleteProduct(productId)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
