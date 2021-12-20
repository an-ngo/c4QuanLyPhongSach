package com.team5.c4quanlyphongsach.repository.locationBook;

import com.team5.c4quanlyphongsach.model.LocationBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ILocationBookRepository extends JpaRepository<LocationBook,Long> {
    List<LocationBook> findAllByRoom_Id(Long id);


    @Modifying
    @Transactional
    @Query(value = "update LocationBook l set l.current  = ?1 where l.id = ?2")
    void updateCurrentOfBookshelf(Long current,Long id);



}
