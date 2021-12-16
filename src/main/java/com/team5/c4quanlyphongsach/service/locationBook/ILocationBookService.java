package com.team5.c4quanlyphongsach.service.locationBook;

import com.team5.c4quanlyphongsach.model.LocationBook;
import com.team5.c4quanlyphongsach.service.IGeneralService;

import java.util.List;

public interface ILocationBookService extends IGeneralService<LocationBook> {
    List<LocationBook> findAllByRoom_Id(Long id);
}
