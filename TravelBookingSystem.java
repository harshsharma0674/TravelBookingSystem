package TravelBookingSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TravelBookingSystem {
    static class Trip {
        String destination;
        double price;
        int availableSeats;

        Trip(String destination, double price, int availableSeats) {
            this.destination = destination;
            this.price = price;
            this.availableSeats = availableSeats;
        }
    }

    static class Booking {
        String passengerName;
        Trip trip;

        Booking(String passengerName, Trip trip) {
            this.passengerName = passengerName;
            this.trip = trip;
        }
    }

    static class BookingSystem {
        List<Trip> trips;
        List<Booking> bookings;

        BookingSystem() {
            trips = new ArrayList<>();
            bookings = new ArrayList<>();
        }

        void addTrip(String destination, double price, int availableSeats) {
            Trip trip = new Trip(destination, price, availableSeats);
            trips.add(trip);
        }

        void displayTrips() {
            System.out.println("Available Trips:");
            for (int i = 0; i < trips.size(); i++) {
                Trip trip = trips.get(i);
                System.out.println(i + 1 + ". " + trip.destination + " - $" + trip.price + " per seat (Seats: " + trip.availableSeats + ")");
            }
        }

        void bookTrip(String passengerName, int tripIndex) {
            if (tripIndex >= 0 && tripIndex < trips.size()) {
                Trip selectedTrip = trips.get(tripIndex);
                if (selectedTrip.availableSeats > 0) {
                    Booking booking = new Booking(passengerName, selectedTrip);
                    bookings.add(booking);
                    selectedTrip.availableSeats--;
                    System.out.println("Booking successful! Enjoy your trip to " + selectedTrip.destination + ".");
                } else {
                    System.out.println("Sorry, no available seats for the selected trip.");
                }
            } else {
                System.out.println("Invalid trip selection.");
            }
        }
    }

    public static void main(String[] args) {
        BookingSystem bookingSystem = new BookingSystem();
        bookingSystem.addTrip("Paris", 1000.0, 10);
        bookingSystem.addTrip("New York", 1500.0, 8);
        bookingSystem.addTrip("Tokyo", 2000.0, 12);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Display Trips\n2. Book a Trip\n3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bookingSystem.displayTrips();
                    break;
                case 2:
                    System.out.print("Enter your name: ");
                    scanner.nextLine(); // Consume the newline character
                    String passengerName = scanner.nextLine();
                    bookingSystem.displayTrips();
                    System.out.print("Enter the trip number you want to book: ");
                    int tripIndex = scanner.nextInt() - 1;
                    bookingSystem.bookTrip(passengerName, tripIndex);
                    break;
                case 3:
                    System.out.println("Exiting the travel booking system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
