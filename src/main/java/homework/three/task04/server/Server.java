package homework.three.task04.server;

import homework.three.task04.Book;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int SERVER_PORT = 8283;
    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            Socket clientSocket = serverSocket.accept();
            ObjectInputStream objIS = new ObjectInputStream(clientSocket.getInputStream());
            while (true) {
                Book book = (Book) objIS.readObject();
                if (book == null) {
                    continue;
                }
                System.out.println(book.getInfo());
                break;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
