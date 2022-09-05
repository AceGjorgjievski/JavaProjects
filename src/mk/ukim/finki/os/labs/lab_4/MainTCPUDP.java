package mk.ukim.finki.os.labs.lab_4;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class TCPServer extends Thread {

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
            while (true) {
                Socket socket = new Socket();
                socket = server.accept();
                new SocketWorker(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class TCPClient extends Thread {

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
}

class SocketWorker extends Thread{

    private Socket socket;

    public SocketWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        PrintWriter writer = null;

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = null;

            System.out.printf("Connected: %s:%d\n",
                    socket.getInetAddress().getHostAddress(),
                    socket.getPort());

            String line = null;

            while((line = reader.readLine()) != null) {
                System.out.printf("Message from: %s:%d-- %s\n",
                        socket.getInetAddress().getHostAddress(),
                        socket.getPort(),
                        //dtf.format(now = LocalDateTime.now()),
                        line);
                writer.println(line);
                writer.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(writer!=null) {
                writer.close();
            }

            if(socket!=null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/**
 * Аце Ѓорѓиевски 201183
 *
 * Се извинувам, не знаев како да пратам посебни 2 фајла со .јава, втората задача е искоментирана.
 */

public class MainTCPUDP {

    public static void main(String[] args) {
        TCPServer server1 = new TCPServer(9753);
        server1.start();
        TCPClient client1 = new TCPClient(9753, "194.149.135.49");
        client1.start();


//        UDPServer server2 = new UDPServer(9753);
//        server2.start();
//        UDPClient client2 = new UDPClient("194.149.135.49",9753, "201183");
//        client2.start();
    }
}

class UDPServer extends Thread {

    private DatagramSocket socket;
    private byte[] buffer = new byte[256];

    public UDPServer(int port) {
        try {
            this.socket = new DatagramSocket(port);
        } catch (SocketException e) {
            System.err.printf("The port %d is busy!\n", port);
            return;
        }
    }

    @Override
    public void run() {
        DatagramPacket packet = new DatagramPacket(this.buffer, this.buffer.length);

        try {
            this.socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength());

            //String respondMessage = "Received: " + received;
            System.out.println("Received: " + received);
            //this.buffer = respondMessage.getBytes();
            InetAddress address = packet.getAddress();
            int port = packet.getPort();

            packet = new DatagramPacket(this.buffer, this.buffer.length, address, port);
            this.socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}

class UDPClient extends Thread {

    private String nameServer;
    private int port;

    private DatagramSocket socket;
    private InetAddress address;
    private String message;
    private byte[] buffer;

    public UDPClient(String nameServer, int serverPort, String message) {
        this.nameServer = nameServer;
        this.port = serverPort;
        this.message = message;

        try {
            this.socket = new DatagramSocket();
            this.address = InetAddress.getByName(nameServer);
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.buffer = this.message.getBytes();
        DatagramPacket packet = new DatagramPacket(this.buffer, this.buffer.length, this.address, this.port);

        try {
            this.socket.send(packet);
            packet = new DatagramPacket(buffer, buffer.length, this.address, this.port);
            this.socket.receive(packet);
            System.out.println("From server: " + new String(packet.getData(), 0, packet.getLength()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
