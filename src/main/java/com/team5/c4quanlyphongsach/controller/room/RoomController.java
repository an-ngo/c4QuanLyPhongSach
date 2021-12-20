package com.team5.c4quanlyphongsach.controller.room;


import com.team5.c4quanlyphongsach.model.Book;
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

import javax.persistence.criteria.Root;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
            room.setCustomer(customerOptional.get());
            customerService.save(customerOptional.get());
            Room room1 = roomService.save(room);
//            fix cung ke
            if (room.getName().equals("Modern Room")){
                LocationBook modernBookshelf = new LocationBook("Modern Bookshelf",10L,0L,"modernBookshelf.jpg",room1);
                LocationBook indiaBookshelf = new LocationBook("India Bookshelf",10L,0L,"indiaBookshelf.jpg",room1);
                LocationBook italiaBookshelf = new LocationBook("Italia Bookshelf",10L,0L,"italiaBookshelf.jpg",room1);
                locationBookService.save(modernBookshelf);
                locationBookService.save(indiaBookshelf);
                locationBookService.save(italiaBookshelf);
            }
            else if (room.getName().equals("Working Room")){
                LocationBook simpleBookshelf = new LocationBook("Simple Bookshelf",5L,0L,"simpleBookshelf.jpg",room1);
                locationBookService.save(simpleBookshelf);

            }
            else if (room.getName().equals("Classic Room")){
                List<Book> books = new ArrayList<>();
                LocationBook classicBookshelf = new LocationBook("Classic Bookshelf","",5L,0L,"classicBookshelf.jpg",books,room1);
                LocationBook simpleBookshelf = new LocationBook("Simple Bookshelf","",5L,0L,"simpleBookshelf.jpg",books,room1);
                locationBookService.save(classicBookshelf);
                locationBookService.save(simpleBookshelf);
            }
            else if (room.getName().equals("Noble Room")){
                LocationBook modernBookshelf = new LocationBook("Modern Bookshelf",10L,0L,"modernBookshelf.jpg",room1);
                LocationBook indiaBookshelf = new LocationBook("India Bookshelf",10L,0L,"indiaBookshelf.jpg",room1);
                LocationBook italiaBookshelf = new LocationBook("Italia Bookshelf",10L,0L,"italiaBookshelf.jpg",room1);
                LocationBook royalBookshelf = new LocationBook("Royal Bookshelf",20L,0L,"royalBookshelf.jpg",room1);
                LocationBook classicBookshelf = new LocationBook("Classic Bookshelf",5L,0L,"classicBookshelf.jpg",room1);
                LocationBook simpleBookshelf = new LocationBook("Simple Bookshelf",5L,0L,"simpleBookshelf.jpg",room1);
                locationBookService.save(modernBookshelf);
                locationBookService.save(indiaBookshelf);
                locationBookService.save(italiaBookshelf);
                locationBookService.save(royalBookshelf);
                locationBookService.save(classicBookshelf);
                locationBookService.save(simpleBookshelf);

            }
            else if (room.getName().equals("Vintage Room")){
                LocationBook modernBookshelf = new LocationBook("Modern Bookshelf",10L,0L,"modernBookshelf.jpg",room1);
                LocationBook classicBookshelf = new LocationBook("Classic Bookshelf",5L,0L,"classicBookshelf.jpg",room1);
                LocationBook simpleBookshelf = new LocationBook("Simple Bookshelf",5L,0L,"simpleBookshelf.jpg",room1);
                locationBookService.save(modernBookshelf);
                locationBookService.save(classicBookshelf);
                locationBookService.save(simpleBookshelf);

            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

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
        return new ResponseEntity<>(roomOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/findAllByUserId/{id}")
    public ResponseEntity<Iterable<Room>> findAllByUserId(@PathVariable Long id) {
        List<Room> rooms = (List<Room>) roomService.findAllByCustomerId(id);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

}
