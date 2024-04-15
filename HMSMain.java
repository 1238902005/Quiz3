import java.util.*;

class Room {
    private final int roomNumber;
    private final String roomType;
    private static final String[] ROOM_TYPES = {"Standard", "Deluxe", "Suite"};

    public Room(int roomNumber, String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }

    public static Room generateRandomRoom() {
        Random random = new Random();
        int roomNumber = random.nextInt(900) + 100; // Random room number between 100 and 999
        String roomType = ROOM_TYPES[random.nextInt(ROOM_TYPES.length)]; // Random room type
        return new Room(roomNumber, roomType);
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }
}

class HotelManager {
    private final Map<Integer, Room> guestRooms;
    private final List<Person> predefinedGuests;

    public HotelManager() {
        guestRooms = new HashMap<>();
        predefinedGuests = getPredefinedGuests();
    }

    public boolean checkIn(String personId, String name, String surname, int roomId, String roomType) {
        if (guestRooms.containsKey(roomId)) {
            System.out.println("Room " + roomId + " is already occupied.");
            return false;
        }
        guestRooms.put(roomId, new Room(roomId, roomType));
        return true;
    }

    public void checkOut(int roomId) {
        guestRooms.remove(roomId);
    }

    public Map<Integer, Room> getGuestRooms() {
        return guestRooms;
    }

    public int getGuestRoomsCount() {
        return guestRooms.size();
    }

    public void displayInfo() {
        System.out.println("Hotel Guest Information:");
        if (guestRooms.isEmpty() && predefinedGuests.isEmpty()) {
            System.out.println("No guests currently checked in.");
        } else {
            for (Map.Entry<Integer, Room> entry : guestRooms.entrySet()) {
                System.out.println("Room Number: " + entry.getValue().getRoomNumber() + ", Room Type: " + entry.getValue().getRoomType());
            }
            for (Person guest : predefinedGuests) {
                System.out.println("Predefined Guest: " + guest.getName() + " " + guest.getSurname());
            }
        }
    }

    private List<Person> getPredefinedGuests() {
        List<Person> guests = new ArrayList<>();
        guests.add(new Person("1001", "John", "Doe"));
        guests.add(new Person("1002", "Jane", "Smith"));
        guests.add(new Person("1003", "Michael", "Johnson"));
        return guests;
    }
}

class Person {
    private final String id;
    private final String name;
    private final String surname;

    public Person(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}

public class HMSMain {
    public static void main(String[] args) {
        // Create an instance of the HotelManager
        HotelManager hotelManager = new HotelManager();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Check-in");
            System.out.println("2. Check-out");
            System.out.println("3. Display Guest Information");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter your name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter your surname:");
                    String surname = scanner.nextLine();
                    System.out.println("Enter your ID:");
                    String id = scanner.nextLine();
                    System.out.println("Enter the room number:");
                    int roomId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter the room type (Suite, Standard, Deluxe):");
                    String roomType = scanner.nextLine();
                    if (hotelManager.checkIn(id, name, surname, roomId, roomType)) {
                        System.out.println("Checked in successfully!");
                    }
                    break;
                case 2:
                    System.out.println("Enter Room Number to check-out:");
                    int roomNum = scanner.nextInt();
                    if (hotelManager.getGuestRooms().containsKey(roomNum)) {
                        hotelManager.checkOut(roomNum);
                        System.out.println("Checked out successfully!");
                    } else {
                        System.out.println("Room " + roomNum + " not found.");
                    }
                    break;
                case 3:
                    hotelManager.displayInfo();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }
}
