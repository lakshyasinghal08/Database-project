import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== LIBRARY SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Update Book");
            System.out.println("6. Issue Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Title: ");
                    String title = sc.nextLine();

                    System.out.print("Author: ");
                    String author = sc.nextLine();

                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();

                    BookService.addBook(id, title, author, qty);
                    break;

                case 2:
                    BookService.viewBooks();
                    break;

                case 3:
                    System.out.print("Enter Book ID: ");
                    int searchId = sc.nextInt();
                    BookService.searchBook(searchId);
                    break;

                case 4:
                    System.out.print("Enter Book ID: ");
                    int delId = sc.nextInt();
                    BookService.deleteBook(delId);
                    break;

                case 5:
                    System.out.print("Enter Book ID: ");
                    int upId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("New Title: ");
                    String newTitle = sc.nextLine();

                    System.out.print("New Author: ");
                    String newAuthor = sc.nextLine();

                    System.out.print("New Quantity: ");
                    int newQty = sc.nextInt();

                    BookService.updateBook(upId, newTitle, newAuthor, newQty);
                    break;

                case 6:
                    System.out.print("Book ID: ");
                    int bId = sc.nextInt();

                    System.out.print("Member ID: ");
                    int mId = sc.nextInt();

                    IssueService.issueBook(bId, mId);
                    break;

                case 7:
                    System.out.print("Issue ID: ");
                    int issueId = sc.nextInt();

                    IssueService.returnBook(issueId);
                    break;

                case 8:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}