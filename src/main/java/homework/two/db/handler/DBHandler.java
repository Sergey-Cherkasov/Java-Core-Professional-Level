package homework.two.db.handler;

import java.sql.*;
import java.text.SimpleDateFormat;

public class DBHandler {

    private static final String PROTOCOL = "jdbc";
    private static final String SUB_PROTOCOL = "sqlite";
    private static final String NAME_DATABASE = "src/main/resources/networkchat.sqlite";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        String urlDB = String.format("%s:%s:%s", PROTOCOL, SUB_PROTOCOL, NAME_DATABASE);
        return DriverManager.getConnection(urlDB);
    }

    public static String authQuery(String login, String password) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        String user_name = null;
        PreparedStatement ps = connection
                .prepareStatement("select first_name, last_name from users where nickname = ? and pwd = ?");
        ps.setString(1, login);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            user_name = String.format("%s %s",
                    rs.getString("first_name"),
                    rs.getString("last_name"));
        }
        connection.close();
        return user_name;
    }

    public static void createTableUsers() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        String strCreateTableUsers = "create table if not exists users (" +
                "id integer primary key autoincrement," +
                "first_name varchar(30)," +
                "last_name varchar(30)," +
                "nickname varchar(30)," +
                "pwd varchar(30))";
        Statement statement = connection.createStatement();
        statement.executeUpdate(strCreateTableUsers);
//        statement = null;
        connection.close();
    }

    public static void createTableLogs() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        String strCreateTableUsers = "create table if not exists logs (" +
                "id integer primary key autoincrement," +
                "date_time_log datetime default current_timestamp," +
                "text_log text)";
        Statement statement = connection.createStatement();
        statement.executeUpdate(strCreateTableUsers);
//        statement = null;
        connection.close();
    }

    public static void insertRecordUser(String firstName, String lastName, String nickName, String password)
            throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement("insert into users values (null, ?, ?, ?, ?)");
        ps.setString(1, firstName);
        ps.setString(2, lastName);
        ps.setString(3, nickName);
        ps.setString(4, password);
        ps.executeUpdate();
        connection.close();
    }

    public static void insertRecordLog(String textLog)
            throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PreparedStatement ps = connection.prepareStatement("insert into logs (text_log) values (?)");
        ps.setString(1, textLog);
        ps.executeUpdate();
        connection.close();
    }

    public static void changeNickname(String oldNickname, String newNickName)
            throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement("update users set nickname = ?" +
                "where nickname = ?");
        ps.setString(1, newNickName);
        ps.setString(2, oldNickname);
        ps.executeUpdate();
        connection.close();
    }

    public static void deleteTableUsers() throws SQLException, ClassNotFoundException {
        String sql_query = "drop table if exists users;";
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql_query);
        connection.close();
    }

    public static void deleteTableLogs() throws SQLException, ClassNotFoundException {
        String sql_query = "drop table if exists logs;";
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql_query);
        connection.close();
    }

    public static void deleteAllRecordsIntoUsers() throws SQLException, ClassNotFoundException {
        String sql_query = "delete from users;";
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql_query);
        connection.close();
    }

}
