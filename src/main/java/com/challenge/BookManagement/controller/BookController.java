package com.challenge.BookManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.BookManagement.dto.BookDto;
import com.challenge.BookManagement.entity.Book;
import com.challenge.BookManagement.service.BookService;

@RestController
@RequestMapping("api/book")
public class BookController {
	
	public BookController(BookService bookService) {
		super();
		this.bookService = bookService;
	}

	private BookService bookService;
	
    @GetMapping("/home")
    public String home() {
        return "Welcome, you are logged in!";
    }
    
	@GetMapping
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<List<BookDto>> getAllBooks(){
		List<BookDto> books = bookService.getAllBooks();
		return new ResponseEntity<>(books,HttpStatus.OK);
	}
	
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<BookDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookDto> create(@RequestBody BookDto dto) {
        return new ResponseEntity<>(bookService.createBook(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookDto> update(@PathVariable Long id, @RequestBody BookDto dto) {
        return ResponseEntity.ok(bookService.updateBook(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>("Book with id: " + id + " deleted successfully", HttpStatus.OK);
    }


}
