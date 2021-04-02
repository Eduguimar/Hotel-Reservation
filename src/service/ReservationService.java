package service;

import model.*;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationService = null;
    static Set<IRoom> rooms;
    static Set<Reservation> reservations;

    private ReservationService(){
        this.rooms = new HashSet<>();
        this.reservations = new HashSet<>();
    }

    public static ReservationService getInstance() {
        if (reservationService == null) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public static void addRoom(IRoom room) {
        rooms.add(room);
    }

    public static IRoom getARoom(String roomId) {
        for (IRoom room: rooms) {
            if (room.getRoomNumber().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        if (reservations.isEmpty()) {
            return rooms;
        } else {
            return findAvailableRooms(checkInDate, checkOutDate);
        }
    }

    static Collection<IRoom> findAvailableRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> availableRooms = new ArrayList<>();

        for (Reservation reservation : reservations) {
            if (!reservation.getCheckInDate().after(checkInDate)  && !reservation.getCheckOutDate().before(checkOutDate)) {
                for (IRoom room : rooms) {
                    if (!reservation.getRoom().equals(room)) {
                        availableRooms.add(room);
                    }
                }
            }
        }
        return availableRooms;
    }

    public static Collection<IRoom> getAllRooms() {
        return rooms;
    }

    public static Collection<Reservation> getCustomersReservation(Customer customer) {
        List<Reservation> reservationsByCustomer = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                reservationsByCustomer.add(reservation);
            }
        }
        return reservationsByCustomer;
    }

    public static void printAllReservations() {
        if (!reservations.isEmpty()) {
            System.out.println(reservations);
        } else {
            System.out.println("There is no reservations yet");
        }
    }
}
