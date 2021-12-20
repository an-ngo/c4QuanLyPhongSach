package com.team5.c4quanlyphongsach.service.book;

import com.team5.c4quanlyphongsach.model.Book;
import com.team5.c4quanlyphongsach.repository.book.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService{
    @Autowired
    private IBookRepository bookRepository;

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void remove(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findAllByCustomer_Id(Long id) {
        return bookRepository.findAllByCustomer_Id(id);
    }

    @Override
    public List<Book> findAllByLocationBook_Id(Long id) {
        return bookRepository.findAllByLocationBook_Id(id);
    }

    @Override
    public List<Book> findAllByLocationBook_IdAndCustomer_Id(Long locationBookId, Long customerId) {
        return bookRepository.findAllByLocationBook_IdAndCustomer_Id(locationBookId,customerId);
    }

    @Override
    public List<Book> findAllByLocationBookNullAndCustomer_Id(Long id) {
        return bookRepository.findAllByLocationBookNullAndCustomer_Id(id);
    }

    @Override
    public void putBookIntoBookshelf(Long locationBookId, Long bookId, Long customerId) {
        bookRepository.putBookIntoBookshelf(locationBookId,bookId,customerId);
    }

    @Override
    public void returnBookComeToCart(Long locationBookId, Long bookId, Long customerId) {
        bookRepository.returnBookComeToCart(locationBookId,bookId,customerId);
    }








    @Override
    public void updateBookRate(Float rate, Long idBook) {
        bookRepository.updateBookRate(rate,idBook);
    }
}
