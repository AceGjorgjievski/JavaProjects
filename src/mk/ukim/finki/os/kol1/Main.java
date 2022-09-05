package mk.ukim.finki.os.kol1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class TCPServer extends Thread {

    private int port;
    private ServerSocket serverSocket;


    public TCPServer(int port) {
        this.port = port;
    }


    @Override
    public void run() {

        try {
            System.out.println("TCP Server is starting...");

            serverSocket = new ServerSocket(this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("TCP Server is started!");
        System.out.println("Waiting for connections...");


        while (true) {
            Socket socket = new Socket();

            try {
                socket = serverSocket.accept();

                SocketWorker wt = new SocketWorker(socket);
                System.out.println("hi");
                wt.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class SocketWorker extends Thread{

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public SocketWorker(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {

        try {
            reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            socket = new Socket();

            String line = null;

            System.out.printf("Connected: %s:%d\n",
                    this.socket.getInetAddress().getHostAddress(),
                    this.socket.getPort());


            while(!(line = reader.readLine()).isEmpty()) {
                System.out.printf("%s:hello\n",line);
                writer.println(line);
                writer.flush();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader!= null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(writer!= null) {
                writer.close();
            }

            if(socket!= null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}


class Client extends Thread {

    private int port = Connection.PORT;
    private String ipAddress = Connection.IP_ADDRESS;
    private Scanner scanner;
    private Socket socket;
    private PrintWriter writer;

    public Client(int port, String ipAddress) {
        this.port = port;
        this.ipAddress = ipAddress;
    }

    @Override
    public void run() {

        try {
            socket = new Socket(this.ipAddress, this.port);
            scanner = new Scanner(System.in);
            writer = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));


            System.out.println("Writer messages ... ");
            String line = null;
            while (true) {
                line = scanner.nextLine();
                writer.write(line);
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

            if (writer != null) {
                writer.close();
            }

            if (scanner != null) {
                scanner.close();
            }

        }

    }
}

/**
 * Аце Ѓорѓиевски 201183
 */

public class Main {

    public static void main(String[] args) {
        TCPServer server = new TCPServer(Connection.PORT);
        server.start();

        Client client = new Client(Connection.PORT, Connection.IP_ADDRESS);
        client.start();
    }
}
