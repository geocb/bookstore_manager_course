package com.geovanna.bookstoremanager.service;

import com.geovanna.bookstoremanager.dto.BookDTO;
import com.geovanna.bookstoremanager.dto.MessageResponseDTO;
import com.geovanna.bookstoremanager.entity.Book;
import com.geovanna.bookstoremanager.exception.BookNotFoundException;
import com.geovanna.bookstoremanager.mapper.BookMapper;
import com.geovanna.bookstoremanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public MessageResponseDTO create(BookDTO bookDTO) {
        Book bookToSave = bookMapper.toModel(bookDTO);

        Book savedBook = bookRepository.save(bookToSave);
        return MessageResponseDTO.builder()
                .message("Book created with ID " + savedBook.getId())
                .build();
    }

    public BookDTO findById(Long id) throws BookNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        return bookMapper.toDTO(book);
    }
}
