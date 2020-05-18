package homework.three.task04.client;

import homework.three.task04.Book;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    private static final String SERVER_HOST = "localhost";
    private static final int PORT_CONNECT = 8283;

    public static void main(String[] args) {
        Book book = new Book(1, "Harry Potter", "J.K. Rowling");
        Socket socketClient = new Socket();
        try {
            socketClient.connect(new InetSocketAddress(SERVER_HOST, PORT_CONNECT));
            ObjectOutputStream objOS = new ObjectOutputStream(socketClient.getOutputStream());
            objOS.writeObject(book);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socketClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
