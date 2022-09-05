package mk.ukim.finki.os.labs.lab_4.zad1;

import java.io.*;
import java.net.Socket;

/**
 * Аце Ѓорѓиевски 201183
 */

public class SocketWorker extends Thread{

    private Socket socket;

    public SocketWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        PrintWriter writer = null;

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            System.out.printf("Connected: %s:%d\n",
                    socket.getInetAddress().getHostAddress(),
                    socket.getPort());

            String line = null;

            while((line = reader.readLine()) != null) {
                System.out.printf("Message from: %s:%d -- %s\n",
                        socket.getInetAddress().getHostAddress(),
                        socket.getPort(),
                        line);
                writer.write(line);
                writer.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(writer!=null) {
                writer.close();
            }

            if(socket!=null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
