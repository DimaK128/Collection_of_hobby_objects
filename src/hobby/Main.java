import java.util.Arrays;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public void printDetails() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Year: " + year);
    }
}

class BookCollection {
    private int count;
    private Book[] books;

    public BookCollection() {
        this.count = 0;
        this.books = new Book[10]; // Initial capacity of 10, you can adjust it as needed
    }

    public void add(Book newBook) {
        if (count == books.length) {
            books = Arrays.copyOf(books, books.length * 2);
        }
        books[count] = newBook;
        count++;
    }

    public void printOne(int i) {
        if (i >= 0 && i < count) {
            books[i].printDetails();
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void printAll() {
        for (int i = 0; i < count; i++) {
            System.out.println("Book " + (i + 1) + ":");
            books[i].printDetails();
        }
    }

    public void find(String s) {
        for (int i = 0; i < count; i++) {
            if (books[i].getTitle().equalsIgnoreCase(s) || books[i].getAuthor().equalsIgnoreCase(s)) {
                books[i].printDetails();
            }
        }
    }

    public void sortByYear() {
        // Bubble sort based on the numeric property (year) of the books
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (books[j].getYear() > books[j + 1].getYear()) {
                    Book temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookCollection bookCollection = new BookCollection();

        System.out.println("Welcome to the Book Database!");

        while (true) {
            System.out.println("Choose an option to proceed:");
            System.out.println("1 - Add books to collection");
            System.out.println("2 - Print all books");
            System.out.println("3 - Sort books by year");
            System.out.println("4 - Search books by title or author");
            System.out.println("0 - Exit the program");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    inputItems(bookCollection, scanner);
                    break;
                case 2:
                    bookCollection.printAll();
                    break;
                case 3:
                    bookCollection.sortByYear();
                    System.out.println("Books sorted by year.");
                    break;
                case 4:
                    System.out.print("Enter title or author to search: ");
                    String searchTerm = scanner.nextLine();
                    bookCollection.find(searchTerm);
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    public static void inputItems(BookCollection collection, Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter book author: ");
        String author = scanner.nextLine();

        System.out.print("Enter book year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        Book newBook = new Book(title, author, year);
        collection.add(newBook);
        System.out.println("Book added to the collection.");
    }
}
