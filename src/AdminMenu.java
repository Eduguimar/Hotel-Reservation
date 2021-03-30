import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    static Scanner input;
    static AdminResource adminResource = AdminResource.getInstance();

    public AdminMenu() {
        this.input = new Scanner(System.in);
    }

    private void seeAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();
        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }
    }

    private void seeAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        for (IRoom room : rooms) {
            System.out.println(room.toString());
        }
    }

    private void seeAllReservations() {
        adminResource.displayAllReservations();
    }

    private void addARoom() {
        String addRoom;
        Integer type;
        do {
            RoomType roomType = null;
            input.nextLine();
            System.out.println("Enter room number:");
            String roomNumber = input.nextLine();
            System.out.println("Enter price per night:");
            Double roomPrice = input.nextDouble();
            do {
                System.out.println("Enter room type: 1 - Single bed, 2 - Double bed");
                type = input.nextInt();
                if (type == 1) {
                    roomType = RoomType.SINGLE;
                } else if (type == 2) {
                    roomType = RoomType.DOUBLE;
                } else {
                    System.out.println("Invalid input");
                }
            } while (type != 1 && type != 2);
            IRoom room = new Room(roomNumber, roomPrice, roomType);
            List<IRoom> rooms = new ArrayList<>();
            rooms.add(room);
            adminResource.addRoom(rooms);
            do {
                System.out.println("Would you like to add another room? y/n");
                addRoom = input.next().toLowerCase().trim();
            } while (!addRoom.equals("y") && !addRoom.equals("n"));
        } while (addRoom.equals("y"));

    }

    public void start() throws ParseException {
        boolean quit = false;
        while (!quit) {
            System.out.println("Admin Menu");
            System.out.println("____________________________________________");
            System.out.println("1. See all Customers");
            System.out.println("2. See all Rooms");
            System.out.println("3. See all Reservations");
            System.out.println("4. Add a Room");
            System.out.println("5. Add Test Data");
            System.out.println("6. Back to Main Menu");
            System.out.println("____________________________________________");
            System.out.println("Please select a number for the menu option");

            switch (input.nextInt()) {
                case 1:
                    seeAllCustomers();
                    break;
                case 2:
                    seeAllRooms();
                    break;
                case 3:
                    seeAllReservations();
                    break;
                case 4:
                    addARoom();
                    break;
                case 5:
                    quit = true;
                    break;
                case 6:
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.start();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }
}
