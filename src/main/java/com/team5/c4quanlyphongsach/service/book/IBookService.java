package com.team5.c4quanlyphongsach.service.book;

import com.team5.c4quanlyphongsach.model.Book;
import com.team5.c4quanlyphongsach.service.IGeneralService;

import java.util.List;

public interface IBookService extends IGeneralService<Book> {
    List<Book> findAllByCustomer_Id(Long id);
    List<Book> findAllByLocationBook_Id(Long id);
    List<Book> findAllByLocationBook_IdAndCustomer_Id(Long locationBookId,Long customerId);
    List<Book> findAllByLocationBookNullAndCustomer_Id(Long id);
    void putBookIntoBookshelf(Long locationBookId,Long bookId,Long customerId);



}
