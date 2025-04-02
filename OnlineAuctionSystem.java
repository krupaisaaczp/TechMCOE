import java.util.*;

class Bid {
    String bidder;
    double amount;
    long time;

    public Bid(String bidder, double amount, long time) {
        this.bidder = bidder;
        this.amount = amount;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[Bidder: " + bidder + ", Amount: " + amount + ", Time: " + time + "]";
    }
}

class BidComparator implements Comparator<Bid> {
    public int compare(Bid b1, Bid b2) {
        int amountCompare = Double.compare(b2.amount, b1.amount);
        if (amountCompare == 0) {
            return Long.compare(b1.time, b2.time);
        }
        return amountCompare;
    }
}

class Auction<T> {
    PriorityQueue<Bid> bidQueue = new PriorityQueue<>(new BidComparator());

    public void placeBid(String bidder, double amount) {
        bidQueue.add(new Bid(bidder, amount, System.currentTimeMillis()));
    }

    public void displayBids() {
        while (!bidQueue.isEmpty()) {
            System.out.println(bidQueue.poll());
        }
    }
}

public class OnlineAuctionSystem {
    public static void main(String[] args) {
        Auction<String> auction = new Auction<>();
        auction.placeBid("Alice", 1000);
        auction.placeBid("Bob", 1200);
        auction.placeBid("Charlie", 1100);
        
        System.out.println("Bids in Priority Order:");
        auction.displayBids();
    }
}
