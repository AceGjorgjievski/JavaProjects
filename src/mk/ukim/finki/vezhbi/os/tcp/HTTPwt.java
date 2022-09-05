package mk.ukim.finki.vezhbi.os.tcp;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HTTPwt extends Thread{

    private Socket socket;

    public HTTPwt(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        BufferedReader reader = null;
        PrintWriter writer = null;
        StringBuilder builder = null;

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            builder = new StringBuilder();

            String line = null;
            while(!(line = reader.readLine()).isEmpty()) {
                builder.append(line).append("\n");
            }
            RP rp = RP.of(builder.toString());
            writer.printf("<html><body><h1>Status 200 - OK</h1></body></html>");

            if(rp.getCommand().equals("GET") && rp.getUri().equals("/time")) {
                writer.printf("<html> <head><meta http-equiv=\"refresh\" content = \"1\"> <meta name=\"viewport\" content=\"device-width\",initial-scale=1.0> </head> <body> <h1> %s </h1></body> </html>",
                        LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
            } else {
                writer.printf("<html> <body> <h1> HELLO WORLD </h1></body> </html>",
                        LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
