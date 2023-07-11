package Connection;

import java.sql.*;

public class ConnectionFactory {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String pass = "victor";
    private static ConnectionFactory singleInstance = new ConnectionFactory();
    private Connection createConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static Connection getConnection(){
        return singleInstance.createConnection();
    }

    public static void close(Connection connection){
        if(connection != null){
            try{
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void close(Statement statement){
        if(statement != null){
            try{
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void close(ResultSet resultSet){
        if(resultSet != null){
            try{
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
