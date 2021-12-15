package com.team5.c4quanlyphongsach.repository.type;

import com.team5.c4quanlyphongsach.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypeRepository extends JpaRepository<Type,Long> {
}
