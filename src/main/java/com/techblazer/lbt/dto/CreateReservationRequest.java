package com.techblazer.lbt.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationRequest {

    @NotBlank(message = "roomId is required")
    private Long roomId;

    @NotBlank(message = "guestName is required")
    private String guestName;

    private String guestEmail;

    @NotBlank(message = "guestPhone is required")
    private String guestPhone;

    @NotBlank(message = "amountPaid is required")
    private double amountPaid;

    private String guestAddress;
}
