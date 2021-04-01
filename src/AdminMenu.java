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
    static final Scanner input = new Scanner(System.in);
    static AdminResource adminResource = AdminResource.getInstance();

    public AdminMenu() {
    }

    private static void seeAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();
        if (!customers.isEmpty()) {
            for (Customer customer : customers) {
                System.out.println(customer.toString());
            }
        } else {
            System.out.println("There is no customers yet");
        }
    }

    private static void seeAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        if (!rooms.isEmpty()) {
            for (IRoom room : rooms) {
                System.out.println(room.toString());
            }
        } else {
            System.out.println("There is no rooms yet");
        }
    }

    private static void seeAllReservations() {
        adminResource.displayAllReservations();
    }

    private static void addARoom() {
        String addRoom;
        Integer type;
        do {
            RoomType roomType = null;
            Boolean isFree = true;
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
            IRoom room = new Room(roomNumber, roomPrice, roomType, isFree);
            List<IRoom> rooms = new ArrayList<>();
            rooms.add(room);
            adminResource.addRoom(rooms);
            do {
                System.out.println("Would you like to add another room? y/n");
                addRoom = input.next().toLowerCase().trim();
            } while (!addRoom.equals("y") && !addRoom.equals("n"));
        } while (addRoom.equals("y"));

    }

    public static void start() throws ParseException {
        boolean quit = false;
        String i;
        while (!quit) {
            do {
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
                i = input.next().trim();
                switch (i) {
                    case "1":
                        seeAllCustomers();
                        break;
                    case "2":
                        seeAllRooms();
                        break;
                    case "3":
                        seeAllReservations();
                        break;
                    case "4":
                        addARoom();
                        break;
                    case "5":
                        quit = true;
                        break;
                    case "6":
                        MainMenu mainMenu = new MainMenu();
                        mainMenu.start();
                        break;
                    default:
                        System.out.println("Invalid input");
                }
            } while (!i.equals("1") && !i.equals("2") && !i.equals("3") && !i.equals("4") && !i.equals("5") && !i.equals("6") && quit != true);
        }
    }
}
