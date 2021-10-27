package com.techblazer.lbt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoomRequest {

    @NotBlank(message = "type is required")
    private String type;

    private String description;

    @NotBlank(message = "type is required")
    private double price;

    @NotBlank(message = "type is required")
    private int number;
}
