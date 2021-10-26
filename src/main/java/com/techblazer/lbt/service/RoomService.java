package com.techblazer.lbt.service;

import com.techblazer.lbt.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    List<Room> getRooms();

    Optional<Room> getRoomByType(String roomType);

    Optional<Room> getRoomById(Long roomId);

    Room saveRoom(Room room);

    void deleteRoom(Long roomId);
}
