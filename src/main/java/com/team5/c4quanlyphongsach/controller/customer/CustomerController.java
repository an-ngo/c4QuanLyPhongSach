package com.team5.c4quanlyphongsach.controller.customer;

import com.team5.c4quanlyphongsach.model.Customer;
import com.team5.c4quanlyphongsach.service.customer.ICustomerService;
import com.team5.c4quanlyphongsach.service.locationBook.ILocationBookService;
import com.team5.c4quanlyphongsach.service.publisher.IPublisherService;
import com.team5.c4quanlyphongsach.service.room.IRoomService;
import com.team5.c4quanlyphongsach.service.type.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    private HttpSession httpSession;

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
    public ResponseEntity<List<Customer>> showAll(){
        List<Customer> customers = (List<Customer>) customerService.findAll();
        if (customers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(customers,HttpStatus.OK);
        }
    }
    @PostMapping
    public ResponseEntity<Customer> save(@Valid @RequestBody Customer customer, BindingResult bindingResult){
        Customer newCustomer = customerService.save(customer);
        httpSession.setAttribute("customer",newCustomer);
        return new ResponseEntity<>(newCustomer,HttpStatus.CREATED);

    }

    @PutMapping("/updateMoney")
    public ResponseEntity<Customer> updateMoney(@RequestParam Double amount){
        Customer customer = (Customer) httpSession.getAttribute("customer");
        customerService.updateBalance(amount+customer.getMoney(),customer.getId());
        return new ResponseEntity<>(HttpStatus.OK);
        }
    @PutMapping
    public ResponseEntity<Customer> update(@Valid @RequestBody Customer customer, BindingResult bindingResult){
        Optional<Customer> customerOptional = customerService.findById(customer.getId());
        if (!customerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            Customer updateCustomer = customerService.save(customer);
            httpSession.setAttribute("customer",updateCustomer);
            return new ResponseEntity<>(updateCustomer,HttpStatus.OK);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> remove(@PathVariable Long id){
        Optional<Customer> customerOptional = customerService.findById(id);
        if (!customerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            customerService.remove(id);
            return new ResponseEntity<>(customerOptional.get(),HttpStatus.OK);
        }
    }
}
