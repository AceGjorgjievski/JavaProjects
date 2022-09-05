package mk.ukim.finki.vezhbi.os.za_juni.aud_4.zad_2;


import mk.ukim.finki.vezhbi.os.tcp.TCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer extends Thread{
    private int port;

    public TCPServer(int port) {
        this.port = port;
    }


    @Override
    public void run() {
        ServerSocket serverSocket = null;
        System.out.println("TCPServer is starting...");

        try {
            serverSocket = new ServerSocket(this.port);
        } catch (IOException e) {
            System.err.println("TCPServer failed to start!");
            return;
        }
        System.out.println("TCPServer is started!");
        System.out.println("Waiting for connections...");


        while (true) {
            Socket socket = null;

            try {
                socket = serverSocket.accept();
                new WorkerThread(socket).start();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public static void main(String[] args) {
        TCPServer server = new TCPServer(9357);
        server.start();
    }
}
