package com.team5.c4quanlyphongsach.repository.locationBook;

import com.team5.c4quanlyphongsach.model.LocationBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILocationBookRepository extends JpaRepository<LocationBook,Long> {
}
