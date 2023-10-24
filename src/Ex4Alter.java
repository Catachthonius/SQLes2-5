
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class Ex4Alter {

    public class Main {
        public static void main(String[] args) {
            String jdbcUrl = "jdbc:mysql://localhost:3306/newdb";
            String user = "root";
            String password = "074211074211";

            try (Connection conn = DriverManager.getConnection(jdbcUrl, user, password)) {
                System.out.println("Successfully connected to database");

                // Add "Country" to "Students" table
                String addColumnQuery = "ALTER TABLE students ADD COLUMN country VARCHAR(30)";
                Statement addColumnStatement = conn.createStatement();
                addColumnStatement.executeUpdate(addColumnQuery);
                System.out.println("Added 'country' column to the 'students' table.");

                // Add values to the column
                String populateDataQuery = "UPDATE students SET country = ? WHERE student_id = ?";
                try (java.sql.PreparedStatement preparedStatement = conn.prepareStatement(populateDataQuery)) {

                    // Add "country" and its values to the column;
                    // As per description, we will add "Italy" for the first two students,
                    // and "Germany" for the other 2

                    preparedStatement.setString(1, "Italy");
                    preparedStatement.setInt(2, 1);
                    preparedStatement.executeUpdate();

                    preparedStatement.setString(1, "Italy");
                    preparedStatement.setInt(2, 2);
                    preparedStatement.executeUpdate();

                    preparedStatement.setString(1, "Germany");
                    preparedStatement.setInt(2, 3);
                    preparedStatement.executeUpdate();

                    preparedStatement.setString(1, "Germany");
                    preparedStatement.setInt(2, 4);
                    preparedStatement.executeUpdate();

                    System.out.println("Updated 'country' column with the desired values.");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

}