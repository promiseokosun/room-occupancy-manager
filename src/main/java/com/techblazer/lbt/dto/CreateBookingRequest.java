package com.techblazer.lbt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingRequest {

    private double amount;

    private String name;

    private String email;

    private String phone;

}
