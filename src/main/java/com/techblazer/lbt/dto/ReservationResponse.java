package com.techblazer.lbt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponse {

    private double premiumTotal;

    private double economyTotal;

    private double TotalSales;
}
