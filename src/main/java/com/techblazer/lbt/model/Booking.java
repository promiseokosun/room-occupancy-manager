package com.techblazer.lbt.model;

import com.techblazer.lbt.constant.BookingStatuses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double amount;

    private String name;

    private String email;

    private String phone;

    private Date createdAt = new Date();

    private String status = BookingStatuses.pending.name();

    public Booking(Long id, double amount, String name, String email, String phone) {
        this.id = id;
        this.amount = amount;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }


}
