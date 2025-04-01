import java.time.LocalDate;
import java.util.*;

abstract class LibraryItem {
    private String itemId, title;
    private int yearPublished;
    private boolean checkedOut;
    
    public LibraryItem(String itemId, String title, int yearPublished) {
        this.itemId = itemId;
        this.title = title;
        this.yearPublished = yearPublished;
        this.checkedOut = false;
    }

    public boolean checkOut() {
        if (checkedOut) return false;
        checkedOut = true;
        return true;
    }

    public boolean returnItem() {
        if (!checkedOut) return false;
        checkedOut = false;
        return true;
    }

    public String getTitle() { return title; }
    public boolean isCheckedOut() { return checkedOut; }

    @Override
    public String toString() {
        return title + " (" + yearPublished + ") - " + (checkedOut ? "Checked Out" : "Available");
    }
}

class Book extends LibraryItem {
    private String author;

    public Book(String itemId, String title, String author, int yearPublished) {
        super(itemId, title, yearPublished);
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book: " + super.toString() + " by " + author;
    }
}

class Patron {
    private String name;
    private List<LibraryItem> checkedOutItems = new ArrayList<>();

    public Patron(String name) { this.name = name; }

    public boolean checkOutItem(LibraryItem item) {
        if (item.checkOut()) {
            checkedOutItems.add(item);
            return true;
        }
        return false;
    }

    public boolean returnItem(LibraryItem item) {
        if (item.returnItem()) {
            checkedOutItems.remove(item);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Patron: " + name + " - Checked Out Items: " + checkedOutItems;
    }
}

class Library {
    private String name;
    private Map<String, LibraryItem> inventory = new HashMap<>();

    public Library(String name) { this.name = name; }

    public void addItem(LibraryItem item) { inventory.put(item.getTitle(), item); }

    public LibraryItem findItem(String title) {
        return inventory.get(title);
    }

    @Override
    public String toString() {
        return name + " Library - Items: " + inventory.values();
    }
}

public class LibraryManagementDemo {
    public static void main(String[] args) {
        Library library = new Library("Main Library");
        Book book1 = new Book("B001", "The Great Gatsby", "F. Scott Fitzgerald", 1925);
        Patron patron = new Patron("John Doe");

        library.addItem(book1);
        System.out.println(library);

        patron.checkOutItem(book1);
        System.out.println(patron);

        patron.returnItem(book1);
        System.out.println(patron);
    }
}
