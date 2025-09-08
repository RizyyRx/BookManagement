package com.challenge.BookManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.BookManagement.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
