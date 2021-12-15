package com.team5.c4quanlyphongsach.controller.type;

import com.team5.c4quanlyphongsach.model.Type;
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

@Controller
@RequestMapping("/types")
@CrossOrigin("*")
public class TypeController {
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
    public ResponseEntity<List<Type>> showAll(){
        List<Type>types = (List<Type>) typeService.findAll();
        return new ResponseEntity<>(types, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Type> save(@RequestBody Type type){
        return new ResponseEntity<>(typeService.save(type),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Type> update(@RequestBody Type type){
        return new ResponseEntity<>(typeService.save(type),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable Long id){
        typeService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
