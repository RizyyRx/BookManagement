package com.challenge.BookManagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.challenge.BookManagement.dto.BookDto;
import com.challenge.BookManagement.entity.Book;
import com.challenge.BookManagement.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{
	
	private BookRepository bookRepo;

	public BookServiceImpl(BookRepository bookRepo) {
		super();
		this.bookRepo = bookRepo;
	}

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepo.findAll()
                       .stream()
                       .map(this::convertToDto)
                       .collect(Collectors.toList());
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        return convertToDto(book);
    }

    @Override
    public BookDto createBook(BookDto dto) {
        Book book = convertToEntity(dto);
        Book saved = bookRepo.save(book);
        return convertToDto(saved);
    }

    @Override
    public BookDto updateBook(Long id, BookDto dto) {
        Book existing = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        existing.setTitle(dto.getTitle());
        existing.setAuthor(dto.getAuthor());
        existing.setYearPublished(dto.getYearPublished());
        Book updated = bookRepo.save(existing);
        return convertToDto(updated);
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepo.existsById(id)) {
            throw new RuntimeException("Book not found with id: " + id);
        }
        bookRepo.deleteById(id);
    }

    private BookDto convertToDto(Book book) {
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setYearPublished(book.getYearPublished());
        return dto;
    }

    private Book convertToEntity(BookDto dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setYearPublished(dto.getYearPublished());
        return book;
    }

}
