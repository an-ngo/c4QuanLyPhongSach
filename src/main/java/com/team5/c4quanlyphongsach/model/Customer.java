package com.team5.c4quanlyphongsach.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "^[0-9a-zA-z]{8}$", message = "Mat khau khong hop le")
//    @Size(max = 18, min = 8,message = "Do dai mat khau khong hop le")
    private String password;

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "vi_VN", timezone = "Asia/Ho_Chi_Minh")
    private java.sql.Date dateOfBirth;

    private String phoneNumber;

    private String avatar;

    private Double money;

    @OneToMany
    private List<Book> bookList;

    @OneToMany
    private List<Room> roomList;

    public Customer(String email, String password, String name, Date dateOfBirth, String phoneNumber, Double money) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.money = money;
    }
}
