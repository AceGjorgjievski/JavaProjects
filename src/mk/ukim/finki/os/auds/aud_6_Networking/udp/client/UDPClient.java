package mk.ukim.finki.os.auds.aud_6_Networking.udp.client;

import java.io.IOException;
import java.net.*;

public class UDPClient extends Thread{

    private String nameServer;//ime na server
    private int port;//i portata na koja shto raboti

    private DatagramSocket socket;//<-datagram soket za da se konektira do samiot srw
    private InetAddress address;//ip-addr na srw za da se prati datagram-soketot
    private String message;//poraka koj shto treba da se prati
    private byte[]buffer; //i buffer-ot shto kje go iskoristime vo datagram paketot


    public UDPClient(String nameServer, int serverPort, String message){
        this.nameServer = nameServer;
        this.port = serverPort;
        this.message = message;

        try {
            socket = new DatagramSocket();
            this.address = InetAddress.getByName(nameServer);//od server-name -> ip-addresa
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        buffer = this.message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length,this.address,this.port);

        try {
            socket.send(packet);
            packet = new DatagramPacket(buffer, buffer.length,this.address,this.port);
            socket.receive(packet);
            System.out.println(new String(packet.getData(),0, packet.getLength()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        UDPClient client = new UDPClient("localhost",4501,"HelloWorld");
        client.start();
    }
}
