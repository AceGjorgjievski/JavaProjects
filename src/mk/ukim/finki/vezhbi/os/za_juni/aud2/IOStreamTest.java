package mk.ukim.finki.vezhbi.os.za_juni.aud2;


import java.io.*;

interface IIOStream {
    void copyFileByteByByte(File from, File to) throws IOException;

    void printContentOfTxtFile(File f, PrintStream printer) throws IOException;

    void readContentFromStdInput(OutputStream to) throws IOException;

    void writeToTextFile(File to, String text, Boolean append) throws IOException;

    void memoryUnsafeTextFileCopy(File from, File to) throws IOException;

    void memorySafeTextFileCopy(File from, File to) throws IOException;

    void readFileWithLineNumber(File from, OutputStream outputStream) throws IOException;

    void writeBinaryDataToBFile(File to, Object... objects) throws IOException ;

    void readBinaryDataFromBFile(File from, Object... objects) throws IOException;

    void writeToRandomAccessFile(File from) throws IOException;

    void readFromRandomAccessFile(File from, PrintStream out) throws IOException;
}

class IOStream implements IIOStream {

    @Override
    public void copyFileByteByByte(File from, File to) throws IOException {

        InputStream is = null;
        OutputStream os = null;

        try {
            is = new FileInputStream(from);
            os = new FileOutputStream(to);
            int num = -1;

            while((num = is.read()) != -1) {
                os.write(num);
                os.flush();
            }
        } catch (IOException e) {
            System.out.println("IOException - copyFileByteByByte");
        } finally {
            if(is != null) {
                is.close();
            }
            if(os != null) {
                os.flush();
                os.close();
            }
        }
    }

    @Override
    public void printContentOfTxtFile(File f, PrintStream printer) throws IOException {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String line = null;

            while((line = br.readLine()) != null) {
                printer.println(line);
                printer.flush();
            }
        } catch (IOException e) {
            System.out.println("IOException - printContentOfTxtFile");
        } finally {
            if(br!= null) br.close();
            if(printer != null) printer.close();
        }
    }

    @Override
    public void readContentFromStdInput(OutputStream to) throws IOException {
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            bw = new BufferedWriter(new OutputStreamWriter(to));
            String line = null;

            while((line = br.readLine()) != null) {
                bw.write(line);
                bw.flush();
            }
        } catch (IOException e) {
            System.out.println("IOException - readContentFromStdInput");
        } finally {
            if(br!= null) br.close();
            if(bw != null) bw.close();
        }

    }

    @Override
    public void writeToTextFile(File to, String text, Boolean append) throws IOException {
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(to, append));
            bw.write(text);
            bw.flush();
        } catch (IOException e) {
            System.out.println("IOException - writeToTextFile");
        } finally {
            if(bw != null) bw.close();
        }
    }

    @Override
    public void memoryUnsafeTextFileCopy(File from, File to) throws IOException {
        BufferedReader br = null;
        BufferedWriter bw = null;
        StringBuilder sb = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(from)));
            bw = new BufferedWriter(new FileWriter(to));
            String line = null;

            while((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            System.out.println("IOException - memoryUnsafeTextFileCopy");
        } finally {
            if(br!=null) br.close();
            if(bw!=null)bw.close();
        }
    }

    @Override
    public void memorySafeTextFileCopy(File from, File to) throws IOException {
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            br = new BufferedReader(new FileReader(from));
            bw = new BufferedWriter(new FileWriter(to));
            String line = null;

            while((line = br.readLine()) != null) {
                bw.write(line);
                bw.flush();
            }
        } catch (IOException e) {
            System.out.println("IOException - memorySafeTextFileCopy");
        } finally {
            if(br != null) br.close();
            if(bw != null) bw.close();
        }
    }

    @Override
    public void readFileWithLineNumber(File from, OutputStream outputStream) throws IOException {
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            br = new BufferedReader(new FileReader(from));
            bw = new BufferedWriter(new OutputStreamWriter(outputStream));
            String line = null;
            int lineCounter = 1;

            while((line = br.readLine()) != null) {
                bw.write(String.format("%d: %s",lineCounter++,line));
                bw.flush();
            }
        } catch (IOException e) {
            System.out.println("IOException - readFileWithLineNumber");
        } finally {
            if(br != null) br.close();
            if(bw != null) bw.close();
        }
    }

    @Override
    public void writeBinaryDataToBFile(File to, Object... objects) throws IOException {
        DataOutputStream dos = null;

        try {
            dos = new DataOutputStream(new FileOutputStream(to));

            for(Object o : objects) {
                if(o instanceof Integer) {
                    dos.writeInt((int)o);
                } else if(o instanceof Double) {
                    dos.writeDouble((double)o);
                } else if(o instanceof Float) {
                    dos.writeFloat((float)o);
                } else if(o instanceof String) {
                    dos.writeUTF(o.toString());
                } else if(o instanceof Character) {
                    dos.writeChar((char)o);
                }
                dos.flush();
            }
        } catch (IOException e) {
            System.out.println("IOException - writeBinaryDataToBFile");
        } finally {
            if(dos != null) dos.close();
        }
    }

    @Override
    public void readBinaryDataFromBFile(File from, Object... objects) throws IOException {
        DataInputStream dis = null;

        try {
            dis = new DataInputStream(new FileInputStream(from));
            for(Object o : objects) {
                if(o instanceof Integer) {
                    o = dis.readInt();
                } else if(o instanceof Double) {
                    o = dis.readDouble();
                } else if(o instanceof Float) {
                    o = dis.readFloat();
                } else if(o instanceof String) {
                    o = dis.readUTF();
                } else if(o instanceof Character) {
                    o = dis.readChar();
                }
                System.out.println(o);
            }
        } catch (IOException e) {
            System.out.println("IOException - readBinaryDataFromBFile");
        } finally {
            if(dis != null) dis.close();
        }
    }

    @Override
    public void writeToRandomAccessFile(File from) throws IOException {
        RandomAccessFile raf = null;

        try {
            raf = new RandomAccessFile(from,"rw");
            for(int i=1; i<=10; i++) {
                raf.writeUTF("hello: "+ i + "\n");
            }
            raf.writeUTF("END");
        } catch (IOException e) {
            System.out.println("IOException - writeToRandomAccessFile");
        } finally {
            if(raf != null) raf.close();
        }
    }

    @Override
    public void readFromRandomAccessFile(File from, PrintStream out) throws IOException {
        RandomAccessFile raf = null;

        try {
            raf = new RandomAccessFile(from,"r");
            for(int i=1; i<=10; i++) {
                out.println(raf.readUTF());
                out.flush();
            }
        } catch (IOException e) {
            System.out.println("IOException - readFromRandomAccessFile");
        } finally {
            if(raf != null) raf.close();
            if(out != null) out.close();
        }
    }

    public void readFromFileToSwap(File from, OutputStream out) throws IOException {
        BufferedReader br = null;
        RandomAccessFile raf = null;

        //Not corecct ////

        try {
            br = new BufferedReader(new FileReader(from));
            raf = new RandomAccessFile(from,"rw");
            int symbol, lineNum = 0;

            while((symbol = raf.read()) != -1) {
                if((char) symbol == '\n') {
                    lineNum++;
                }

                if(lineNum == 2 && (char)symbol != '\n') {
                    System.out.println((char)symbol);
                }
            }

            raf.writeChar(symbol);
            String reverse = new StringBuilder(raf.readLine()).reverse().toString();
            raf.writeBytes(reverse);
            br.close();
            raf.close();
        } catch (IOException e) {
            System.out.println("IOException - readFromFileToSwap");
        } finally {
            if(br!=null)br.close();
            if(raf!=null)raf.close();
        }
    }
}

public class IOStreamTest {

    public static void main(String[] args) throws IOException {
        String fileSource = "C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\vezhbi\\os\\za_juni\\aud2\\source.txt";
        String fileDestination = "C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\vezhbi\\os\\za_juni\\aud2\\destination.txt";

        IOStream ioStream = new IOStream();
//        ioStream.copyFileByteByByte(new File(fileSource), new File(fileDestination));
        //ioStream.writeToRandomAccessFile(new File(fileSource));
        //ioStream.readFromRandomAccessFile(new File(fileSource),System.out);
        ioStream.readFromFileToSwap(new File(fileSource),System.out);
    }

}
