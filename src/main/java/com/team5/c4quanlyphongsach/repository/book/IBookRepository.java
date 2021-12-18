package com.team5.c4quanlyphongsach.repository.book;

import com.team5.c4quanlyphongsach.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllByCustomer_Id(Long id);
    List<Book> findAllByLocationBook_Id(Long id);
}
