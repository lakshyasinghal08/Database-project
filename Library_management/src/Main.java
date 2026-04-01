import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LibraryDAO library = new LibraryDAO();

        int choice;

        do {
            System.out.println("\n========== LIBRARY MANAGEMENT SYSTEM ==========");
            System.out.println("1. Add Book");
            System.out.println("2. Show All Books");
            System.out.println("3. Search Book by ID");
            System.out.println("4. Search Book by Title");
            System.out.println("5. Update Book");
            System.out.println("6. Delete Book");
            System.out.println("7. Count Total Books");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();

                    System.out.print("Enter Category: ");
                    String category = sc.nextLine();

                    System.out.print("Enter Quantity: ");
                    int quantity = sc.nextInt();

                    Book book = new Book(id, title, author, category, quantity);
                    library.addBook(book);
                    break;

                case 2:
                    library.showAllBooks();
                    break;

                case 3:
                    System.out.print("Enter Book ID to search: ");
                    int searchId = sc.nextInt();
                    library.searchBookById(searchId);
                    break;

                case 4:
                    System.out.print("Enter Title to search: ");
                    String searchTitle = sc.nextLine();
                    library.searchBookByTitle(searchTitle);
                    break;

                case 5:
                    System.out.print("Enter Book ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Title: ");
                    String newTitle = sc.nextLine();

                    System.out.print("Enter New Author: ");
                    String newAuthor = sc.nextLine();

                    System.out.print("Enter New Category: ");
                    String newCategory = sc.nextLine();

                    System.out.print("Enter New Quantity: ");
                    int newQuantity = sc.nextInt();

                    library.updateBook(updateId, newTitle, newAuthor, newCategory, newQuantity);
                    break;

                case 6:
                    System.out.print("Enter Book ID to delete: ");
                    int deleteId = sc.nextInt();
                    library.deleteBook(deleteId);
                    break;

                case 7:
                    library.countBooks();
                    break;

                case 8:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 8);

        sc.close();
    }
}