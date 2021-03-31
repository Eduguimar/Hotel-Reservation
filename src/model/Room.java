package model;

public class Room implements IRoom{
    protected String roomNumber;
    protected Double price;
    protected RoomType enumeration;
    protected Boolean isFree;

    public Room() { }

    public Room(String roomNumber, Double price, RoomType enumeration, Boolean isFree) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
        this.isFree = isFree;
    }

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
        return isFree;
    }

    @Override
    public String toString() {
        return "Room Number: " + getRoomNumber() + "\nRoom Price: " + getRoomPrice() + "\nRoom Type: " + getRoomType();
    }
}
