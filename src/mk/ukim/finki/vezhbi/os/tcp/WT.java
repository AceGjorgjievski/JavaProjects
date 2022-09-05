package mk.ukim.finki.vezhbi.os.tcp;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class WT extends Thread {

    private Socket socket;


    public WT(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        BufferedReader reader = null;
        PrintWriter writer = null;
        Scanner scanner = null;

        try {
            System.out.printf("Connected %s:%d\n",
                    socket.getInetAddress(),
                    socket.getPort());

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            scanner = new Scanner(System.in);

            String line = null;

            while (!(line = reader.readLine()).isEmpty()) {
                System.out.printf("Message from Client %s:%d --- %s\n",
                        socket.getInetAddress(),
                        socket.getPort(),
                        line);


                writer.println(line);
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

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (writer != null) writer.close();
        }
    }
}
