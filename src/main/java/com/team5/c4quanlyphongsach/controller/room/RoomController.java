package com.team5.c4quanlyphongsach.controller.room;


import com.team5.c4quanlyphongsach.model.Customer;
import com.team5.c4quanlyphongsach.model.LocationBook;
import com.team5.c4quanlyphongsach.model.Room;
import com.team5.c4quanlyphongsach.service.customer.ICustomerService;
import com.team5.c4quanlyphongsach.service.locationBook.ILocationBookService;
import com.team5.c4quanlyphongsach.service.room.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/rooms")
@CrossOrigin("*")
public class RoomController {
    @Autowired
    private IRoomService roomService;

    @Autowired
    private ILocationBookService locationBookService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private HttpSession httpSession;
// List LocationBook theo RoomID
    @GetMapping("/{id}")
    public ResponseEntity<List<LocationBook>> listLocationBookByRoom(@PathVariable Long id) {
        Optional<Room> roomOptional = roomService.findById(id);
        if (!roomOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<LocationBook> locationBooks = locationBookService.findAllByRoom_Id(id);
            return new ResponseEntity<>(locationBooks, HttpStatus.OK);
        }
    }

    //list phong theo customer
    @GetMapping("/customer")
    public ResponseEntity<List<Room>> findAllByCustomer() {
        Customer customer = (Customer) httpSession.getAttribute("customer");
        List<Room> rooms = roomService.findAllByCustomer(customer);
        if (rooms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(rooms, HttpStatus.OK);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Room>> findAllRoom() {
        List<Room> rooms = (List<Room>) roomService.findAll();
        if (rooms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(rooms, HttpStatus.OK);
        }
    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Room> findRoomById(@PathVariable Long id) {
//        Optional<Room> roomOptional = roomService.findById(id);
//        if (!roomOptional.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<>(roomOptional.get(), HttpStatus.OK);
//        }
//    }

    @PostMapping("/{id}")
    public ResponseEntity<Room> saveRoom(@PathVariable Long id, @RequestBody Room room) {
        Optional<Customer> customerOptional = customerService.findById(id);
        List<Room> rooms = (List<Room>) roomService.findAllByCustomerId(id);
        boolean check = true;
        if(customerOptional.get().getMoney() >= room.getPrice()){
            for (Room value : rooms) {
                if (Objects.equals(value.getCustomer().getId(), id) && Objects.equals(value.getName(), room.getName())) {
                    check = false;
                    break;
                }
            } 
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(check) {
            customerOptional.get().setMoney(customerOptional.get().getMoney() - room.getPrice());
            customerService.save(customerOptional.get());
            roomService.save(room);
        return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateBlog(@PathVariable Long id, @RequestBody Room room) {
        Optional<Room> roomOptional = roomService.findById(id);
        if (!roomOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        room.setId(roomOptional.get().getId());

        return new ResponseEntity<>(roomService.save(room), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Room> deleteCustomer(@PathVariable Long id) {
        Optional<Room> roomOptional = roomService.findById(id);
        if (!roomOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        roomService.remove(id);
        return new ResponseEntity<>(roomOptional.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findAllByUserId/{id}")
    public ResponseEntity<Iterable<Room>> findAllByUserId(@PathVariable Long id) {
        List<Room> rooms = (List<Room>) roomService.findAllByCustomerId(id);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

}
