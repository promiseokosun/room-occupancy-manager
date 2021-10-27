package com.techblazer.lbt.model;

import com.techblazer.lbt.constant.RoomStatuses;
import com.techblazer.lbt.constant.RoomTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;

    private String description;

    private double price;

    private int number;

    @Enumerated(EnumType.STRING)
    private RoomStatuses status = RoomStatuses.AVAILABLE;

    public Room(Long id, String type, String description, double price, int number) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.price = price;
        this.number = number;
    }
}
