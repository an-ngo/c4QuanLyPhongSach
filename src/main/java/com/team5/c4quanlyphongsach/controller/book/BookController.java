package com.team5.c4quanlyphongsach.controller.book;

import com.team5.c4quanlyphongsach.model.Book;
import com.team5.c4quanlyphongsach.model.Room;
import com.team5.c4quanlyphongsach.service.book.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private IBookService bookService;


    @GetMapping
    public ResponseEntity<Iterable<Book>> findAllRoom() {
        List<Book> books = (List<Book>) bookService.findAll();
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findRoomById(@PathVariable Long id) {
        Optional<Book> bookOptional = bookService.findById(id);
        if (!bookOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(bookOptional.get(), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Book> saveRoom(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBlog(@PathVariable Long id, @RequestBody Book book) {
        Optional<Book> bookOptional = bookService.findById(id);
        if (!bookOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        book.setId(bookOptional.get().getId());
        return new ResponseEntity<>(bookService.save(book), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteCustomer(@PathVariable Long id) {
        Optional<Book> bookOptional = bookService.findById(id);
        if (!bookOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookService.remove(id);
        return new ResponseEntity<>(bookOptional.get(), HttpStatus.NO_CONTENT);
    }

}
