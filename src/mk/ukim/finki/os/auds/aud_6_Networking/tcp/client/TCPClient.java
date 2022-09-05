package mk.ukim.finki.os.auds.aud_6_Networking.tcp.client;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient extends Thread {


    //ako saka da pristapi do dalechen servis, mora da gi znaeme negovite ...
    private String nameServer;//idAddr
    private int port;


    public TCPClient(String nameServer, int port) {
        this.nameServer = nameServer;
        this.port = port;
    }

    @Override
    public void run() {
        //ako sakame da pechatime infor. od serverot shto gi vrakja
        PrintWriter writer = null;
        Scanner scanner = null;
        //za da kreirame nova konekcija do srw, mora da se iskoristi
        //Socket od java.net library
        Socket socket = null;

        try {
            socket = new Socket(this.nameServer, this.port);
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            //outputstream na socketot go koristime kaj PW za da prakjame info do srw
            scanner = new Scanner(System.in);
            String line = null;

            System.out.println("Write messages ... ");

            while (true) {
                line = scanner.nextLine();
                writer.println(line);
                writer.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (scanner != null) {
                scanner.close();
            }

            if (writer != null) {
                writer.close();
            }
        }
    }

    public static void main(String[] args) {
        TCPClient client = new TCPClient("localhost",9000);
        client.start();
    }

}