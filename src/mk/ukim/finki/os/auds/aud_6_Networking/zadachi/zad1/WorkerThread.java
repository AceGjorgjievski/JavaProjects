package mk.ukim.finki.os.auds.aud_6_Networking.zadachi.zad1;

import java.io.*;
import java.net.Socket;

//local port e na srw
//nas ni treba na klientot -> getPort()

public class WorkerThread extends Thread{

    private Socket socket;
    private String filePath;
    private DataInputStream dis;

    public WorkerThread(Socket socket, String filePath) throws IOException {
        this.socket = socket;
        this.filePath = filePath;
        dis = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(this.filePath,true));
            int numFiles = dis.readInt();

            synchronized (WorkerThread.class) {
                writer.write(String.format("%s %d %d\n",
                        socket.getInetAddress().getHostAddress(),
                        socket.getPort(),
                        numFiles));
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer!= null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
