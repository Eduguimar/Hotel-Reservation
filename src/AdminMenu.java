import api.AdminResource;
import api.HotelResource;

import java.util.Scanner;

public class AdminMenu {
    static Scanner input = new Scanner(System.in);
    static HotelResource hotelResource = HotelResource.getInstance();
    static AdminResource adminResource = AdminResource.getInstance();

    public static void adminMenu() {

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

            switch (input.nextInt()) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    Scanner customer = new Scanner(System.in);
                    System.out.println("Enter Email:");
                    String email = customer.nextLine();
                    System.out.println("First Name:");
                    String firstName = customer.nextLine();
                    System.out.println("Last Name:");
                    String lastName = customer.nextLine();
                    hotelResource.createACustomer(email, firstName, lastName);
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }

        } while (input.nextInt() != 6);
    }
}
