package com.example.task.task.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "product")
public class Product implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  private String name;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @OneToOne(fetch = FetchType.LAZY)
  private Category category;

  public Product(Long id, String name) {
    this.id=id;
    this.name = name;

  }
}
