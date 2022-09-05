package mk.ukim.finki.vezhbi.os.za_juni.aud_4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer extends Thread{
    private int port;
    private BufferedWriter bufferedWriter;

    public TCPServer(int port, File outputFile) throws IOException {
        this.port = port;
        this.bufferedWriter = new BufferedWriter(new FileWriter(outputFile,true));
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
        System.out.println("Waiting for connections");

        while(true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                new WThread(socket,bufferedWriter).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
