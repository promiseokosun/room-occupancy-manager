package com.techblazer.lbt.repo;

import com.techblazer.lbt.constant.RoomStatuses;
import com.techblazer.lbt.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {
    Optional<Room> findByType(String roomType);

    List<Room> findByStatusOrderByPriceDesc(RoomStatuses status);

    List<Room> findByStatusAndTypeOrderByPriceDesc(RoomStatuses status, String roomType);
}
