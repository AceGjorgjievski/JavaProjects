package mk.ukim.finki.os.auds.aud_2_IO;

import java.io.File;
import java.io.IOException;

public class IOStreamTest {

    public static void main(String[] args) throws IOException {
        String fileSource = "C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\os\\aud_2\\source.txt";
        String fileDestination = "C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\os\\aud_2\\destination.txt";

        IOStreamManager ioStreamManager = new IOStreamManagerImpl();
        //ioStreamManager.copyFileByteByByte(new File(fileSource), new File(fileDestination));
        ioStreamManager.writeToRandomAccessFile(new File(fileSource));
    }
}
