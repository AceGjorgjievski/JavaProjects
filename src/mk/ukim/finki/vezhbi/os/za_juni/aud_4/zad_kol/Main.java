package mk.ukim.finki.vezhbi.os.za_juni.aud_4.zad_kol;

import mk.ukim.finki.vezhbi.os.za_juni.aud_4.TCPClient;
import mk.ukim.finki.vezhbi.os.za_juni.aud_4.TCPServer;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class Main {
    public static final String FILE_SOURCE = "C:\\Users\\Ace\\Desktop\\tcp_exe";
    public static final String FILE_DEST = "C:\\Users\\Ace\\Desktop\\tcp_exe\\results.txt";
    public static final String IP_ADDRESS = "localhost";
    public static final int PORT = 9811;

    public static void main(String[] args) throws IOException {
        TCPServer server = new TCPServer(PORT,new File(FILE_DEST));
        server.start();
        TCPClient client1 = new TCPClient(FILE_SOURCE,IP_ADDRESS,PORT);
        TCPClient client2 = new TCPClient(FILE_SOURCE,IP_ADDRESS,PORT);
        client1.start();
        client2.start();
    }
}
