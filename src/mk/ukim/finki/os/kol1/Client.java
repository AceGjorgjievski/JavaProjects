//package mk.ukim.finki.os.kol1;
//
//
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
//import java.net.Socket;
//import java.net.UnknownHostException;
//import java.util.Scanner;
//
///**
// * Аце Ѓорѓиевски 201183
// */
//
//public class Client extends Thread{
//
//    private int port = Connection.PORT;
//    private String ipAddress = Connection.IP_ADDRESS;
//    private Scanner scanner;
//    private Socket socket;
//    private PrintWriter writer;
//
//    public Client(int port,String ipAddress) {
//        this.port = port;
//        this.ipAddress = ipAddress;
//    }
//
//    @Override
//    public void run() {
//
//        try {
//            socket = new Socket(this.ipAddress,this.port);
//            scanner = new Scanner(System.in);
//            writer = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
//
//
//            System.out.println("Writer messages ... ");
//            String line = null;
//            while(true) {
//                line = scanner.nextLine();
//                writer.write(line);
//                writer.flush();
//            }
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if(socket != null) {
//                try {
//                    socket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            if(writer != null) {
//                writer.close();
//            }
//
//            if(scanner != null) {
//                scanner.close();
//            }
//
//        }
//
//    }
//
//
//    public static void main(String[] args) {
//        Client client = new Client(Connection.PORT, Connection.IP_ADDRESS);
//        client.start();
//    }
//}
