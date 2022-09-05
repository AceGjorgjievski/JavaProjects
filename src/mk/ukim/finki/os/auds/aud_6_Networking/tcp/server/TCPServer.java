package mk.ukim.finki.os.auds.aud_6_Networking.tcp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer extends Thread {


    private int port;

    public TCPServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {

        ServerSocket server = null; //socket koj postojano kje bide aktiven, kje slusha za konekcii, blokira ako nema konekcii
        System.out.println("Server is starting...");

        try {
            server = new ServerSocket(this.port);//ako portata e zafatena, togash se frla excp

        } catch (IOException e) {
            System.err.println("TCP Server failed.");
            return;
        }

        System.out.println("TCP Server is started.");
        System.out.println("Waiting for connection..");

        try {

            while (true) {
                //end-point megju server i client
                Socket socket = null;
                //dodeka ne pristigne novo baranja, blokiraj na ovaa linija
                //kanal preku koj se vrshi komunikacija - socket
                socket = server.accept();
                new HTTPWorkerThread(socket).start();//shtom se dobie nova konekcija, instancirame nov WT
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TCPServer server = new TCPServer(9000);
        server.start();
    }
}