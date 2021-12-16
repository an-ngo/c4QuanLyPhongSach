package com.team5.c4quanlyphongsach.controller.publisher;

import com.team5.c4quanlyphongsach.model.Publisher;
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

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/publishers")
@CrossOrigin("*")
public class PublisherController {

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
    public ResponseEntity<List<Publisher>> showAll(){
        List<Publisher> publishers = (List<Publisher>) publisherService.findAll();
        if (publishers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(publishers, HttpStatus.OK);
        }

    }
    @PostMapping
    public ResponseEntity<Publisher> save(@RequestBody Publisher publisher){
        return new ResponseEntity<>(publisherService.save(publisher),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Publisher> update(@PathVariable Long id){
        Optional<Publisher> publisherOptional = publisherService.findById(id);
        if (!publisherOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(publisherOptional.get(),HttpStatus.OK);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Publisher> remove(@PathVariable Long id){
        Optional<Publisher> publisherOptional = publisherService.findById(id);
        if (!publisherOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            publisherService.remove(id);
            return new ResponseEntity<>(publisherOptional.get(),HttpStatus.OK);
        }
    }
}
