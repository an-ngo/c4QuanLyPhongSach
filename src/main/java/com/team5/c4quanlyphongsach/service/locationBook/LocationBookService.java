package com.team5.c4quanlyphongsach.service.locationBook;

import com.team5.c4quanlyphongsach.model.LocationBook;
import com.team5.c4quanlyphongsach.repository.locationBook.ILocationBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationBookService implements ILocationBookService {

    @Autowired
    private ILocationBookRepository locationBookRepository;

    @Override
    public Iterable<LocationBook> findAll() {
        return locationBookRepository.findAll();
    }

    @Override
    public Optional<LocationBook> findById(Long id) {
        return locationBookRepository.findById(id);
    }

    @Override
    public LocationBook save(LocationBook locationBook) {
        return locationBookRepository.save(locationBook);
    }

    @Override
    public void remove(Long id) {
        locationBookRepository.deleteById(id);
    }

    @Override
    public List<LocationBook> findAllByRoom_Id(Long id) {
        return locationBookRepository.findAllByRoom_Id(id);
    }
}
