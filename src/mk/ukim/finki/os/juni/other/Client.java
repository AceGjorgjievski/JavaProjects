package mk.ukim.finki.os.juni.other;

import java.io.*;
import java.net.Socket;

public class Client extends Thread{
    private String address;
    private int port;
    private StringBuilder sb;

    public Client(String address, int port) {
        this.address = address;
        this.port = port;
        sb = new StringBuilder();
        sb.append("201183:attach:filename.txt\n").append("hsidkbvzl\n").append("dssdbvzx\n")
                .append("sdzbvfz\n").append("567eydrz\n").append("zfdbaw4gvfzx\n")
                .append("srdfvaw4ersd\n").append("wea4egrds\n").append("34wefsdvfbdhtrs\n")
                .append("sfvszbbrsefd\n").append("srea5rhzdf\n").append("201183:over");
    }

    @Override
    public void run() {
        PrintWriter pw = null;
        Socket socket = null;
        BufferedReader br = null;


        try {
            socket = new Socket(this.address, this.port);
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            int size = sb.length();
            while (true) {
                //1vo baranje
                pw.println("hello:201183");
                pw.flush();
                System.out.println(br.readLine());


                //2ro baranje
                pw.println(sb.toString());
                pw.flush();
                System.out.println(br.readLine());


                //3to baranje
                pw.println("201183:fileSize:"+size);
                pw.flush();
                System.out.println(br.readLine());

                break;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *Аце Ѓорѓиевски 201183
     *
     */

    public static void main(String[] args) {

        String address = "194.149.135.49";
        int port = 9753;
        Client client = new Client(address,port);
        client.start();
    }
}
