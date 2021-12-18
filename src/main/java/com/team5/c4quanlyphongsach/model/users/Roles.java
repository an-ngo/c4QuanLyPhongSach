package com.team5.c4quanlyphongsach.model.users;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class Roles implements GrantedAuthority{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Roles(Long id) {
        this.id = id;
    }

    public Roles(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
