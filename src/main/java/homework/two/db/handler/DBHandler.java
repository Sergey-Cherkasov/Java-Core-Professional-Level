package homework.two.db.handler;

import java.sql.*;

public class DBHandler {

    private static final String PROTOCOL = "jdbc";
    private static final String SUB_PROTOCOL = "sqlite";
    private static final String NAME_DATABASE = "src/main/resources/networkchat.sqlite";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        String urlDB = String.format("%s:%s:%s", PROTOCOL, SUB_PROTOCOL, NAME_DATABASE);
        return DriverManager.getConnection(urlDB);
    }

    public static ResultSet authQuery(Connection connection, String login, String password) throws SQLException {
        PreparedStatement ps = connection
                .prepareStatement("select last_name from users where nickname = ? and pwd = ?");
        ps.setString(1, login);
        ps.setString(2, password);
        return ps.executeQuery();
    }

    public static void createTableUsers(Connection connection) throws SQLException {
         String strCreateTableUsers = "create table if not exists users (" +
                 "id integer primary key autoincrement," +
                 "first_name varchar(30)," +
                 "last_name varchar(30)," +
                 "nickname varchar(30)," +
                 "pwd varchar(30))";
         Statement statement = connection.createStatement();
         int intResult = statement.executeUpdate(strCreateTableUsers);
         statement = null;
    }

    public static void insertRecord(String firstName, String lastName, String nickName, String password)
            throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        String strQuery = "insert into users values" +
                "(null, 'Denials', 'Denials', 'pass1')";
        PreparedStatement ps = connection.prepareStatement("insert into users values (null, ?, ?, ?, ?)");
        ps.setString(1, firstName);
        ps.setString(2, lastName);
        ps.setString(3, nickName);
        ps.setString(4, password);
        ps.executeUpdate();
        connection.close();
    }
}
