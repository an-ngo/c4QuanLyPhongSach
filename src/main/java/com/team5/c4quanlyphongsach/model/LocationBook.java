package com.team5.c4quanlyphongsach.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Long capacity;

    private Long current;

    private String image;



    @OneToMany()
    @JsonBackReference
    private List<Book> bookList;

    @ManyToOne(targetEntity = Room.class)
    private Room room;

    public LocationBook(String name, String description, Long capacity, Long current, String image, List<Book> bookList, Room room) {
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.current = current;
        this.image = image;
        this.bookList = bookList;
        this.room = room;
    }

    public LocationBook(String name, Long capacity, Long current, String image, Room room) {
        this.name = name;
        this.capacity = capacity;
        this.current = current;
        this.image = image;
        this.room = room;
    }
}
