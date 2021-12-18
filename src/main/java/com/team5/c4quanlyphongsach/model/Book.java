package com.team5.c4quanlyphongsach.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    private Boolean status;

    @ManyToOne(targetEntity = Publisher.class)
    private Publisher publisher;

    @ManyToOne(targetEntity = Type.class)
    @JoinColumn()
    private Type type;

    @ManyToOne(targetEntity = LocationBook.class)
    private LocationBook locationBook;

    @ManyToOne
    private Customer customer;


}
