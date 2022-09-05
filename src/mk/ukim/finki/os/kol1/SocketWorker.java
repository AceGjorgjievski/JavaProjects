//package mk.ukim.finki.os.kol1;
//
//import java.io.*;
//import java.net.Socket;
//
///**
// * Аце Ѓорѓиевски 201183
// */
//
//public class SocketWorker extends Thread{
//
//    private Socket socket;
//    private BufferedReader reader;
//    private PrintWriter writer;
//
//    public SocketWorker(Socket socket) {
//        this.socket = socket;
//    }
//
//
//    @Override
//    public void run() {
//
//        try {
//            reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
//            writer = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
//            socket = new Socket();
//
//            String line = null;
//
//            System.out.printf("Connected: %s:%d\n",
//                    this.socket.getInetAddress().getHostAddress(),
//                    this.socket.getPort());
//
//
//            while(!(line = reader.readLine()).isEmpty()) {
//                System.out.printf("%s:hello\n",line);
//                writer.println(line);
//                writer.flush();
//            }
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if(reader!= null) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            if(writer!= null) {
//                writer.close();
//            }
//
//            if(socket!= null) {
//                try {
//                    socket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//
//    }
//}
