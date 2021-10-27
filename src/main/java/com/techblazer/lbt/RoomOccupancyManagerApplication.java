package com.techblazer.lbt;

import com.techblazer.lbt.model.Booking;
import com.techblazer.lbt.model.Room;
import com.techblazer.lbt.repo.BookingRepo;
import com.techblazer.lbt.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import static com.techblazer.lbt.constant.RoomTypes.ECONOMY;
import static com.techblazer.lbt.constant.RoomTypes.PREMIUM;

@SpringBootApplication
public class RoomOccupancyManagerApplication implements CommandLineRunner {

	@Autowired
	private RoomRepo roomRepo;

	@Autowired
	private BookingRepo bookingRepo;


	public static void main(String[] args) {
		SpringApplication.run(RoomOccupancyManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<Room> rooms = roomRepo.findAll();

		if (rooms.isEmpty()) {
			roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 301));
			roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 302));
			roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 303));
//			roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 303));
//			roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 303));
//			roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 303));
//			roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 303));
//			roomRepo.save(new Room(null, ECONOMY.getType(), null, ECONOMY.getPrice(), 307));
//			roomRepo.save(new Room(null, ECONOMY.getType(), null, ECONOMY.getPrice(), 304));
//			roomRepo.save(new Room(null, ECONOMY.getType(), null, ECONOMY.getPrice(), 306));
//			roomRepo.save(new Room(null, ECONOMY.getType(), null, ECONOMY.getPrice(), 306));
			roomRepo.save(new Room(null, ECONOMY.getType(), null, ECONOMY.getPrice(), 306));
			roomRepo.save(new Room(null, ECONOMY.getType(), null, ECONOMY.getPrice(), 306));
			roomRepo.save(new Room(null, ECONOMY.getType(), null, ECONOMY.getPrice(), 306));
		}

		List<Booking> bookings = bookingRepo.findAll();

		if (bookings.isEmpty()) {
			bookingRepo.save(new Booking(null, 23, "Tunde Bode", "tunde.bode@gmail.com", "090773636"));
			bookingRepo.save(new Booking(null, 45, "Joel Timothy", "Joe.Tim@gmail.com", "09-888877"));
			bookingRepo.save(new Booking(null, 155, "Rita Lorri", "rita.lorri@yahoo.com", "0905364364"));
			bookingRepo.save(new Booking(null, 374, "Grem Shola", "g.shola@gmail.com", "76646464" ));
			bookingRepo.save(new Booking(null, 22, "Henry Cole", "henry.co@gmail.com", "7848484848"));
			bookingRepo.save(new Booking(null, 99.99, "Tayo Great", "t.g@yahoo.com", "8883949"));
			bookingRepo.save(new Booking(null, 100, "Emma Franca", "emmy.franco@gmail.com", "9-836447"));
			bookingRepo.save(new Booking(null, 101, "colins jubril", "col.jub@yahoo.com", "0909733636"));
			bookingRepo.save(new Booking(null, 115, "Lanre Shittu", "lanre.shit@gmail.com", "09737383"));
			bookingRepo.save(new Booking(null, 209, "zita Amata", "zita.amata@gmail.com", "8993838383"));
		}

	}
}
