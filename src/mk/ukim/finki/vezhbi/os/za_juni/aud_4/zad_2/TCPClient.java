package mk.ukim.finki.vezhbi.os.za_juni.aud_4.zad_2;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient extends Thread{
    private String hostName;
    private int port;

    public TCPClient(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        PrintWriter pw = null;
        Scanner sc = null;
        Socket socket = null;

        try {
            socket = new Socket(this.hostName, this.port);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            sc = new Scanner(System.in);

            String line = null;

            while(true) {
                line = sc.nextLine();
                pw.println(line);
                pw.flush();

                System.out.println("Message from server: " + br.readLine());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TCPClient client = new TCPClient("194.149.135.49", 9357);
        client.start();
    }
}
