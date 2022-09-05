package mk.ukim.finki.os.auds.aud_2_IO;


import java.io.*;

public class IOStreamManagerImpl implements IOStreamManager {

    @Override
    public void copyFileByteByByte(File from, File to) throws IOException {
        InputStream is = null;
        OutputStream os = null;

        try {
            is = new FileInputStream(from);
            os = new FileOutputStream(to);
            int num = -1;

            while ((num = is.read()) != -1) {
                os.write(num);
                os.flush();
            }
        } catch (IOException e) {
            System.out.println("IOException - copyFileByteByByte");
        } finally {
            if (is != null) is.close();
            if (os != null) os.close();
        }
    }

    @Override
    public void printContentOfTxtFile(File f, PrintStream printer) throws IOException {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(f));
            String line = null;

            while ((line = br.readLine()) != null) {
                printer.println(line);
                printer.flush();
            }
        } catch (IOException e) {
            System.out.println("IOException - printContentOfTxtFile");
        } finally {
            if (br != null) br.close();
            if (printer != null) printer.close();
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

            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.flush();
            }
        } catch (IOException e) {
            System.out.println("IOException - readContentFromStdInput");
        } finally {
            if (br != null) br.close();
            if (bw != null) bw.close();
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
            if (bw != null) bw.close();
        }
    }

    @Override
    public void memoryUnsafeTextFileCopy(File from, File to) throws IOException {
        BufferedReader br = null;
        BufferedWriter bw = null;
        StringBuilder sb = null;

        try {
            br = new BufferedReader(new FileReader(from));
            bw = new BufferedWriter(new FileWriter(to));
            sb = new StringBuilder();
            String line = null;

            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            System.out.println("IOException - memoryUnsafeTextFileCopy");
        } finally {
            if (br != null) br.close();
            if (bw != null) bw.close();
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
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.flush();
            }
        } catch (IOException e) {
            System.out.println("IOException - memorySafeTextFileCopy");
        } finally {
            if (br != null) br.close();
            if (bw != null) bw.close();
        }
    }

    @Override
    public void readFileWithLineNumber(File from, OutputStream outputStream) throws IOException {
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            br = new BufferedReader(new FileReader(from));
            bw = new BufferedWriter(new OutputStreamWriter(outputStream));

            int lineCounter = 1;

            String line = null;
            while ((line = br.readLine()) != null) {
                bw.write(String.format("%d: %s", lineCounter++, line));
                bw.flush();
            }
        } catch (IOException e) {
            System.out.println("IOException - readFileWithLineNumber");
        } finally {
            if (br != null) br.close();
            if (bw != null) bw.close();
        }
    }

    @Override
    public void writeBinaryDataToBFile(File to, Object... objects) throws IOException {
        DataOutputStream dataOutputStream = null;

        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(to));

            for (Object o : objects) {
                if (o instanceof Integer) {
                    dataOutputStream.writeInt((int) o);
                } else if (o instanceof Double) {
                    dataOutputStream.writeDouble((double) o);
                } else if (o instanceof Character) {
                    dataOutputStream.writeChar((char) o);
                } else if (o instanceof String) {
                    dataOutputStream.writeUTF((String) o);
                } else if (o instanceof Float) {
                    dataOutputStream.writeFloat((float) o);
                }
            }
            dataOutputStream.flush();
        } catch (IOException e) {
            System.out.println("IOException - writeBinaryDataToBFile");
        } finally {
            if (dataOutputStream != null) dataOutputStream.close();
        }
    }

    @Override
    public void readBinaryDataFromBFile(File from, Object... objects) throws IOException {
        DataInputStream dataInputStream = null;

        try {
            dataInputStream = new DataInputStream(new FileInputStream(from));
            for (Object o : objects) {
                if(o instanceof Integer) {
                    o = dataInputStream.readInt();
                } else if(o instanceof Double) {
                    o = dataInputStream.readDouble();
                } else if(o instanceof Character) {
                    o = dataInputStream.readChar();
                } else if(o instanceof String) {
                    o = dataInputStream.readUTF();
                } else if(o instanceof Float) {
                    o = dataInputStream.readFloat();
                }
                System.out.println(o);
            }
        } catch (IOException e) {
            System.out.println("IOException - readBinaryDataFromBFile");
        } finally {
            if(dataInputStream != null) dataInputStream.close();
        }
    }

    @Override
    public void writeToRandomAccessFile(File from) throws IOException {
        RandomAccessFile randomAccessFile = null;


        try {
            randomAccessFile = new RandomAccessFile(from,"rw");
            for(int i=1; i<=10; i++) {
                randomAccessFile.writeDouble(i*5+1%9+4);
            }
            randomAccessFile.writeUTF("END");
        } catch (IOException e) {
            System.out.println("IOException - writeToRandomAccessFile");
        } finally {
            if(randomAccessFile != null) randomAccessFile.close();
        }
    }

    @Override
    public void readFromRandomAccessFile(File from, PrintStream out) throws IOException {
        RandomAccessFile randomAccessFile = null;

        try {
            randomAccessFile = new RandomAccessFile(from,"r");
            for(int i=1; i<=10; i++) {
                out.println(randomAccessFile.readDouble());
            }
            out.println(randomAccessFile.readUTF());
            out.flush();
        } catch (IOException e) {
            System.out.println("IOException - readFromRandomAccessFile");
        } finally {
            if(randomAccessFile != null) randomAccessFile.close();
        }
    }
}
