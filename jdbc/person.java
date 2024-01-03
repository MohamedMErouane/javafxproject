import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String lastname;
    private int age;

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/school";
        String user = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Create a Person object
            Person person = new Person();
            person.setId(1);
            person.setname("John");
            person.setlastname("Doe");
            person.setAge(30);
            insertPerson(connection, person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertPerson(Connection connection, Person person) throws SQLException {
        String sql = "INSERT INTO person (id, name, lastname, age) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setString(2, person.getname());
            preparedStatement.setString(3, person.getlastname());
            preparedStatement.setInt(4, person.getAge());

            preparedStatement.executeUpdate();
            System.out.println("Person inserted into the database.");
        }
    }

    // Getters and setters for fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getlastname() {
        return lastname;
    }

    public void setlastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
