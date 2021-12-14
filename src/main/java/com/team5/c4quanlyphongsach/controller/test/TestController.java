package com.team5.c4quanlyphongsach.controller.test;

import com.team5.c4quanlyphongsach.model.users.Users;
import com.team5.c4quanlyphongsach.service.userService.IJwtUsersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    IJwtUsersDetailService jwtUsersDetailService;

    @GetMapping("/")
    public ResponseEntity<Users> test(){
        jwtUsersDetailService.getUserByUsername("name");
        return new ResponseEntity<>(HttpStatus.OK) ;
    }
    @RequestMapping(value = "/he", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Users>> findAllCustomer(){
            return new ResponseEntity<>( HttpStatus.OK);
    }
}
