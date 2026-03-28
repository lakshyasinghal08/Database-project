import java.sql.*;
import java.time.LocalDate;

public class IssueService {

    public static void issueBook(int bookId, int memberId) {
        try {
            Connection conn = DBConnection.getConnection();

            String check = "SELECT Available FROM Books WHERE BookID=?";
            PreparedStatement ps1 = conn.prepareStatement(check);
            ps1.setInt(1, bookId);
            ResultSet rs = ps1.executeQuery();

            if (rs.next() && rs.getInt("Available") > 0) {

                String issue = "INSERT INTO IssuedBooks (BookID, MemberID, IssueDate, Status) VALUES (?, ?, ?, 'ISSUED')";
                PreparedStatement ps2 = conn.prepareStatement(issue);

                ps2.setInt(1, bookId);
                ps2.setInt(2, memberId);
                ps2.setDate(3, Date.valueOf(LocalDate.now()));

                ps2.executeUpdate();

                String update = "UPDATE Books SET Available = Available - 1 WHERE BookID=?";
                PreparedStatement ps3 = conn.prepareStatement(update);
                ps3.setInt(1, bookId);
                ps3.executeUpdate();

                System.out.println("Book issued!");

            } else {
                System.out.println("Book not available!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void returnBook(int issueId) {
        try {
            Connection conn = DBConnection.getConnection();

            String query = "UPDATE IssuedBooks SET Status='RETURNED', ReturnDate=? WHERE IssueID=?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setInt(2, issueId);

            ps.executeUpdate();

            System.out.println("Book returned!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}