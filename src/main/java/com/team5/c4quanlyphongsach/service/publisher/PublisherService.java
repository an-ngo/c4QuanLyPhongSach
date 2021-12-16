package com.team5.c4quanlyphongsach.service.publisher;

import com.team5.c4quanlyphongsach.model.Publisher;
import com.team5.c4quanlyphongsach.repository.publisher.IPublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublisherService implements IPublisherService {

    @Autowired
    private IPublisherRepository publisherRepository;

    @Override
    public Iterable<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Optional<Publisher> findById(Long id) {
        return publisherRepository.findById(id);
    }

    @Override
    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public void remove(Long id) {
        publisherRepository.deleteById(id);
    }
}
