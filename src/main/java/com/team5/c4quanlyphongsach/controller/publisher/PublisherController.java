package com.team5.c4quanlyphongsach.controller.publisher;

import com.team5.c4quanlyphongsach.service.customer.ICustomerService;
import com.team5.c4quanlyphongsach.service.locationBook.ILocationBookService;
import com.team5.c4quanlyphongsach.service.publisher.IPublisherService;
import com.team5.c4quanlyphongsach.service.room.IRoomService;
import com.team5.c4quanlyphongsach.service.type.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

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



}
