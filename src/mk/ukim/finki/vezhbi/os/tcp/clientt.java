package mk.ukim.finki.vezhbi.os.tcp;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class clientt extends Thread {

    private String serverName;
    private int port;


    public clientt(String serverName, int port) {
        this.serverName = serverName;
        this.port = port;
    }

    @Override
    public void run() {
        PrintWriter writer = null;
        Scanner scanner = null;
        Socket socket = null;
        BufferedReader reader = null;

        try {
            socket = new Socket(this.serverName, this.port);
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            scanner = new Scanner(System.in);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line = null;
            String lineFromServer = null;
            System.out.println("Write messages ... ");
            while (true) {
                line = scanner.nextLine();


                writer.println(line);
                writer.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (scanner != null) scanner.close();
        }
    }

    public static void main(String[] args) {
        clientt client = new clientt("localhost",9000);
        client.start();
    }
}
