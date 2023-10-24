
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Ex5View {
    public class Student {
        private String name;
        private String surname;

        public Student(String name, String surname) {
            this.name = name;
            this.surname = surname;
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        public void main(String[] args) {
            String jdbcUrl = "jdbc:mysql://localhost:3306/newdb";
            String user = "root";
            String password = "074211074211";

            try (Connection conn = DriverManager.getConnection(jdbcUrl, user, password)) {
                System.out.println("Successfully connected to database");

                // Creating view for "italian_students"
                String createItalianViewQuery = "CREATE OR REPLACE VIEW italian_students AS " +
                        "SELECT name, surname FROM students WHERE country = 'Italy'";
                Statement createItalianViewStatement = conn.createStatement();
                createItalianViewStatement.execute(createItalianViewQuery);
                System.out.println("Created 'italian_students' view");

                // Creating view for "german_students"
                String createGermanViewQuery = "CREATE OR REPLACE VIEW german_students AS " +
                        "SELECT name, surname FROM students WHERE country = 'Germany'";
                Statement createGermanViewStatement = conn.createStatement();
                createGermanViewStatement.execute(createGermanViewQuery);
                System.out.println("Created 'german_students' view");

                // Query for italian students
                String italianSelectQuery = "SELECT name, surname FROM italian_students";
                Statement italianStatement = conn.createStatement();
                ResultSet italianResultSet = italianStatement.executeQuery(italianSelectQuery);

                ArrayList<Student> italianStudents = new ArrayList<>();

                while (italianResultSet.next()) {
                    String name = italianResultSet.getString("name");
                    String surname = italianResultSet.getString("surname");

                    italianStudents.add(new Student(name, surname));
                }

                // Query for German Students
                String germanSelectQuery = "SELECT name, surname FROM german_students";
                Statement germanStatement = conn.createStatement();
                ResultSet germanResultSet = germanStatement.executeQuery(germanSelectQuery);

                ArrayList<Student> germanStudents = new ArrayList<>();

                while (germanResultSet.next()) {
                    String name = germanResultSet.getString("name");
                    String surname = germanResultSet.getString("surname");

                    germanStudents.add(new Student(name, surname));
                }

            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

}