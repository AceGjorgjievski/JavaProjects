package mk.ukim.finki.os.auds.aud_6_Networking.tcp.server;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HTTPWorkerThread extends Thread{

    private Socket socket;

    public HTTPWorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        BufferedReader reader = null;
        PrintWriter writer = null;
        StringBuilder sb = null;

        try {
            System.out.printf("Connected %s:%d\n",
                    socket.getInetAddress(),
                    socket.getPort());

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            sb = new StringBuilder();

            String line = null;


            while(!(line = reader.readLine()).isEmpty()) {
                sb.append(line).append("\n");
                System.out.println(line);
            }

            RequestProcessor requestProcessor = RequestProcessor.of(sb.toString());
            writer.write("HTTP 1.1/ 200 OK\n\n");
            if(requestProcessor.getCommand().equals("GET") && requestProcessor.getUri().equals("/time")) {
                writer.printf("<html><body><h1>%s</h1></body></html>",
                        LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
            } else {
                writer.printf("<html><body><h1>HELLO WORLD</h1></body></html>");
            }
//            writer.write(sb.toString());
            writer.flush();

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
            if(writer!= null) {
                writer.close();
            }

            if(socket!= null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
