package com.techblazer.lbt.service;

import com.techblazer.lbt.dto.ReservationResponse;
import com.techblazer.lbt.model.Booking;
import com.techblazer.lbt.model.Room;
import com.techblazer.lbt.repo.BookingRepo;
import com.techblazer.lbt.repo.ReservationRepo;
import com.techblazer.lbt.repo.RoomRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static com.techblazer.lbt.constant.RoomTypes.ECONOMY;
import static com.techblazer.lbt.constant.RoomTypes.PREMIUM;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepo reservationRepo;


    @BeforeEach
    public void setup() {

        reservationRepo.deleteAll(); // needed to clear the table
        roomRepo.deleteAll();
        bookingRepo.deleteAll();


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

    /**
     * (input) Free Premium rooms: 3 ● (input) Free Economy rooms: 3 ● (output) Usage Premium: 3 (EUR 738) ● (output) Usage Economy: 3 (EUR 167.99)
     */

    @Test
    public void test1() {

        roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 301));
        roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 302));
        roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 303));

        roomRepo.save(new Room(null, ECONOMY.getType(), null, ECONOMY.getPrice(), 306));
        roomRepo.save(new Room(null, ECONOMY.getType(), null, ECONOMY.getPrice(), 306));
        roomRepo.save(new Room(null, ECONOMY.getType(), null, ECONOMY.getPrice(), 306));

        ReservationResponse reservation = reservationService.createReservation();

        assertThat(reservation.getEconomyTotal()).isEqualTo(167.99);
        assertThat(reservation.getPremiumTotal()).isEqualTo(738);
    }


    /**
     *  (input) Free Premium rooms: 7 ● (input) Free Economy rooms: 5 ● (output) Usage Premium: 6 (EUR 1054) ● (output) Usage Economy: 4 (EUR 189.99)
     */

    @Test
    public void test2() {

        roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 301));
        roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 302));
        roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 303));
        roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 303));
        roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 303));
        roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 303));
        roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 303));

        roomRepo.save(new Room(null, ECONOMY.getType(), null, ECONOMY.getPrice(), 307));
        roomRepo.save(new Room(null, ECONOMY.getType(), null, ECONOMY.getPrice(), 304));
        roomRepo.save(new Room(null, ECONOMY.getType(), null, ECONOMY.getPrice(), 306));
        roomRepo.save(new Room(null, ECONOMY.getType(), null, ECONOMY.getPrice(), 306));
        roomRepo.save(new Room(null, ECONOMY.getType(), null, ECONOMY.getPrice(), 306));

        ReservationResponse reservation = reservationService.createReservation();

        assertThat(reservation.getEconomyTotal()).isEqualTo(189.99);
        assertThat(reservation.getPremiumTotal()).isEqualTo(1054);
    }

    /**
     * (input) Free Premium rooms: 2 ● (input) Free Economy rooms: 7 ● (output) Usage Premium: 2 (EUR 583) ● (output) Usage Economy: 4 (EUR 189.99)
     */

    @Test
    public void test3() {

        roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 303));
        roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 303));

        roomRepo.save(new Room(null, ECONOMY.getType(), null, ECONOMY.getPrice(), 307));
        roomRepo.save(new Room(null, ECONOMY.getType(), null, ECONOMY.getPrice(), 304));
        roomRepo.save(new Room(null, ECONOMY.getType(), null, ECONOMY.getPrice(), 306));
        roomRepo.save(new Room(null, ECONOMY.getType(), null, ECONOMY.getPrice(), 306));
        roomRepo.save(new Room(null, ECONOMY.getType(), null, ECONOMY.getPrice(), 306));
        roomRepo.save(new Room(null, ECONOMY.getType(), null, ECONOMY.getPrice(), 306));
        roomRepo.save(new Room(null, ECONOMY.getType(), null, ECONOMY.getPrice(), 306));

        ReservationResponse reservation = reservationService.createReservation();

        assertThat(reservation.getEconomyTotal()).isEqualTo(189.99);
        assertThat(reservation.getPremiumTotal()).isEqualTo(583);
    }

    /**
     * (input) Free Premium rooms: 7 ● (input) Free Economy rooms: 1 ● (output) Usage Premium: 7 (EUR 1153) ● (output) Usage Economy: 1 (EUR 45)
     */

    @Test
    public void test4() {

        roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 301));
        roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 302));
        roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 303));
        roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 303));
        roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 303));
        roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 303));
        roomRepo.save(new Room(null, PREMIUM.getType(), null, PREMIUM.getPrice(), 303));

        roomRepo.save(new Room(null, ECONOMY.getType(), null, ECONOMY.getPrice(), 307));

        ReservationResponse reservation = reservationService.createReservation();

        assertThat(reservation.getEconomyTotal()).isEqualTo(45);
        assertThat(reservation.getPremiumTotal()).isEqualTo(1153);
    }
}
