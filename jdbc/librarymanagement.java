package foundation;

import java.sql.*;
import java.util.Scanner;

public class librarymanagement {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/library_db";
        String username = "root";
        String password = "Mahesh@2005";

        try {

            Connection con = DriverManager.getConnection(
                url, username, password
            );

            System.out.println("Connected to Library Database!");

            while (true) {

                System.out.println("===== LIBRARY MANAGEMENT SYSTEM =====");
                System.out.println("1. View All Books");
                System.out.println("2. Add Books");
                System.out.println("3. Delete Books");
                System.out.println("4. Exit");

                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                        String selectQuery = "SELECT * FROM books";

                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(selectQuery);

                        System.out.println("\n===== AVAILABLE BOOKS =====");

                        boolean found = false;

                        while (rs.next()) {
                            found = true;

                            System.out.println("Book ID: " + rs.getInt("id"));
                            System.out.println("Name: " + rs.getString("name"));
                            System.out.println("Author: " + rs.getString("author"));
                            System.out.println("Price: " + rs.getDouble("price"));
                            System.out.println("-------------------------");
                        }

                        if (!found) {
                            System.out.println("No books available.");
                        }

                        rs.close();
                        st.close();
                        break;

                    case 2:
                        System.out.print("Enter book name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter author name: ");
                        String author = sc.nextLine();

                        System.out.print("Enter book price: ");
                        double price = sc.nextDouble();
                        sc.nextLine();

                        String insertQuery =
                            "INSERT INTO books (name, author, price) VALUES (?, ?, ?)";

                        PreparedStatement ps =
                            con.prepareStatement(insertQuery);

                        ps.setString(1, name);
                        ps.setString(2, author);
                        ps.setDouble(3, price);

                        ps.executeUpdate();

                        System.out.println("Book added successfully!");

                        ps.close();
                        break;

                    case 3:
                        System.out.print("Enter Book ID to delete: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        String deleteQuery =
                            "DELETE FROM books WHERE id = ?";

                        PreparedStatement psDelete =
                            con.prepareStatement(deleteQuery);

                        psDelete.setInt(1, id);

                        int rowsDeleted = psDelete.executeUpdate();

                        if (rowsDeleted > 0) {
                            System.out.println("Book deleted successfully!");
                        } else {
                            System.out.println("Book ID not found.");
                        }

                        psDelete.close();
                        break;

                    case 4:
                        System.out.println(
                            "Thank you for visiting the library!"
                        );

                        con.close();
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
