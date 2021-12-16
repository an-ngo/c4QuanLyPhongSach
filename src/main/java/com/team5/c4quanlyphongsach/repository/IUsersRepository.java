package com.team5.c4quanlyphongsach.repository;

import com.team5.c4quanlyphongsach.model.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsersRepository extends JpaRepository<Users,Long> {
    Users findByName(String name);
}
