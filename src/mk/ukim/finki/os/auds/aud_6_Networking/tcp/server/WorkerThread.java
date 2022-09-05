package mk.ukim.finki.os.auds.aud_6_Networking.tcp.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WorkerThread extends Thread {


    private Socket socket;

    public WorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        BufferedReader reader = null;
        PrintWriter writer = null;

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //chitame podatoci prateni od dalechen pc ^ (od klient -> srw)
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            //prakjame podatoci do dalechen pc ^ (od srw -> klient)

            System.out.printf("Connected: %s:%d\n",
                    socket.getInetAddress(),
                    socket.getPort());
            //bidejkji sme na srw, i barame koj ni e ip addr na host,
            // onoj koj shto ni go prakja podatokot
            String line = null;

            while((line = reader.readLine()) != null) {//mozhe i so -> !.isEmpty()
                System.out.printf("Message from %s:%d --- %s\n",
                        socket.getInetAddress(),
                        socket.getPort(),
                        line);
                writer.write(String.format("Echo: %s\n", line));
                writer.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (writer != null) writer.close();

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}