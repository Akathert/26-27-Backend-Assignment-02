package com.example.jpaexample.repository;

import com.example.jpaexample.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Book, Long> {
}
