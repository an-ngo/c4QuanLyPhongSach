package com.team5.c4quanlyphongsach.repository.book;

import com.team5.c4quanlyphongsach.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends JpaRepository<Book,Long> {
}
