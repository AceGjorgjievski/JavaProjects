package mk.ukim.finki.vezhbi.os.za_juni.aud_4;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WThread extends Thread{
    private Socket socket;
    private DataInputStream dis;
    private BufferedWriter bw;

    public WThread(Socket socket, BufferedWriter bw) {
        this.socket = socket;
        this.bw = bw;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        PrintWriter pw = null;

        System.out.printf("Connected: %s:%d\n",socket.getInetAddress(),socket.getPort());

        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            dis = new DataInputStream(socket.getInputStream());

            int numberOfFile = dis.readInt();
            synchronized (WThread.class) {
                bw.write(String.format("%s %s %d\n",socket.getInetAddress(),socket.getPort(),numberOfFile));
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br!= null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(pw!=null) {
                pw.close();
            }
        }
    }
}
