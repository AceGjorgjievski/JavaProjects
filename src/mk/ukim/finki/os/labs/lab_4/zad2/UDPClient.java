package mk.ukim.finki.os.labs.lab_4.zad2;

import java.io.IOException;
import java.net.*;

/**
 * Аце Ѓорѓиевски 201183
 */

public class UDPClient extends Thread{

    private String nameServer;
    private int port;

    private DatagramSocket socket;
    private InetAddress address;
    private String message;
    private byte [] buffer;

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
            System.out.println("From server: " +  new String(packet.getData(), 0, packet.getLength()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(socket!= null) {
                socket.close();
            }
        }
    }

    public static void main(String[] args) {
        UDPClient client = new UDPClient("194.149.135.49",9753, "201183");
        client.start();
    }
}
