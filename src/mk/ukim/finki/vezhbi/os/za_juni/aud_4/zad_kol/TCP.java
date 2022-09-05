package mk.ukim.finki.vezhbi.os.za_juni.aud_4.zad_kol;

import java.io.*;
import java.net.Socket;

public class TCP {
    public static String hostName = "194.149.135.49";
    public static int port = 9357;
    public static void main(String[] args) {
        try {
            Socket socket = new Socket(hostName, port);
            Client client = new Client(socket);
            WorkerThread wt = new WorkerThread(socket,"hello:201183");

            wt.start();
            client.start();

            client.join();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class Client extends Thread {
    private Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            String line = null;
            while (true) {
                line = br.readLine();

                if(line.isEmpty() || line == null) continue;

                System.out.println("Received: "+line);

                if(line.equals("201183:hello")) {
                    new WorkerThread(this.socket,"201183:receive\n").start();
                }

                if(line.startsWith("201183:send")) {
                    String [] parts = line.split(":");
                    File f = new File(parts[2]);

                    if(!f.exists()) f.createNewFile();
                    FileWriter fw = null;
                    try {
                        String input = null;
                        fw = new FileWriter(f);
                        while(true) {
                            input = br.readLine();

                            if (input.equals("201183:over")) break;
                            fw.write(input);
                            fw.flush();
                        }
                    }finally {

                    }
                    new WorkerThread(this.socket, "201183:size:"+f.length()).start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class WorkerThread extends Thread{
    private Socket socket;
    private String message;

    public WorkerThread(Socket socket, String message) {
        this.socket = socket;
        this.message = message;
    }

    @Override
    public void run() {
        PrintWriter pw = null;

        try{
            pw = new PrintWriter(socket.getOutputStream(),true);
            synchronized (WorkerThread.class) {
                pw.println(message);
                pw.flush();
            }
            System.out.println("Sent: "+message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
