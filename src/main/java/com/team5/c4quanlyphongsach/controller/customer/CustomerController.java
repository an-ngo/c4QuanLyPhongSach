package com.team5.c4quanlyphongsach.controller.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.c4quanlyphongsach.model.Customer;
import com.team5.c4quanlyphongsach.model.users.Roles;
import com.team5.c4quanlyphongsach.service.customer.ICustomerService;
import com.team5.c4quanlyphongsach.service.locationBook.ILocationBookService;
import com.team5.c4quanlyphongsach.service.publisher.IPublisherService;
import com.team5.c4quanlyphongsach.service.room.IRoomService;
import com.team5.c4quanlyphongsach.service.type.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.multipart.MultipartFile;



import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
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

    @Autowired
    Environment env;

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
    @PostMapping("/signup")
    public ResponseEntity<Customer> save(@Valid @RequestBody Customer customer){
        Optional<Customer> customer2 = customerService.findByEmail(customer.getEmail());
        if(!customer2.isPresent()) {
            customer.setRoles(new Roles(Long.parseLong("1")));
            customerService.save(customer);
            return new ResponseEntity<>(customer, HttpStatus.CREATED);
        }
        else return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
        }
    }

    @PutMapping("/updateMoney")
    public ResponseEntity<Customer> updateMoney(@RequestParam Double amount){
        Customer customer = (Customer) httpSession.getAttribute("customer");
        customerService.updateBalance(amount+customer.getMoney(),customer.getId());
        return new ResponseEntity<>(HttpStatus.OK);
        }


    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id,@RequestPart("file")MultipartFile file,@RequestPart ("newCustomer") String customer){
        Optional<Customer> customerOptional = customerService.findById(id);
        if (!customerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            MultipartFile multipartFile = file;
            String file1 = multipartFile.getOriginalFilename();
            try {
                Customer customer1 = new ObjectMapper().readValue(customer,Customer.class);
                customer1.setId(customerOptional.get().getId());
                customer1.setAvatar(file1);
                customer1.setRoles(new Roles(Long.parseLong("1")));
                customerService.save(customer1);
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
    }


    @PutMapping("/recharge/{id}/{recharge}")
    public ResponseEntity<Customer>recharge(@PathVariable long id, @PathVariable String recharge){
        Optional<Customer> customerOptional = customerService.findById(id);
        if (!customerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            if(customerOptional.get().getMoney() == null){
                customerOptional.get().setMoney(Double.parseDouble(recharge));
                customerService.save(customerOptional.get());
            }else {
                Double newMoney = customerOptional.get().getMoney() + Double.parseDouble(recharge);
                customerOptional.get().setMoney(newMoney);
                customerService.save(customerOptional.get());
            }
            return new ResponseEntity<>(HttpStatus.OK);
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
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
