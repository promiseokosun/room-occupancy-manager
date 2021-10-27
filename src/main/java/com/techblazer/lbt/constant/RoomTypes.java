package com.techblazer.lbt.constant;

public enum RoomTypes {

    PREMIUM("premium", 100.0),
    ECONOMY("economy", 50.0);

    private String type;
    private Double price;

    RoomTypes(String type, Double price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "RoomTypes{" +
                "type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
