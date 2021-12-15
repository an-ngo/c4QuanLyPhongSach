package com.team5.c4quanlyphongsach.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Date;

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

    @Pattern(regexp = "^[0-9a-zA-z]{8}$")
    private String password;

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "vi_VN", timezone = "Asia/Ho_Chi_Minh")
    private Date dateOfBirth;

    private String phoneNumber;

    private String avatar;

}
