package com.techblazer.lbt;

import com.techblazer.lbt.constant.RoomTypes;
import com.techblazer.lbt.model.Guest;
import com.techblazer.lbt.model.Room;
import com.techblazer.lbt.repo.GuestRepo;
import com.techblazer.lbt.repo.RoomRepo;
import com.techblazer.lbt.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class RoomOccupancyManagerApplication implements CommandLineRunner {

	@Autowired
	private RoomRepo roomRepo;

	@Autowired
	private GuestRepo guestRepo;

	public static void main(String[] args) {
		SpringApplication.run(RoomOccupancyManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<Room> rooms = roomRepo.findAll();

		if (rooms.isEmpty()) {
			roomRepo.save(new Room(null, RoomTypes.PREMIUM, null, 300, 301));
			roomRepo.save(new Room(null, RoomTypes.PREMIUM, null, 250, 302));
			roomRepo.save(new Room(null, RoomTypes.PREMIUM, null, 100, 303));
			roomRepo.save(new Room(null, RoomTypes.ECONOMY, null, 90, 50));
			roomRepo.save(new Room(null, RoomTypes.ECONOMY, null, 60, 50));
			roomRepo.save(new Room(null, RoomTypes.ECONOMY, null, 50, 50));
			roomRepo.save(new Room(null, RoomTypes.ECONOMY, null, 50, 50));
		}


		List<Guest> guests = guestRepo.findAll();

		if (guests.isEmpty()) {
			guestRepo.save(new Guest(null, "Promise Okosun", "promiseokosun@gmail.com", "test address", "0904773636"));
			guestRepo.save(new Guest(null, "Thomas Jones", "t.jones@gmail.com", "test address", "0904773636"));
		}




	}
}
