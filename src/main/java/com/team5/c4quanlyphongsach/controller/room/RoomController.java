package com.team5.c4quanlyphongsach.controller.room;


import com.team5.c4quanlyphongsach.model.Customer;
import com.team5.c4quanlyphongsach.model.Room;
import com.team5.c4quanlyphongsach.service.room.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/rooms")
@CrossOrigin("*")
public class RoomController {
    @Autowired
    private IRoomService roomService;

    @Autowired
    private HttpSession httpSession;


    @GetMapping
    public ModelAndView showALl(){
        Customer customer = (Customer) httpSession.getAttribute("customer");

        return new ModelAndView("/homePage").addObject("customer",customer);
    }

    @GetMapping("/customer")
    public ResponseEntity<List<Room>> findAllByCustomer(){
        Customer customer = (Customer) httpSession.getAttribute("customer");
        List<Room>rooms = roomService.findAllByCustomer(customer);
        if (rooms.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(rooms,HttpStatus.OK);
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

    @GetMapping("/{id}")
    public ResponseEntity<Room> findRoomById(@PathVariable Long id) {
        Optional<Room> roomOptional = roomService.findById(id);
        if (!roomOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(roomOptional.get(), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Room> saveRoom(@RequestBody Room room) {
        return new ResponseEntity<>(roomService.save(room), HttpStatus.CREATED);
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

}
