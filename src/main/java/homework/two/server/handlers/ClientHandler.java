package homework.two.server.handlers;

import homework.two.auth.AuthenticationServiceInterface;
import homework.two.common.Command;
import homework.two.common.CommandType;
import homework.two.common.commands.AuthCommand;
import homework.two.common.commands.BroadcastMessageCommand;
import homework.two.common.commands.PrivateMessageCommand;
import homework.two.db.handler.DBHandler;
import homework.two.server.models.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

/**
 * Класс обработчика действий клиента
 */
public class ClientHandler {
   private final Server server;
   private final Socket socket;
   private ObjectInputStream inputStream;
   private ObjectOutputStream outputStream;

   private String name;

   private final AuthenticationServiceInterface authenticationServiceInterface;

   /**
    * Конструктор обработчика действий клиента запускает аутентификацию и метод чтения сообщений
    * в отдельном потоке.
    *
    * @param server экземпляр запущенного сервера
    * @param socket экземпляр сокета
    */
   public ClientHandler(Server server, Socket socket) {
      this.server = server;
      this.socket = socket;
      this.authenticationServiceInterface = server.getAuthenticationServiceInterface();
   }

   /**
    * Метод осуществляет закрытие соединения и освобождение ресурсов
    */
   private void closeConnection() {
      try {
         server.unsubscribe(this);
         server.broadcastMessage(Command.broadcastMessageCommand(name + " вышел из чата"));
         socket.close();
      } catch (IOException e) {
         System.err.println(e.getMessage());
      }
   }

   /**
    * Метод осуществляет чтение сообщений, полученных от сервера
    */
   private void readMessages() throws IOException {
      while (true) {
         Command command = readCommand();
         if (command == null) {
            continue;
         }
         switch (command.getType()) {
            case CMD_END:
               return;
            case CMD_BROADCAST_MESSAGE:
               BroadcastMessageCommand data = (BroadcastMessageCommand) command.getData();
               server.broadcastMessage(Command.messageCommand(name, data.getMessage()));
               break;
            case CMD_PRIVATE_MESSAGE:
               PrivateMessageCommand privateMessageCommand = (PrivateMessageCommand) command.getData();
               String receiver = privateMessageCommand.getReceiver();
               String message = privateMessageCommand.getMessage();
               server.sendPrivateMessage(receiver, Command.messageCommand(name, message));
               break;
            default:
               String errorMessage = "Unknown type of command : " + command.getType();
               System.err.println(errorMessage);
               sendMessage(Command.errorCommand(errorMessage));
         }
      }
   }

   private Command readCommand() throws IOException {
      try {
         return (Command) inputStream.readObject();
      } catch (ClassNotFoundException e) {
         String errorMessage = "Unknown type of object from client!";
         System.err.println(errorMessage);
         e.printStackTrace();
         sendMessage(Command.errorCommand(errorMessage));
         return null;
      }
   }

   /**
    * Метод аутентификации клиента
    */
   private void authentication() throws IOException, SQLException, ClassNotFoundException {

      Thread timeOut = authTimeout();

      while (true) {
         Command command = readCommand();

         if (command == null) {
            continue;
         }

         switch (command.getType()){
            case CMD_AUTH:
               if (processAuthCommand(command)) {
                  timeOut.interrupt();
                  return;
               }
               break;
            case CMD_REG_REQUEST:
               timeOut.interrupt();
               sendMessage(command);
               break;
            case CMD_REG:
               sendMessage(command);
               break;
            case CMD_END:
               timeOut.interrupt();
               return;
            default:
               String errorMessage = "Illegal command for authentication: " + command.getType();
               System.err.println(errorMessage);
               sendMessage(Command.errorCommand(errorMessage));
         }
      }
   }

   /**
    * Метод отслеживает время авторизации пользователя в чате
    */
   private Thread authTimeout() {
      Thread timeOut = new Thread(() -> {
         try {
            Thread.sleep(30000);
            String errorMessage = "Вышло время авторизации.\nПерезапустите приложение";
            DBHandler.insertRecordLog("Time for authorization has expired");
            sendMessage(Command.errorCommand(errorMessage));
            System.err.println(errorMessage);
            closeConnection();
         } catch (InterruptedException e) {
            System.out.println("Поток timeOut успешно прерван");
         } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
         }
      }, "timeOut");
      timeOut.start();
      return timeOut;
   }

   private boolean processAuthCommand(Command command) throws IOException, SQLException, ClassNotFoundException {
      AuthCommand authCommand = (AuthCommand) command.getData();
      String login = authCommand.getLogin();
      String password = authCommand.getPassword();
      String nickname = authenticationServiceInterface.getUserNameByLoginPassword(login, password);
      if (nickname == null) {
         String textWrongLoginPassword = "Неверные логин/пароль!";
         DBHandler.insertRecordLog(textWrongLoginPassword);
         sendMessage(Command.authErrorCommand(textWrongLoginPassword));
      } else if (server.isUserNameBusy(nickname)) {
         String textAccountAlreadyUsed = "Учетная запись уже используется!";
         DBHandler.insertRecordLog(textAccountAlreadyUsed);
         sendMessage(Command.authErrorCommand(textAccountAlreadyUsed));
      } else {
         String textSuccessfullyLoggedIn = "The user has successfully logged in";
         authCommand.setUsername(nickname);
         sendMessage(command);
         setName(nickname);
         DBHandler.insertRecordLog(textSuccessfullyLoggedIn);
         server.broadcastMessage(Command.messageCommand(null, nickname + " Зашел в чат!"));
         server.subscribe(this);
         return true;
      }
      return false;
   }

   /**
    * Метод осуществляет отправку сообщения на сервер
    *
    * @param command текст с данными сообщения
    */
   public void sendMessage(Command command) throws IOException {
      outputStream.writeObject(command);
   }

   public void handle() throws IOException {
      inputStream = new ObjectInputStream(socket.getInputStream());
      outputStream = new ObjectOutputStream(socket.getOutputStream());

      new Thread(() -> {
         try {
            authentication();
            readMessages();
         } catch (IOException e) {
            System.out.println("Connect has been failed.");
//            System.err.println(e.getMessage());
         } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
         } finally {
            closeConnection();
         }
      }).start();
   }

   private void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

}
