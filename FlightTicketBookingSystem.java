// 2. Flight Ticket Booking System
import java.time.LocalDateTime;
import java.util.*;

enum MembershipStatus {
    NONE(0),
    SILVER(1),
    GOLD(2),
    PLATINUM(3);
    
    private int priority;
    
    MembershipStatus(int priority) {
        this.priority = priority;
    }
    
    public int getPriority() {
        return priority;
    }
}

enum SpecialNeeds {
    NONE(0),
    WHEELCHAIR(3),
    MEDICAL(5),
    INFANT(2),
    ELDERLY(2);
    
    private int priority;
    
    SpecialNeeds(int priority) {
        this.priority = priority;
    }
    
    public int getPriority() {
        return priority;
    }
}

class Passenger {
    private String id;
    private String name;
    private MembershipStatus membershipStatus;
    private SpecialNeeds specialNeeds;
    private LocalDateTime bookingTime;
    
    public Passenger(String id, String name, MembershipStatus membershipStatus, 
                    SpecialNeeds specialNeeds, LocalDateTime bookingTime) {
        this.id = id;
        this.name = name;
        this.membershipStatus = membershipStatus;
        this.specialNeeds = specialNeeds;
        this.bookingTime = bookingTime;
    }
    
    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public MembershipStatus getMembershipStatus() { return membershipStatus; }
    public SpecialNeeds getSpecialNeeds() { return specialNeeds; }
    public LocalDateTime getBookingTime() { return bookingTime; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(id, passenger.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Passenger{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", membershipStatus=" + membershipStatus +
                ", specialNeeds=" + specialNeeds +
                ", bookingTime=" + bookingTime +
                '}';
    }
}

class Ticket {
    private String ticketId;
    private String flightNumber;
    private Passenger passenger;
    private boolean cancelled;
    
    public Ticket(String ticketId, String flightNumber, Passenger passenger) {
        this.ticketId = ticketId;
        this.flightNumber = flightNumber;
        this.passenger = passenger;
        this.cancelled = false;
    }
    
    // Getters
    public String getTicketId() { return ticketId; }
    public String getFlightNumber() { return flightNumber; }
    public Passenger getPassenger() { return passenger; }
    public boolean isCancelled() { return cancelled; }
    
    public void cancel() {
        this.cancelled = true;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticketId, ticket.ticketId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(ticketId);
    }
}

class PassengerPriorityComparator implements Comparator<Passenger> {
    @Override
    public int compare(Passenger p1, Passenger p2) {
        // First priority: Special needs
        int specialNeedsDiff = p2.getSpecialNeeds().getPriority() - p1.getSpecialNeeds().getPriority();
        if (specialNeedsDiff != 0) {
            return specialNeedsDiff;
        }
        
        // Second priority: Membership status
        int membershipDiff = p2.getMembershipStatus().getPriority() - p1.getMembershipStatus().getPriority();
        if (membershipDiff != 0) {
            return membershipDiff;
        }
        
        // Third priority: Booking time (earlier is better)
        return p1.getBookingTime().compareTo(p2.getBookingTime());
    }
}

public class FlightTicketBookingSystem {
    private Map<String, Queue<Passenger>> waitingLists; // Flight number -> waiting list
    private Map<String, Set<Ticket>> flightTickets; // Flight number -> tickets
    private Map<String, Ticket> ticketMap; // Ticket ID -> ticket
    private Map<String, Passenger> passengerMap; // Passenger ID -> passenger
    
    public FlightTicketBookingSystem() {
        waitingLists = new HashMap<>();
        flightTickets = new HashMap<>();
        ticketMap = new HashMap<>();
        passengerMap = new HashMap<>();
    }
    
    public void addPassenger(Passenger passenger) {
        passengerMap.put(passenger.getId(), passenger);
    }
    
    public Ticket bookTicket(String flightNumber, String passengerId) {
        Passenger passenger = passengerMap.get(passengerId);
        if (passenger == null) {
            throw new IllegalArgumentException("Passenger not found");
        }
        
        // Create ticket
        String ticketId = UUID.randomUUID().toString();
        Ticket ticket = new Ticket(ticketId, flightNumber, passenger);
        
        // Update collections
        ticketMap.put(ticketId, ticket);
        
        Set<Ticket> tickets = flightTickets.getOrDefault(flightNumber, new HashSet<>());
        tickets.add(ticket);
        flightTickets.put(flightNumber, tickets);
        
        return ticket;
    }
    
    public void cancelTicket(String ticketId) {
        Ticket ticket = ticketMap.get(ticketId);
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket not found");
        }
        
        ticket.cancel();
        
        // Check waiting list and assign ticket if available
        Queue<Passenger> waitingList = waitingLists.get(ticket.getFlightNumber());
        if (waitingList != null && !waitingList.isEmpty()) {
            Passenger nextPassenger = waitingList.poll();
            bookTicket(ticket.getFlightNumber(), nextPassenger.getId());
            System.out.println("Ticket assigned to waiting passenger: " + nextPassenger.getName());
        }
    }
    
    public void addToWaitingList(String flightNumber, String passengerId) {
        Passenger passenger = passengerMap.get(passengerId);
        if (passenger == null) {
            throw new IllegalArgumentException("Passenger not found");
        }
        
        Queue<Passenger> waitingList = waitingLists.getOrDefault(
            flightNumber, 
            new PriorityQueue<>(new PassengerPriorityComparator())
        );
        
        waitingList.add(passenger);
        waitingLists.put(flightNumber, waitingList);
    }
    
    public List<Passenger> getWaitingListInPriorityOrder(String flightNumber) {
        Queue<Passenger> waitingList = waitingLists.get(flightNumber);
        if (waitingList == null) {
            return new ArrayList<>();
        }
        
        // Create a copy of the priority queue to avoid modifying the original
        PriorityQueue<Passenger> tempQueue = new PriorityQueue<>(waitingList);
        List<Passenger> result = new ArrayList<>();
        
        while (!tempQueue.isEmpty()) {
            result.add(tempQueue.poll());
        }
        
        return result;
    }
    
    public List<Ticket> getFlightTickets(String flightNumber) {
        Set<Ticket> tickets = flightTickets.getOrDefault(flightNumber, new HashSet<>());
        return new ArrayList<>(tickets);
    }
    
    // Main method for demonstration
    public static void main(String[] args) {
        FlightTicketBookingSystem system = new FlightTicketBookingSystem();
        
        // Add passengers
        Passenger p1 = new Passenger("P1", "John Smith", MembershipStatus.GOLD, 
                                   SpecialNeeds.NONE, LocalDateTime.now().minusHours(5));
        Passenger p2 = new Passenger("P2", "Mary Johnson", MembershipStatus.PLATINUM, 
                                   SpecialNeeds.NONE, LocalDateTime.now().minusHours(2));
        Passenger p3 = new Passenger("P3", "Robert Brown", MembershipStatus.NONE, 
                                   SpecialNeeds.WHEELCHAIR, LocalDateTime.now().minusHours(8));
        Passenger p4 = new Passenger("P4", "Sarah Davis", MembershipStatus.SILVER, 
                                   SpecialNeeds.INFANT, LocalDateTime.now().minusHours(4));
        
        system.addPassenger(p1);
        system.addPassenger(p2);
        system.addPassenger(p3);
        system.addPassenger(p4);
        
        // Book tickets
        Ticket t1 = system.bookTicket("FL123", "P1");
        Ticket t2 = system.bookTicket("FL123", "P2");
        
        // Add to waiting list
        system.addToWaitingList("FL123", "P3");
        system.addToWaitingList("FL123", "P4");
        
        // Check waiting list priority
        System.out.println("Waiting list in priority order:");
        system.getWaitingListInPriorityOrder("FL123").forEach(System.out::println);
        
        // Cancel ticket and check if waiting list is updated
        System.out.println("\nCancelling ticket for: " + t1.getPassenger().getName());
        system.cancelTicket(t1.getTicketId());
        
        // Check waiting list again
        System.out.println("\nWaiting list after cancellation:");
        system.getWaitingListInPriorityOrder("FL123").forEach(System.out::println);
        
        // Check tickets for the flight
        System.out.println("\nCurrent tickets for flight FL123:");
        system.getFlightTickets("FL123").forEach(ticket -> 
            System.out.println(ticket.getPassenger().getName() + 
                             (ticket.isCancelled() ? " (Cancelled)" : ""))
        );
    }
}
