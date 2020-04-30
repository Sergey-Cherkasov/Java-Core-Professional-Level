package homework.two.auth;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Map;
import java.util.Objects;

/**
 * Класс аутентификации клиентов
 */
public class AuthenticationService implements AuthenticationServiceInterface{

   private static final String NAME_CLASS = "org.sqlite.JDBC";
   private static final String PROTOCOL = "jdbc";
   private static final String SUB_PROTOCOL = "sqlite";
   private static final String NAME_DB = "src/main/resources/NetworkChat.sqlite";

   private static final Map<DataUsers, String> USERNAME_BY_LOGIN_PASSWORD = Map.of(
           new DataUsers("Denials", "pass1"), "Denials",
           new DataUsers("Lawson", "pass2"), "Lawson",
           new DataUsers("McGregor", "pass3"), "McGregor"
   );

   private Connection connectionDB;
   private PreparedStatement preparedStatement;

   /**
    * Метод открывает соединение с БД
    */
   @Override
   public void start() {
      System.out.println("Authentication service has been started");

      try {
         Class.forName(NAME_CLASS);
         String urlDB = String.format("%s:%s:%s", PROTOCOL, SUB_PROTOCOL, NAME_DB);
         connectionDB = DriverManager.getConnection(urlDB);
         preparedStatement = connectionDB.prepareStatement("select name from users where nickname = ? and pwd = ?");
      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
      }

   }

   /**
    * Метод закрывает соединение с БД
    */
   @Override
   public void stop() {
      System.out.println("Authentication service has been stopped");
      try {
         connectionDB.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   /**
    * Метод возвращает имя пользователя при совпадении логина/пароля, либо null.
    * @param login логин пользователя
    * @param password пароль пользователя
    * @return имя пользователя, либо null.
    */
   @Override
   public String getUserNameByLoginPassword(String login, String password) {
      ResultSet resultSet;
      String userName= "";
      try {
         preparedStatement.setString(1, login);
         preparedStatement.setString(2, password);
         resultSet = preparedStatement.executeQuery();
         userName = resultSet.getString("name");
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return userName;
//      return USERNAME_BY_LOGIN_PASSWORD.get(new DataUsers(login, password));
   }

   /**
    * Внутреннй клас, описывающий модель данных клиента (логин/пароль/имя пользователя)
    */
   private static class DataUsers{
      private final String login;
      private final String password;

      public DataUsers(String login, String password){
         this.login = login;
         this.password = password;
      }

      @Override
      public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         DataUsers dataUsers = (DataUsers) o;
         return Objects.equals(login, dataUsers.login) &&
                 Objects.equals(password, dataUsers.password);
      }

      @Override
      public int hashCode() {
         return Objects.hash(login, password);
      }
   }

}
