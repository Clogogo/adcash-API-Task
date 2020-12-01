package com.example.task.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "category")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
//
//  public Category(String name) {
//    this.name = name;
//  }


}
