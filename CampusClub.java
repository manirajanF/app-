import java.util.*;

class Event {
    String eventName;
    String date;
    String venue;

    Event(String eventName, String date, String venue) {
        this.eventName = eventName;
        this.date = date;
        this.venue = venue;
    }

    void display() {
        System.out.printf("%-20s %-12s %-10s\n", eventName, date, venue);
    }
}

class Club {
    String clubName;
    ArrayList<Event> events;

    Club(String clubName) {
        this.clubName = clubName;
        this.events = new ArrayList<>();
    }

    void addEvent(Event e) {
        events.add(e);
    }

    void displayEvents() {
        if (events.isEmpty()) {
            System.out.println("No events scheduled yet for " + clubName);
            return;
        }
        System.out.println("\nEvents under " + clubName + ":");
        System.out.printf("%-20s %-12s %-10s\n", "Event Name", "Date", "Venue");
        for (Event e : events)
            e.display();
    }
}

public class CampusClub{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Club> clubs = new ArrayList<>();

        while (true) {
            System.out.println("\n===== CAMPUS CLUB EVENT SCHEDULER =====");
            System.out.println("1. Add New Club");
            System.out.println("2. Schedule Event for Club");
            System.out.println("3. Display All Events");
            System.out.println("4. Search Events by Club Name");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter club name: ");
                    String cname = sc.nextLine();
                    clubs.add(new Club(cname));
                    System.out.println("Club added successfully!");
                    break;

                case 2:
                    System.out.print("Enter club name to add event: ");
                    String target = sc.nextLine();
                    Club found = null;
                    for (Club c : clubs) {
                        if (c.clubName.equalsIgnoreCase(target))
                            found = c;
                    }
                    if (found == null) {
                        System.out.println("Club not found. Add club first!");
                    } else {
                        System.out.print("Enter event name: ");
                        String ename = sc.nextLine();
                        System.out.print("Enter event date (DD/MM): ");
                        String date = sc.nextLine();
                        System.out.print("Enter venue: ");
                        String venue = sc.nextLine();

                        found.addEvent(new Event(ename, date, venue));
                        System.out.println("Event added under " + found.clubName);
                    }
                    break;

                case 3:
                    for (Club c : clubs) {
                        c.displayEvents();
                    }
                    break;

                case 4:
                    System.out.print("Enter club name to search: ");
                    String search = sc.nextLine();
                    boolean matched = false;
                    for (Club c : clubs) {
                        if (c.clubName.equalsIgnoreCase(search)) {
                            c.displayEvents();
                            matched = true;
                        }
                    }
                    if (!matched)
                        System.out.println("❌ No club found with that name.");
                    break;

                case 5:
                    System.out.println("👋 Exiting... Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("⚠ Invalid choice! Try again.");
            }
        }
    }
}