package homework.two.server;

import homework.two.db.handler.DBHandler;
import homework.two.server.models.Server;

import java.sql.SQLException;

/**
 * Основной класс для запуска сервера
 */

public class ServerApplication {

   public static void main(String[] args) {
      initDB();
      if (args.length != 0) {
         Server.getServer(Integer.parseInt(args[0])).initConnection();
      }
      Server.getServer().initConnection();
   }

   private static void initDB() {
      try {
//         DBHandler.deleteAllRecordsIntoUsers();
//         DBHandler.deleteTableUsers();
         DBHandler.createTableUsers();

         DBHandler.deleteTableLogs();
         DBHandler.createTableLogs();
      } catch (SQLException | ClassNotFoundException e) {
         System.err.println("Error of creating database: " + e.getMessage());
         e.printStackTrace();
      }
   }

}
