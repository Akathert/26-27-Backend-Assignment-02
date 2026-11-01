package com.example.jpaexample.service;

import com.example.jpaexample.domain.Author;
import com.example.jpaexample.domain.Book;
import com.example.jpaexample.dto.BookInfoResponseDto;
import com.example.jpaexample.dto.BookSaveRequestDto;
import com.example.jpaexample.repository.AuthorRepository;
import com.example.jpaexample.repository.BooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BooksRepository booksRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    public BookInfoResponseDto saveBook(BookSaveRequestDto bookSaveRequestDto) {
        Author author = authorRepository.findById(bookSaveRequestDto.authorId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 작가입니다."));

        Book book = Book.builder()
                .title(bookSaveRequestDto.title())
                .genre(bookSaveRequestDto.genre())
                .author(author)
                .build();

        booksRepository.save(book);

        return BookInfoResponseDto.from(book);
    }

    @Transactional(readOnly = true)
    public BookInfoResponseDto getBook(Long bookId) {
        Book book = booksRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 도서 정보를 찾을 수 없습니다."));

        return BookInfoResponseDto.from(book);
    }

    @Transactional
    public BookInfoResponseDto updateBook(Long bookId, BookSaveRequestDto bookSaveRequestDto) {
        Book book = booksRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 도서 정보를 찾을 수 없습니다."));

        book.update(bookSaveRequestDto.title(),
                bookSaveRequestDto.genre());

        return BookInfoResponseDto.from(book);
    }

    @Transactional
    public void deleteBook(Long bookId) {
        booksRepository.deleteById(bookId);
    }

    @Transactional(readOnly = true)
    public List<BookInfoResponseDto> getAllBook() {
        return booksRepository.findAll()
                .stream()
                .map(BookInfoResponseDto::from)
                .toList();
    }
}