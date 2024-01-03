package este;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {
    private static final String URL = "jdbc:mysql://localhost:3306/school";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

       
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (connection != null) {
                System.out.println("Connexion réussie !");

                createTable(connection);
                insertTestData(connection);

            } else {
                System.out.println("Échec de la connexion.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS etudiants ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "nom VARCHAR(100),"
                + "prenom VARCHAR(100),"
                + "age INT"
                + ")";
        try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {
            preparedStatement.executeUpdate();
        }
    }

    private static void insertTestData(Connection connection) throws SQLException {
        String insertDataSQL = "INSERT INTO etudiants (nom, prenom, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertDataSQL)) {
            preparedStatement.setString(1, "Doe");
            preparedStatement.setString(2, "John");
            preparedStatement.setInt(3, 22);
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "Roe");
            preparedStatement.setString(2, "Jane");
            preparedStatement.setInt(3, 20);
            preparedStatement.executeUpdate();
        }
    }

}
