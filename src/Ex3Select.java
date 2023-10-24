import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class Ex3Select {


    public class Main {
        public static void main(String[] args) {
            String jdbcUrl = "jdbc:mysql://localhost:3306/newdb";
            String user = "root";
            String password = "074211074211";

            try (Connection conn = DriverManager.getConnection(jdbcUrl, user, password)) {
                System.out.println("Successfully connected to database");

                String query = "SELECT first_name, last_name FROM students";

                Statement statement = conn.createStatement();

                ResultSet resultSet = statement.executeQuery(query);

                ArrayList<String> surnames = new ArrayList<>();

                while (resultSet.next()) {
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");

                    System.out.println("Student Name: " + firstName);
                    surnames.add(lastName);
                }

                System.out.println("Surnames:");
                for (String surname : surnames) {
                    System.out.println(surname);
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }
}