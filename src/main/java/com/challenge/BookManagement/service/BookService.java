package com.challenge.BookManagement.service;

import java.util.List;

import com.challenge.BookManagement.dto.BookDto;
import com.challenge.BookManagement.entity.Book;

public interface BookService {

    List<BookDto> getAllBooks();
    BookDto getBookById(Long id);
    BookDto createBook(BookDto dto);
    BookDto updateBook(Long id, BookDto dto);
    void deleteBook(Long id);
}
