package com.techblazer.lbt.controller;


import com.techblazer.lbt.dto.BaseResponse;
import com.techblazer.lbt.dto.ReservationResponse;
import com.techblazer.lbt.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;


    @GetMapping
    public ResponseEntity<?> createReservationForGuest() {

        ReservationResponse reservation = reservationService.createReservation();

        return new ResponseEntity<>(BaseResponse.getSuccessResponse(reservation), HttpStatus.OK);
    }

}
