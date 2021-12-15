package com.team5.c4quanlyphongsach.service.book;

import com.team5.c4quanlyphongsach.model.Book;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService implements IBookService{
    @Override
    public Iterable<Book> findAll() {
        return null;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
