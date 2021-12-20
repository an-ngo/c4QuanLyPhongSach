package com.team5.c4quanlyphongsach.repository.book;

import com.team5.c4quanlyphongsach.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllByCustomer_Id(Long id);
    List<Book> findAllByLocationBook_Id(Long id);
    List<Book> findAllByLocationBook_IdAndCustomer_Id(Long locationBookId,Long customerId);
    List<Book> findAllByLocationBookNullAndCustomer_Id(Long id);


    @Modifying
    @Transactional
    @Query(value = "update Book b set b.locationBook.id = ?1 where b.id = ?2 and b.customer.id = ?3")
    void putBookIntoBookshelf(Long locationBookId,Long bookId,Long customerId);

    @Modifying
    @Transactional
    @Query(value = "update Book b set b.locationBook.id = null where b.locationBook.id=?1 and b.id = ?2 and b.customer.id = ?3")
    void returnBookComeToCart(Long locationBookId,Long bookId,Long customerId);








    @Modifying
    @Transactional
    @Query(value = "update Book b set b.rate=?1 where b.id=?2")
    void updateBookRate(Float rate, Long id);
}
