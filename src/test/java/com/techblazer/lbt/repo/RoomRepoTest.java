package com.techblazer.lbt.repo;

import com.techblazer.lbt.model.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static com.techblazer.lbt.constant.RoomStatuses.AVAILABLE;
import static com.techblazer.lbt.constant.RoomStatuses.BOOKED;
import static com.techblazer.lbt.constant.RoomTypes.ECONOMY;
import static com.techblazer.lbt.constant.RoomTypes.PREMIUM;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RoomRepoTest {

    @Autowired
    private RoomRepo roomRepo;

    @Test
    public void test_findByStatusOrderByPriceDesc() {
        shouldReturnAnOrderedListOfAvailableRooms();
        shouldReturnEmptyWhenNoAvailableRoomIsFound();
    }

    @Test
    public void test_findByStatusAndTypeOrderByPriceDesc() {
        shouldReturnAnOrderedListOfAvailablePremiumRooms();
        shouldReturnAnOrderedListOfAvailableEconomyRooms();
        shouldReturnEmptyWhenNonExists();
    }

    private void shouldReturnAnOrderedListOfAvailablePremiumRooms() {
        List<Room> availableRooms = roomRepo.findAll().stream()
                .filter(room -> room.getStatus().equals(BOOKED))
                .map(room -> new Room(room.getId(), PREMIUM, room.getDescription(), room.getPrice(), room.getNumber(), AVAILABLE))
                .collect(Collectors.toList());

        roomRepo.saveAll(availableRooms);

        assertThat(roomRepo.findByStatusAndTypeOrderByPriceDesc(AVAILABLE, PREMIUM)).isNotEmpty();
    }

    private void shouldReturnAnOrderedListOfAvailableEconomyRooms() {
        List<Room> availableRooms = roomRepo.findAll().stream()
                .map(room -> new Room(room.getId(), ECONOMY, room.getDescription(), room.getPrice(), room.getNumber(), AVAILABLE))
                .collect(Collectors.toList());

        roomRepo.saveAll(availableRooms);

        assertThat(roomRepo.findByStatusAndTypeOrderByPriceDesc(AVAILABLE, ECONOMY)).isNotEmpty();
    }

    private void shouldReturnEmptyWhenNonExists() {
        List<Room> availableRooms = roomRepo.findAll().stream()
                .map(room -> new Room(room.getId(), ECONOMY, room.getDescription(), room.getPrice(), room.getNumber(), BOOKED))
                .collect(Collectors.toList());

        roomRepo.saveAll(availableRooms);

        assertThat(roomRepo.findByStatusAndTypeOrderByPriceDesc(AVAILABLE, ECONOMY)).isEmpty();


        availableRooms = roomRepo.findAll().stream()
                .map(room -> new Room(room.getId(), ECONOMY, room.getDescription(), room.getPrice(), room.getNumber(), AVAILABLE))
                .collect(Collectors.toList());

        roomRepo.saveAll(availableRooms);

        assertThat(roomRepo.findByStatusAndTypeOrderByPriceDesc(AVAILABLE, PREMIUM)).isEmpty();


        availableRooms = roomRepo.findAll().stream()
                .map(room -> new Room(room.getId(), PREMIUM, room.getDescription(), room.getPrice(), room.getNumber(), AVAILABLE))
                .collect(Collectors.toList());

        roomRepo.saveAll(availableRooms);

        assertThat(roomRepo.findByStatusAndTypeOrderByPriceDesc(AVAILABLE, ECONOMY)).isEmpty();

    }

    private void shouldReturnAnOrderedListOfAvailableRooms() {
        List<Room> availableRooms = roomRepo.findAll().stream()
                .filter(room -> room.getStatus().equals(BOOKED))
                .map(room -> new Room(room.getId(), room.getType(), room.getDescription(), room.getPrice(), room.getNumber(), AVAILABLE))
                .collect(Collectors.toList());

        roomRepo.saveAll(availableRooms);

        assertThat(roomRepo.findByStatusOrderByPriceDesc(AVAILABLE)).isNotEmpty();
    }

    private void shouldReturnEmptyWhenNoAvailableRoomIsFound() {
        List<Room> bookedRooms = roomRepo.findAll().stream()
                .filter(room -> room.getStatus().equals(AVAILABLE))
                .map(room -> new Room(room.getId(), room.getType(), room.getDescription(), room.getPrice(), room.getNumber(), BOOKED))
                .collect(Collectors.toList());

        roomRepo.saveAll(bookedRooms);

        assertThat(roomRepo.findByStatusOrderByPriceDesc(AVAILABLE)).isEmpty();
    }
}
