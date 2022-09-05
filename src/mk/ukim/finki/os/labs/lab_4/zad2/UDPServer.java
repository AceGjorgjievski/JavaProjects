package mk.ukim.finki.os.labs.lab_4.zad2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Аце Ѓорѓиевски 201183
 */

public class UDPServer extends Thread{

    private DatagramSocket socket;
    private byte [] buffer = new byte[256];

    public UDPServer(int port) {
        try {
            this.socket = new DatagramSocket(port);
        } catch (SocketException e) {
            System.err.printf("The port %d is busy!\n",port);
            return;
        }
    }

    @Override
    public void run() {
        DatagramPacket packet = new DatagramPacket(this.buffer, this.buffer.length);

        try {
            this.socket.receive(packet);
            String received = new String(packet.getData(),0, packet.getLength());

            //String respondMessage = "Received: " + received;
            System.out.println("Received: " + received);
            //this.buffer = respondMessage.getBytes();
            InetAddress address = packet.getAddress();
            int port = packet.getPort();

            packet = new DatagramPacket(this.buffer,this.buffer.length,address,port);
            this.socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(socket != null) {
                socket.close();
            }
        }
    }

    public static void main(String[] args) {
        UDPServer server = new UDPServer(9753);
        server.start();
    }

}
