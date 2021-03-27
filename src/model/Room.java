package model;

public class Room implements IRoom{
    protected String roomNumber;
    protected Double price;
    protected RoomType enumeration;

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    public String toString() {
        return "Room Number: " + getRoomNumber() + "\nRoom Price: " + getRoomPrice() + "\nRoom Type: " + getRoomType();
    }
}
