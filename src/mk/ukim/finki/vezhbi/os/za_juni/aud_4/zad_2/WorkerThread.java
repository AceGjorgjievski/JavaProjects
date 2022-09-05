package mk.ukim.finki.vezhbi.os.za_juni.aud_4.zad_2;

import java.io.*;
import java.net.Socket;

public class WorkerThread extends Thread{
    private Socket socket;

    public WorkerThread(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        BufferedReader br = null;
        PrintWriter pw = null;

        System.out.printf("Connected: %s:%d\n",
                this.socket.getInetAddress(),
                this.socket.getPort());


        try {
            br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            pw = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));

            String line = null;

            while(!(line=br.readLine()).isEmpty()) {
                System.out.printf("Message from: %s:%d -- %s\n",
                        socket.getInetAddress(),
                        socket.getPort(),
                        line);

                pw.println(line);
                pw.flush();
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

            if(pw!=null)pw.close();
        }
    }
}
