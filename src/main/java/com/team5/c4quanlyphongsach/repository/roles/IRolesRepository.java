package com.team5.c4quanlyphongsach.repository.roles;

import com.team5.c4quanlyphongsach.model.users.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolesRepository extends JpaRepository<Roles,Long> {
}
