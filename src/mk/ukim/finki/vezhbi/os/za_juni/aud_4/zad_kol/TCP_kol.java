package mk.ukim.finki.vezhbi.os.za_juni.aud_4.zad_kol;

import java.io.*;
import java.net.Socket;

public class TCP_kol {
    public static String IP_ADDRESS = "localhost";
    public static int PORT = 8891;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(IP_ADDRESS,PORT);
        WThread thread = new WThread(socket,"hello:201183");
        thread.start();
        ClientTCP clientTCP = new ClientTCP(socket);
        clientTCP.start();
    }
}


class ClientTCP extends Thread {
    private Socket socket;

    public ClientTCP(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        PrintWriter pw = null;

        try {
            br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            pw = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()),true);
            String line = null;

            while (true) {
                line = br.readLine();

                if(line.isEmpty() || line == null) continue;

                if(line.equals("201183:hello")) {
                    new WThread(socket,"201183:receive").start();
                }

                if(line.startsWith("201183:send")) {
                    String [] parts = line.split(":");
                    File f = new File(parts[2]);

                    FileWriter fw = new FileWriter(f);
                    while (true) {
                        line = br.readLine();

                        if(line.equals("201183:over")) {
                            break;
                        }

                        fw.write(line);
                        fw.write("\n");
                        fw.flush();
                    }
                    new WThread(socket, "201183:size:"+f.length()).start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



class WThread extends Thread {
    private Socket socket;
    private String message;

    public WThread(Socket socket, String message) {
        this.socket = socket;
        this.message = message;
    }

    @Override
    public void run() {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            synchronized (WThread.class) {
                pw.println(this.message);
                pw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(pw!=null)pw.close();
        }
    }
}
