package mk.ukim.finki.vezhbi.os.za_juni.aud_4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.DirectoryNotEmptyException;
import java.util.Arrays;
import java.util.Scanner;

public class TCPClient extends Thread{
    private String ipAddress;
    private int port;
    private String fileSourcePath;
    private DataOutputStream dos;
    private Socket socket = null;

    public TCPClient(String fileSourcePath,String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.fileSourcePath = fileSourcePath;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        PrintWriter pw = null;
        Scanner scanner = null;

        System.out.printf("Client %d started!\n",Thread.currentThread().getId());

        try {
            socket = new Socket(this.ipAddress, this.port);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            scanner = new Scanner(System.in);
            dos = new DataOutputStream(socket.getOutputStream());

            int number = this.findDocuments(new File(this.fileSourcePath));

            dos.writeInt(number);
            dos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br!=null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(dos!=null){
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(pw!=null)pw.close();
            if(scanner!=null)scanner.close();
            if(socket!=null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.printf("Client %d finished!\n",Thread.currentThread().getId());

    }

    private int findDocuments(File folder) throws FileNotFoundException {
        if(!folder.isDirectory()) throw new FileNotFoundException();

        return Arrays.stream(folder.listFiles())
                .filter(i -> i.getName().endsWith(".txt") && i.length() < 20*1024)
                .toList()
                .size();
    }
}
