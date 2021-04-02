import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {
    static final Scanner input = new Scanner(System.in);
    static HotelResource hotelResource = HotelResource.getInstance();

    public MainMenu() {
    }

    private static void findAndReserveARoom() {
        String book;
        String account;
        Calendar calendar = Calendar.getInstance();
        final String PATTERN = "MM/dd/yyyy";
        SimpleDateFormat date = new SimpleDateFormat(PATTERN);
        input.nextLine();
        try {
            System.out.println("Enter CheckIn Date mm/dd/yyyy:");
            Date todayDate = date.parse(date.format(calendar.getTime()));
            Date checkInDate = new SimpleDateFormat(PATTERN).parse(input.nextLine());
            System.out.println("Enter CheckOut Date mm/dd/yyyy:");
            Date checkOutDate = new SimpleDateFormat(PATTERN).parse(input.nextLine());
            if (!checkInDate.before(todayDate) && !checkOutDate.before(checkInDate)) {
                Collection<IRoom> rooms = hotelResource.findARoom(checkInDate, checkOutDate);
                if (!rooms.isEmpty()) {
                    do {
                        System.out.println("Would you like to book a room? y/n");
                        book = input.next().toLowerCase().trim();
                        if (book.equals("y")) {
                            do {
                                System.out.println("Do you have an account with us? y/n");
                                account = input.next().toLowerCase().trim();
                                if (account.equals("y")) {
                                    input.nextLine();
                                    System.out.println("Enter Email format: name@domain.com");
                                    String email = input.next();
                                    Customer customer = hotelResource.getCustomer(email);
                                    System.out.println("What room number would you like to reserve?");
                                    rooms.forEach(System.out::println);
                                    String roomNumber = input.next();
                                    IRoom room = hotelResource.getRoom(roomNumber);
                                    hotelResource.bookARoom(customer.getEmail(), room, checkInDate, checkOutDate);
                                } else if (account.equals("n")) {
                                    System.out.println("You have to create an account");
                                    createAccount();
                                } else {
                                    System.out.println("Invalid input!");
                                }
                            } while (!account.equals("y") && !account.equals("n"));
                        } else if (book.equals("n")) {
                            start();
                        } else {
                            System.out.println("Invalid input!");
                        }
                    } while (!book.equals("y") && !book.equals("n"));
                } else {
                    System.out.println("There is no room available");
                }
            } else {
                System.out.println("Check in date needs to be from today onwards and check out date can not be before check in date");
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    private static void seeMyReservations() {
        input.nextLine();
        System.out.println("Enter your email:");
        String email = input.next();
        Collection<Reservation> reservations = hotelResource.getCustomersReservations(email);
        if (!reservations.isEmpty()) {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        } else {
            System.out.println("You have no reservations yet");
        }
    }

    private static void createAccount() {
        input.nextLine();
        System.out.println("Enter Email:");
        String email = input.nextLine();
        System.out.println("First Name:");
        String firstName = input.nextLine();
        System.out.println("Last Name:");
        String lastName = input.nextLine();
        hotelResource.createACustomer(email, firstName, lastName);
    }

    public static void start() throws ParseException {
        boolean quit = false;
        String i;
        while (!quit) {
            do {
                System.out.println("Welcome to the Hotel Reservation Application");
                System.out.println("____________________________________________");
                System.out.println("1. Find and reserve a room");
                System.out.println("2. See my reservations");
                System.out.println("3. Create an account");
                System.out.println("4. Admin");
                System.out.println("5. Exit");
                System.out.println("____________________________________________");
                System.out.println("Please select a number for the menu option");
                i = input.next().trim();
                switch (i) {
                    case "1":
                        findAndReserveARoom();
                        break;
                    case "2":
                        seeMyReservations();
                        break;
                    case "3":
                        createAccount();
                        break;
                    case "4":
                        AdminMenu adminMenu = new AdminMenu();
                        adminMenu.start();
                        break;
                    case "5":
                        quit = true;
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
            } while (!i.equals("1") && !i.equals("2") && !i.equals("3") && !i.equals("4") && !i.equals("5") && !quit);
        }
    }
}