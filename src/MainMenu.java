import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {
    Scanner input;
    HotelResource hotelResource = HotelResource.getInstance();

    public MainMenu() {
        this.input = new Scanner(System.in);
    }

    private void findAndReserveARoom() throws ParseException {
        input.nextLine();
        System.out.println("Enter CheckIn Date mm/dd/yyyy:");
        Date checkInDate = new SimpleDateFormat("MM/dd/yyyy").parse(input.nextLine());
        System.out.println("Enter CheckOut Date mm/dd/yyyy:");
        Date checkOutDate = new SimpleDateFormat("MM/dd/yyyy").parse(input.nextLine());
        Collection<IRoom> rooms = hotelResource.findARoom(checkInDate, checkOutDate);
        if (!rooms.isEmpty()) {
            System.out.println("Would you like to book a room? y/n");
            char book = input.next().trim().charAt(0);
            if (book == 'y') {
                System.out.println("Do you have an account with us? y/n");
                char account = input.next().trim().charAt(0);
                if (account == 'y') {
                    System.out.println("Enter Email format: name@domain.com");
                    String email = input.nextLine();
                    Customer customer = hotelResource.getCustomer(email);
                    System.out.println("What room number would you like to reserve?");
                    String roomNumber = input.nextLine();
                    for (IRoom room : rooms) {
                        if (room.getRoomNumber().equals(roomNumber)) {
                            hotelResource.bookARoom(customer.getEmail(), room, checkInDate, checkOutDate);
                        }
                    }
                } else if (account == 'n'){
                    System.out.println("You have to create an account");
                    this.createAccount();
                } else {
                    System.out.println("Invalid input!");
                    return;
                }
            } else if (book == 'n') {
                return;
            } else {
                System.out.println("Invalid input!");
                return;
            }
        } else {
            System.out.println("There is no room available");
            return;
        }
    }

    private void seeMyReservations() {
        input.nextLine();
        System.out.println("Enter your email:");
        String email = input.nextLine();
        Collection<Reservation> reservations = hotelResource.getCustomersReservations(email);
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    private void createAccount() {
        input.nextLine();
        System.out.println("Enter Email:");
        String email = input.nextLine();
        System.out.println("First Name:");
        String firstName = input.nextLine();
        System.out.println("Last Name:");
        String lastName = input.nextLine();
        hotelResource.createACustomer(email, firstName, lastName);
    }

    public void start() throws ParseException {
        boolean quit = false;
        while (!quit) {
            System.out.println("Welcome to the Hotel Reservation Application");
            System.out.println("____________________________________________");
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            System.out.println("____________________________________________");
            System.out.println("Please select a number for the menu option");
            switch (input.nextInt()) {
                case 1:
                    findAndReserveARoom();
                    break;
                case 2:
                    seeMyReservations();
                    break;
                case 3:
                    createAccount();
                    break;
                case 4:
                    AdminMenu adminMenu = new AdminMenu();
                    adminMenu.start();
                    break;
                case 5:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid Input!");
            }
        }
    }
}
