package connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/schooldb";
    private static final String USER = "root";
    private static final String PASSWORD = "tbpeu669944";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    private ConnectionFactory(){
        try{
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() {
        Connection connection = null;
       try{
           connection = DriverManager.getConnection(DBURL, USER, PASSWORD);
       }catch (SQLException e) {
           System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
           LOGGER.log(Level.WARNING, "An error occurred while trying to connect to the database");
           e.printStackTrace();
       } catch (Exception e) {
           e.printStackTrace();
       }
       return connection;
    }

    /**
     * Method to create connection
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return singleInstance.createConnection();
    }

    /**
     *  Method to close connection
     * @param connection
     * @throws SQLException
     */
    public static void close(Connection connection) throws SQLException {
        if(connection!=null){
            try{
                connection.close();
            }catch (SQLException e){
                LOGGER.log(Level.WARNING, "An error occurred while trying to close the connection");
            }
        }
    }

    /**
     * Method to close statement
     * @param statement
     * @throws SQLException
     */
    public static void close(Statement statement) throws SQLException {
        if(statement!=null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occurred while trying to close the statement");
            }
        }
    }

    /**
     * Method to close result set
     * @param resultSet
     * @throws SQLException
     */
    public static void close(ResultSet resultSet) throws SQLException {
        if(resultSet!=null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occurred while trying to close the ResultSet");
            }
        }
    }

}
