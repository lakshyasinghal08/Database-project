import java.sql.*;

public class BookService {

    public static void addBook(int id, String title, String author, int qty) {
        try {
            Connection conn = DBConnection.getConnection();

            String query = "INSERT INTO Books VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, id);
            ps.setString(2, title);
            ps.setString(3, author);
            ps.setInt(4, qty);
            ps.setInt(5, qty);

            ps.executeUpdate();
            System.out.println("Book added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewBooks() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM Books");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("BookID") + " | " +
                    rs.getString("Title") + " | " +
                    rs.getString("Author") + " | " +
                    rs.getInt("Available")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void searchBook(int id) {
        try {
            Connection conn = DBConnection.getConnection();

            String query = "SELECT * FROM Books WHERE BookID = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(
                    rs.getInt("BookID") + " | " +
                    rs.getString("Title") + " | " +
                    rs.getString("Author")
                );
            } else {
                System.out.println("Book not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteBook(int id) {
        try {
            Connection conn = DBConnection.getConnection();

            String query = "DELETE FROM Books WHERE BookID = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Book deleted!");
            } else {
                System.out.println("Book not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateBook(int id, String title, String author, int qty) {
        try {
            Connection conn = DBConnection.getConnection();

            String query = "UPDATE Books SET Title=?, Author=?, Quantity=?, Available=? WHERE BookID=?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setInt(3, qty);
            ps.setInt(4, qty);
            ps.setInt(5, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Book updated!");
            } else {
                System.out.println("Book not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}