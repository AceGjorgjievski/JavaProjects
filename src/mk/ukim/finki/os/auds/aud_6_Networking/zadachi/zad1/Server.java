package mk.ukim.finki.os.auds.aud_6_Networking.zadachi.zad1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

    private int port;
    private static final String FILE_PATH = "C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\os\\auds\\aud_6_Networking\\zadachi\\result.txt";


    public Server(int port) {
        this.port = port;
    }


    @Override
    public void run() {
        ServerSocket server = null;

        System.out.println("TCP Server is starting...");

        try {
            server = new ServerSocket(this.port);
            System.out.println("TCP Server is started.");

            while(true) {
                Socket socket = new Socket();
                try {
                    socket = server.accept();
                    new WorkerThread(socket,FILE_PATH).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
//            e.printStackTrace();
            System.err.println("TCP Server failed to start.");
            return;
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
