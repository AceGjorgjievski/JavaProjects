package mk.ukim.finki.os.auds.aud_6_Networking.udp.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer extends Thread{

    private DatagramSocket socket;//udp komunikacija [UDP srw slusha na nekoj datagram soket]
    //a toj soket ni generira soodvetni paketi[28 line]
    private final byte [] buffer = new byte[256];//prakjanje i primanje na data

    public UDPServer(int port) {
        try {
            this.socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        //za daden datagram paket mora da specificirame koj buff kje go koristi i negovata golemina
        //mora da specificirame koj buffer da go koristime za chitanje, ispishuvanje
        //na data koi shto se razmenuvaat pomegju samiot klient-srw
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        //razmena na datagram pri udp-komunikacija => zatoa datagram packet

        while(true) {
            try {
                socket.receive(packet);//paketite soketot gi prema preku receive
                //site shto kje gi primi kje gi smesti vo ovoj datagram paket

                //za da gi ischitame podatocite koi shto se dobieni o 0-ot byte do kraj, gi smestuvame vo string
                String received = new String(packet.getData(),0,packet.getLength());
                System.out.println("Received: " +received);//pechatime na out

                //mora da ja znaeme ip-adrr na samiot udp-klient i portata za da
                //podatocite bidat nazad vrateni
                InetAddress address = packet.getAddress();
                int port = packet.getPort();


                packet = new DatagramPacket(buffer, buffer.length,address,port);
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        UDPServer server = new UDPServer(4501);
        server.start();
    }
}
