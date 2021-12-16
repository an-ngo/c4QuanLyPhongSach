package com.team5.c4quanlyphongsach.service.room;

import com.team5.c4quanlyphongsach.model.Customer;
import com.team5.c4quanlyphongsach.model.Room;
import com.team5.c4quanlyphongsach.service.IGeneralService;

import java.util.List;

public interface IRoomService extends IGeneralService<Room> {
    List<Room> findAllByCustomer(Customer customer);

}
