import java.sql.*;

public class LibraryDAO {

    // Add Book
    public void addBook(Book book) {
        String query = "INSERT INTO Books (book_id, title, author, category, quantity) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, book.getBookId());
            pst.setString(2, book.getTitle());
            pst.setString(3, book.getAuthor());
            pst.setString(4, book.getCategory());
            pst.setInt(5, book.getQuantity());

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("Book added successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Error adding book.");
            e.printStackTrace();
        }
    }

    // Show All Books
    public void showAllBooks() {
        String query = "SELECT * FROM Books";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            boolean found = false;

            System.out.println("\n========== ALL BOOKS ==========");
            while (rs.next()) {
                found = true;
                System.out.println("Book ID   : " + rs.getInt("book_id"));
                System.out.println("Title     : " + rs.getString("title"));
                System.out.println("Author    : " + rs.getString("author"));
                System.out.println("Category  : " + rs.getString("category"));
                System.out.println("Quantity  : " + rs.getInt("quantity"));
                System.out.println("-----------------------------------");
            }

            if (!found) {
                System.out.println("No books found.");
            }

        } catch (SQLException e) {
            System.out.println("Error fetching books.");
            e.printStackTrace();
        }
    }

    // Search Book by ID
    public void searchBookById(int id) {
        String query = "SELECT * FROM Books WHERE book_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                System.out.println("\nBook Found:");
                System.out.println("Book ID   : " + rs.getInt("book_id"));
                System.out.println("Title     : " + rs.getString("title"));
                System.out.println("Author    : " + rs.getString("author"));
                System.out.println("Category  : " + rs.getString("category"));
                System.out.println("Quantity  : " + rs.getInt("quantity"));
                System.out.println("-----------------------------------");
            } else {
                System.out.println("Book not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error searching book.");
            e.printStackTrace();
        }
    }

    // Search Book by Title
    public void searchBookByTitle(String title) {
        String query = "SELECT * FROM Books WHERE title LIKE ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, "%" + title + "%");
            ResultSet rs = pst.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println("Book ID   : " + rs.getInt("book_id"));
                System.out.println("Title     : " + rs.getString("title"));
                System.out.println("Author    : " + rs.getString("author"));
                System.out.println("Category  : " + rs.getString("category"));
                System.out.println("Quantity  : " + rs.getInt("quantity"));
                System.out.println("-----------------------------------");
            }

            if (!found) {
                System.out.println("No matching book found.");
            }

        } catch (SQLException e) {
            System.out.println("Error searching by title.");
            e.printStackTrace();
        }
    }

    // Update Book
    public void updateBook(int id, String newTitle, String newAuthor, String newCategory, int newQuantity) {
        String query = "UPDATE Books SET title = ?, author = ?, category = ?, quantity = ? WHERE book_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, newTitle);
            pst.setString(2, newAuthor);
            pst.setString(3, newCategory);
            pst.setInt(4, newQuantity);
            pst.setInt(5, id);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("Book updated successfully!");
            } else {
                System.out.println("Book not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating book.");
            e.printStackTrace();
        }
    }

    // Delete Book
    public void deleteBook(int id) {
        String query = "DELETE FROM Books WHERE book_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, id);
            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("Book deleted successfully!");
            } else {
                System.out.println("Book not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting book.");
            e.printStackTrace();
        }
    }

    // Count Total Books
    public void countBooks() {
        String query = "SELECT COUNT(*) AS total FROM Books";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            if (rs.next()) {
                System.out.println("Total Books in Library: " + rs.getInt("total"));
            }

        } catch (SQLException e) {
            System.out.println("Error counting books.");
            e.printStackTrace();
        }
    }
}