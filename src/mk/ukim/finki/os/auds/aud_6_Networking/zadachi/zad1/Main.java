package mk.ukim.finki.os.auds.aud_6_Networking.zadachi.zad1;

public class Main {

    private final static String IP_ADDRESS = "localhost";
    private final static int PORT = 4450;


    public static void main(String[] args) {
        Server server = new Server(PORT);

        server.start();

        Client client1 = new Client(IP_ADDRESS,PORT);
        Client client2 = new Client(IP_ADDRESS,PORT);

        client1.start();
        client2.start();
    }
}
