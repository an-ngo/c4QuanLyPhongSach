package com.team5.c4quanlyphongsach.repository.room;

import com.team5.c4quanlyphongsach.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomRepository extends JpaRepository<Room,Long> {
}
