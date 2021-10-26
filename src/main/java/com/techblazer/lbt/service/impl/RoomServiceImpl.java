package com.techblazer.lbt.service.impl;

import com.techblazer.lbt.constant.RoomStatuses;
import com.techblazer.lbt.constant.RoomTypes;
import com.techblazer.lbt.model.Room;
import com.techblazer.lbt.repo.RoomRepo;
import com.techblazer.lbt.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.techblazer.lbt.constant.RoomStatuses.AVAILABLE;

@Service
@Slf4j
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepo roomRepo;

    @Override
    public List<Room> getRooms() {
        return roomRepo.findAll();
    }

    @Override
    public Optional<Room> getRoomByType(String roomType) {
        return roomRepo.findByType(roomType);
    }

    @Override
    public Optional<Room> getRoomById(Long roomId) {
        return roomRepo.findById(roomId);
    }

    @Override
    public Room saveRoom(Room room) {
        return roomRepo.save(room);
    }

    @Override
    public void deleteRoom(Long roomId) {
        roomRepo.deleteById(roomId);
    }

    public List<Room> getAvailableRooms() {
        return roomRepo.findByStatusOrderByPriceDesc(AVAILABLE);
    }

    public List<Room> getAvailableRoomsByType(RoomTypes roomType) {
        return roomRepo.findByStatusAndTypeOrderByPriceDesc(AVAILABLE, roomType);
    }

}
