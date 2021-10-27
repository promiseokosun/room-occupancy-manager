package com.techblazer.lbt.controller;


import com.techblazer.lbt.constant.RoomStatuses;
import com.techblazer.lbt.dto.CreateRoomRequest;
import com.techblazer.lbt.model.Room;
import com.techblazer.lbt.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<?> createRoom(@Valid @RequestBody CreateRoomRequest request) {

        Room room = new Room(
                null,
                request.getType(),
                request.getDescription(),
                request.getPrice(),
                request.getNumber()
        );

        return new ResponseEntity<>(roomService.saveRoom(room), HttpStatus.OK);
    }
}
