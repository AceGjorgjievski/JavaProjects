package mk.ukim.finki.vezhbi.os.tcp;

import mk.ukim.finki.os.auds.aud_6_Networking.tcp.server.HTTPWorkerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP extends Thread {

    private int port;

    public TCP(int port) {
        this.port = port;
    }


    @Override
    public void run() {
        System.out.println("TCP Server is starting...");
        ServerSocket server = null;

        try {
            server = new ServerSocket(this.port);
        } catch (IOException e) {
            System.err.println("TCP Server failed to start.");
            return;
        }

        System.out.println("TCP Server is started.");
        System.out.println("Waiting for connections ...");


        while (true) {
            Socket socket = new Socket();

            try {
                socket = server.accept();
                new HTTPWorkerThread(socket).start();
            } catch (IOException e) {
                System.out.println("The user has disconnected.");
            }
        }
    }

    public static void main(String[] args) {
        TCP tcp = new TCP(9000);
        tcp.start();
    }
}
