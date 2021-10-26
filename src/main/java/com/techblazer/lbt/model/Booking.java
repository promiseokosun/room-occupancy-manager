package com.techblazer.lbt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double amountPaid;

    private Date createdAt = new Date();

    @ManyToOne
    private Room room;

    @ManyToMany
    private List<Guest> guests = new ArrayList<>();

    public Booking(Long id, double amountPaid, Room room, List<Guest> guests) {
        this.id = id;
        this.amountPaid = amountPaid;
        this.room = room;
        this.guests = guests;
    }
}
