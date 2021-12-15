package com.team5.c4quanlyphongsach.repository.publisher;

import com.team5.c4quanlyphongsach.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPublisherRepository extends JpaRepository<Publisher,Long> {
}
