package com.team5.c4quanlyphongsach.controller.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.c4quanlyphongsach.model.Book;
import com.team5.c4quanlyphongsach.model.Customer;
import com.team5.c4quanlyphongsach.model.LocationBook;
import com.team5.c4quanlyphongsach.model.users.Roles;
import com.team5.c4quanlyphongsach.service.book.IBookService;
import com.team5.c4quanlyphongsach.service.customer.ICustomerService;
import com.team5.c4quanlyphongsach.service.locationBook.ILocationBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
@CrossOrigin("*")
public class BookController {
    @Autowired
    private IBookService bookService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ILocationBookService locationBookService;

    @Autowired
    Environment env;



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

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Book>> findAllBookByCustomerId(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<Book> books = bookService.findAllByCustomer_Id(id);
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
    }

    @GetMapping("/locationBook/{id}")
    public ResponseEntity<List<Book>> findAllBookByLocationBookId(@PathVariable Long id) {
        Optional<LocationBook> locationBook = locationBookService.findById(id);
        if (!locationBook.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<Book> books = bookService.findAllByLocationBook_Id(id);
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
    }

    @GetMapping("/{locationBookId}/{customerId}")
    public ResponseEntity<List<Book>> findAllBookByLocationBookIdAndCustomerId(@PathVariable("locationBookId") Long locationBookId,@PathVariable("customerId") Long customerId) {
     return new ResponseEntity<>(bookService.findAllByLocationBook_IdAndCustomer_Id(locationBookId,customerId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> saveRoom(@RequestPart("file") MultipartFile file,@RequestPart ("newBook") String book) {
            MultipartFile multipartFile = file;
            String file1 = multipartFile.getOriginalFilename();
            try {
                Book book1 = new ObjectMapper().readValue(book,Book.class);
                book1.setImage(file1);
                bookService.save(book1);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            String fileUpLoad = env.getProperty("upload.path");
            try {
                FileCopyUtils.copy(multipartFile.getBytes(),new File(fileUpLoad + file1));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(HttpStatus.OK);

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
        return new ResponseEntity<>(bookOptional.get(), HttpStatus.OK);
    }

}
