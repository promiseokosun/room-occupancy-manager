package com.techblazer.lbt.repo;

import com.techblazer.lbt.constant.BookingStatuses;
import com.techblazer.lbt.model.Booking;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookingRepoTest {

    @Autowired
    private BookingRepo bookingRepo;

    @BeforeEach
    public void setup() {
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

    @Test
    public void test_findAllByOrderByPriceDesc() {
        shouldReturnListOfBookingsInDescendingOrder();
        shouldReturnEmptyWhenNoBookingExists();
    }

    @Test
    public void test_findByStatus() {
        shouldReturnListOfSuccessfulBookings();
        shouldReturnEmptyWhenNoSuccessfulBookingsExists();
    }

    private void shouldReturnListOfSuccessfulBookings() {
        // make all bookings to be success
        List<Booking> bookings = bookingRepo.findAll()
                .stream()
                .map(b -> new Booking(b.getId(), b.getAmount(), b.getName(), b.getEmail(), b.getPhone(), b.getCreatedAt(), BookingStatuses.success.name()))
                .collect(Collectors.toList());

        bookingRepo.saveAll(bookings);

        assertThat(bookingRepo.findByStatus(BookingStatuses.success.name())).isNotEmpty();
    }

    private void shouldReturnEmptyWhenNoSuccessfulBookingsExists() {

        // make all bookings to be pending
        List<Booking> bookings = bookingRepo.findAll()
                .stream()
                .map(b -> new Booking(b.getId(), b.getAmount(), b.getName(), b.getEmail(), b.getPhone(), b.getCreatedAt(), BookingStatuses.pending.name()))
                .collect(Collectors.toList());

        bookingRepo.saveAll(bookings);

        assertThat(bookingRepo.findByStatus(BookingStatuses.success.name())).isEmpty();
    }

    private void shouldReturnListOfBookingsInDescendingOrder() {
        List<Booking> bookings = bookingRepo.findAllByOrderByAmountDesc();
        Booking [] bookingArray = new Booking[bookings.size()];
        bookingArray = bookings.toArray(bookingArray);

        for (int i = 0; i < bookingArray.length - 1; i++) {
            assertThat(bookingArray[i].getAmount()).isGreaterThan(bookingArray[i + 1].getAmount());
        }

    }

    private void shouldReturnEmptyWhenNoBookingExists() {
        bookingRepo.deleteAll();
        List<Booking> bookings = bookingRepo.findAllByOrderByAmountDesc();
        assertThat(bookings.size()).isZero();
    }
}
