package mk.ukim.finki.os.juni;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

    public final static String IP_ADDRESS = "194.149.135.49";
    public final static int PORT = 9753;

    public static void main(String[] args) {
        try {
            WorkerThread workerThread = new WorkerThread(new Socket(IP_ADDRESS,PORT),"hello:201183");
            Client client = new Client(IP_ADDRESS, PORT);
            client.start();
            workerThread.start();
            //workerThread.join();

            //client.join();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


class Client extends Thread {
    private String hostAddress;
    private int port;

    public Client(String hostAddress, int port) {
        this.hostAddress = hostAddress;
        this.port = port;
    }

    @Override
    public void run() {
        Socket socket = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        //System.out.println("Client connecting...");

        try {
            socket = new Socket(this.hostAddress, this.port);
            //System.out.printf("Client connected %s:%s\n",socket.getInetAddress(),socket.getPort());
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            String line = null;
            while(true) {
                line = br.readLine();
                if(line==null) continue;

                if(line.startsWith("201183")) {
                    System.out.println(line);
                }
                System.out.println("ok");

//                if(line.equals("201183:hello")) {
//                    new WorkerThread(socket,"201183:send:111111").start();
//                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(socket!= null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(br!=null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



class WorkerThread extends Thread {
    private Socket socket;
    private String message;

    public WorkerThread(Socket socket, String message) {
        this.socket = socket;
        this.message = message;
    }

    @Override
    public void run() {
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
           // synchronized (WorkerThread.class) {
                pw.println(this.message);
                pw.flush();
            //}
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(pw!=null)pw.close();
        }
    }
}
