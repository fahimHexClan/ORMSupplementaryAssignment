package Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Author {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name;
   @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Book> books = new ArrayList<>();
   private String country;

   public Author() {
   }

   public Author(Long id, String name, List<Book> books, String country) {
      this.id = id;
      this.name = name;
      this.books = books;
      this.country = country;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public List<Book> getBooks() {
      return books;
   }

   public void setBooks(List<Book> books) {
      this.books = books;
   }

   public String getCountry() {
      return country;
   }

   public void setCountry(String country) {
      this.country = country;
   }
}