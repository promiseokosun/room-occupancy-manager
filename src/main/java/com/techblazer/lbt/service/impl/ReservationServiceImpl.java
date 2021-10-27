package com.techblazer.lbt.service.impl;

import com.techblazer.lbt.constant.BookingStatuses;
import com.techblazer.lbt.constant.RoomStatuses;
import com.techblazer.lbt.dto.BaseResponse;
import com.techblazer.lbt.dto.ReservationResponse;
import com.techblazer.lbt.model.Booking;
import com.techblazer.lbt.model.Reservation;
import com.techblazer.lbt.model.Room;
import com.techblazer.lbt.repo.ReservationRepo;
import com.techblazer.lbt.service.BookingService;
import com.techblazer.lbt.service.ReservationService;
import com.techblazer.lbt.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.techblazer.lbt.constant.RoomTypes.ECONOMY;
import static com.techblazer.lbt.constant.RoomTypes.PREMIUM;

@Service
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private ReservationRepo reservationRepo;


    @Override
    public ReservationResponse createReservation() {

        List<Booking> sortedBookings = bookingService.getBookingsOrderedByAmountDesc();

        List<Room> availablePremiumRooms = roomService.getAvailableRoomsByType(PREMIUM.getType());
        Queue<Room> premiumRoomQueue = new LinkedList<>(availablePremiumRooms);

        List<Room> availableEconomyRooms = roomService.getAvailableRoomsByType(ECONOMY.getType());
        Queue<Room> economyRoomQueue = new LinkedList<>(availableEconomyRooms);

        for (Booking booking : sortedBookings) {

            if (booking.getAmount() >= PREMIUM.getPrice()) {

                Room room = premiumRoomQueue.poll();

                if (room == null) {
                    break;
                }

                reservationRepo.save(new Reservation( null, room, booking));

                room.setStatus(RoomStatuses.BOOKED);
                roomService.saveRoom(room);

                booking.setStatus(BookingStatuses.success.name());
                bookingService.saveBooking(booking);
            }
        }

        List<Booking> successfulBookings = bookingService.getSuccessfulBookings();
        int pendingEconomyBooking = sortedBookings.size() - successfulBookings.size();

        for (Booking booking : sortedBookings) {

            if (booking.getAmount() < PREMIUM.getPrice()) {

                Room room = null;
                if ( pendingEconomyBooking > availableEconomyRooms.size()) {
                    // here economyRoom  will be completely occupied and we will be having pendingEconomyBooking thus we have to upgrade The highest paying customers below EUR 100 will get preference for the “upgrade”.
                    room = !premiumRoomQueue.isEmpty() ? premiumRoomQueue.poll() : economyRoomQueue.poll(); //  But they will book lower paying customers into Premium rooms if these rooms are empty and all Economy rooms are occupied with low paying customers
                } else {
                    // here economyRoom is completely occupied and premiumRoom isn't
                    room = economyRoomQueue.isEmpty() ? premiumRoomQueue.poll() : economyRoomQueue.poll(); //  But they will book lower paying customers into Premium rooms if these rooms are empty and all Economy rooms are occupied with low paying customers
                }

                if (room == null) {
                    break;
                }

                reservationRepo.save(new Reservation( null, room, booking));

                room.setStatus(RoomStatuses.BOOKED);
                roomService.saveRoom(room);

                booking.setStatus(BookingStatuses.success.name());
                bookingService.saveBooking(booking);
            }
        }


        List<Reservation> successfulPremiumReservations = getSuccessfulReservationByRoomType(PREMIUM.getType());
        List<Reservation> successfulEconomyReservations = getSuccessfulReservationByRoomType(ECONOMY.getType());

        double premiumTotalSales = Math.floor(successfulPremiumReservations.stream().mapToDouble(value -> value.getBooking().getAmount()).sum());
        double economyTotalSales = successfulEconomyReservations.stream().mapToDouble(value -> value.getBooking().getAmount()).sum();

        return new ReservationResponse(premiumTotalSales, economyTotalSales, (premiumTotalSales + economyTotalSales));
    }

    public List<Reservation> getSuccessfulReservationByRoomType(String roomType) {
        return reservationRepo.findByBookingStatusAndRoomType(BookingStatuses.success.name(), roomType);
    }
}
