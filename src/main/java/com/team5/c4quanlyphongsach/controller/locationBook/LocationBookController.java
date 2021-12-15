package com.team5.c4quanlyphongsach.controller.locationBook;

import com.team5.c4quanlyphongsach.model.LocationBook;
import com.team5.c4quanlyphongsach.service.customer.ICustomerService;
import com.team5.c4quanlyphongsach.service.locationBook.ILocationBookService;
import com.team5.c4quanlyphongsach.service.publisher.IPublisherService;
import com.team5.c4quanlyphongsach.service.room.IRoomService;
import com.team5.c4quanlyphongsach.service.type.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/locationBooks")
@CrossOrigin("*")
public class LocationBookController {
    @Autowired
    private IPublisherService publisherService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ILocationBookService bookService;

    @Autowired
    private ILocationBookService locationBookService;

    @Autowired
    private IRoomService roomService;

    @Autowired
    private ITypeService typeService;

    @GetMapping
    public ResponseEntity<List<LocationBook>> showAll(){
        List<LocationBook> locationBooks = (List<LocationBook>) locationBookService.findAll();
        if (locationBooks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(locationBooks,HttpStatus.OK);
        }
    }
    @PostMapping
    public ResponseEntity<LocationBook> save(@RequestBody LocationBook locationBook){
        return new ResponseEntity<>(locationBookService.save(locationBook),HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<LocationBook> update(@RequestBody LocationBook locationBook){
        Optional<LocationBook> locationBookOptional = locationBookService.findById(locationBook.getId());
        if (!locationBookOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            locationBookService.save(locationBook);
            return new ResponseEntity<>(locationBookOptional.get(),HttpStatus.OK);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<LocationBook> remove(@PathVariable Long id){
        Optional<LocationBook> locationBookOptional = locationBookService.findById(id);
        if (!locationBookOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            locationBookService.remove(id);
            return new ResponseEntity<>(locationBookOptional.get(),HttpStatus.OK);
        }
    }
}
