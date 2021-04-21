package dataAccessLayer;

import connection.ConnectionFactory;
import model.Student;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDAO {

    protected static final Logger LOGGER = Logger.getLogger(StudentDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO student (name,address,email,age)" + " VALUES (?,?,?,?)";
    private final static String findStatementString = "SELECT * FROM student where id = ?";

    public Student findById(int studentId) throws SQLException {
        //initializations and create connection with the db
        Student student = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;

        try {
            //prepare a statement
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, studentId);    // set parameters

            //execute query
            rs = findStatement.executeQuery();

            //parsing resultSet
            rs.next();  // fiecare element dintr-un resultSet corespunde la o linie din tabel

            String name = rs.getString("name");
            String address = rs.getString("address");
            String email = rs.getString("email");
            int age = rs.getInt("age");

            //create student
            student = new Student(studentId, name, address, email, age);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"StudentDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return student;
    }

    public static int insert(Student student) throws SQLException {

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        int insertedId = -1;

        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, student.getName());
            insertStatement.setString(2, student.getAddress());
            insertStatement.setString(3, student.getEmail());
            insertStatement.setInt(4, student.getAge());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
                System.out.println("insertedStudentId = " + insertedId);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "StudentDAO:insert " + e.getMessage());
            System.out.println("StudentDAO:insert" + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
}
