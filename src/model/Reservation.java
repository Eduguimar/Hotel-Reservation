package model;

import java.util.Date;

public class Reservation {
    Customer customer;
    IRoom room;
    Date checkInDate;
    Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setRoom(IRoom room) {
        this.room = room;
    }

    public IRoom getRoom() {
        return room;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public String toString() {
        return "Thanks for your registration " + getCustomer().firstName + "\nRoom: " + getRoom().getRoomNumber()
                + "\nCheck In Date: " + getCheckInDate() + "\nCheck Out Date: " + getCheckOutDate();
    }

}
