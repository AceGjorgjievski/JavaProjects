package mk.ukim.finki.os.auds.aud_6_Networking.zadachi.zad1;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.DirectoryNotEmptyException;
import java.util.Arrays;

public class Client extends Thread{

    private final static String FOLDER_SOURCE_PATH = "C:\\Users\\Ace\\Desktop\\txtDocuments";

    private String hostIpAddress;
    private int hostPort;
    private Socket socket = null;
    DataOutputStream dos = null;


    public Client(String hostIpAddress, int hostPort) {
        this.hostIpAddress = hostIpAddress;
        this.hostPort = hostPort;
    }


    @Override
    public void run() {

        try {
            System.out.printf("Client %d started\n",Thread.currentThread().getId());

            socket = new Socket(this.hostIpAddress,this.hostPort);
            int numberFiles = this.getNumberOfFiles(FOLDER_SOURCE_PATH);

            dos = new DataOutputStream(socket.getOutputStream());
            dos.writeInt(numberFiles);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(socket!=null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(dos!=null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.printf("Client %d is finished.\n",Thread.currentThread().getId());
    }

    private int getNumberOfFiles(String path) throws FileNotFoundException {
        File f = new File(path);
        if(!f.isDirectory() || !f.exists()) {
            throw new FileNotFoundException();
        }

        return (int)Arrays.stream(f.listFiles())
                .filter(i -> this.isValidFile(i))
                .count();
    }

    private boolean isValidFile(File f) {
        return f.getName().endsWith(".txt") && f.length() < 20*1024;
    }
}
