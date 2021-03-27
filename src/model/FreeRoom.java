package model;

public class FreeRoom extends Room{
    public FreeRoom() {
        super.price = 0.00;
    }

    public String toString() {
        return "Room Number: " + super.getRoomNumber() + "\nRoom Price: " + super.getRoomPrice() + "\nRoom Type: " + super.getRoomType();
    }
}
