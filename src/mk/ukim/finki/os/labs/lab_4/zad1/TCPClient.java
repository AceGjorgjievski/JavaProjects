package mk.ukim.finki.os.labs.lab_4.zad1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Аце Ѓорѓиевски 201183
 */

public class TCPClient extends Thread {

    private int port;
    private String ipAddress;

    public TCPClient(int port, String ipAddress) {
        this.port = port;
        this.ipAddress = ipAddress;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        PrintWriter writer = null;
        Scanner scanner = null;
        Socket socket = null;

        try {
            socket = new Socket(this.ipAddress, this.port);
            scanner = new Scanner(System.in);
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Write messages...");

            String line = null;
            while (true) {
                line = scanner.nextLine();
                writer.println(line);
                writer.flush();
//
                System.out.println(reader.readLine());
//
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

            if (writer != null) {
                writer.close();
            }

            if (scanner != null) {
                scanner.close();
            }
        }
    }

    public static void main(String[] args) {
        TCPClient client = new TCPClient(9753, "194.149.135.49");
        client.start();
    }
}
