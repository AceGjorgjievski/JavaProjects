package mk.ukim.finki.os.labs.lab_4.zad1;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Аце Ѓорѓиевски 201183
 */

public class TCPServer extends Thread{

    private int port;

    public TCPServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        ServerSocket server = null;
        System.out.println("Server is starting!...");

        try {
            server = new ServerSocket(this.port);
        } catch (IOException e) {
            System.err.println("TCP server failed.");
            return;
        }

        System.out.println("Server started!");
        System.out.println("Waiting for connections...");

        try {
            while(true) {
                Socket socket = new Socket();
                socket = server.accept();
                new SocketWorker(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TCPServer server = new TCPServer(9753);
        server.start();
    }
}
