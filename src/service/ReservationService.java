package service;

import model.*;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationService = null;
    Collection<IRoom> rooms;
    Collection<Reservation> reservations;

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

    public void addRoom(IRoom room) {
        rooms.add(room);
    }

    public IRoom getARoom(String roomId) {
        for (IRoom room: rooms) {
            if (room.getRoomNumber().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> availableRooms = new ArrayList<>();

        for (Reservation reservation : reservations) {
            if (reservation.getCheckInDate().after(checkInDate)  && reservation.getCheckOutDate().before(checkOutDate)) {
                for (IRoom room : rooms) {
                    if (!reservation.getRoom().equals(room)) {
                        availableRooms.add(room);
                    }
                }
            }
        }

        return availableRooms;
    }

    public Collection<IRoom> getAllRooms() {
        return rooms;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        List<Reservation> reservationsByCustomer = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                reservationsByCustomer.add(reservation);
            }
        }
        return reservationsByCustomer;
    }

    public void printAllReservations() {
        System.out.println(reservations);
    }
}
