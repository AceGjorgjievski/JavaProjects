package mk.ukim.finki.os.auds.aud_1_FileManager;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Date;

public class FileManagerImplementation  implements FileManager{


    @Override
    public void createNewFile(String file) throws IOException, FileExistsException {
        File f = new File(file);
        if(f.exists()) throw new FileExistsException();
        f.createNewFile();
    }

    @Override
    public File[] getFilesInFolder(File file) throws FileNotFoundException {
        if(!file.exists()) throw new FileNotFoundException();
        if(!file.isDirectory()) throw new FileNotFoundException();
        return file.listFiles();
    }

    @Override
    public void printFileNames(File file, PrintStream writer) throws FileNotFoundException {
        if(!file.exists()) throw new FileNotFoundException();
        Arrays.stream(getFilesInFolder(file))
                .forEach(i -> writer.println(i.getName()));
    }

    @Override
    public String getAbsolutePath(String relativePath) throws FileNotFoundException {
        File f = new File(relativePath);
        if(!f.exists()) throw new FileNotFoundException();
        return f.getAbsolutePath();
    }

    @Override
    public long getFileSize(String file) throws FileNotFoundException {
        File f = new File(file);
        if(!f.exists()) throw new FileNotFoundException();
        return f.length();
    }

    @Override
    public void printFilePermissions(File f, PrintStream writer) throws FileNotFoundException {
        if(!f.exists()) throw new FileNotFoundException();
        Arrays.stream(getFilesInFolder(f))
                .forEach(i -> {
                    writer.printf("Read: %x, Write: %x, Execute: %x",
                            i.canRead(),
                            i.canWrite(),
                            i.canExecute());
                });
    }

    @Override
    public void createFolder(String folder) throws FileExistsException {
        File f = new File(folder);
        if(f.exists()) throw new FileExistsException();
        f.mkdirs();
        //f.mkdir();
    }

    @Override
    public void renameFile(File src, File dest) throws FileExistsException, FileNotFoundException {
        if(!src.exists()) throw new FileNotFoundException();
        if(dest.exists()) throw new FileExistsException();
        src.renameTo(dest);
    }

    @Override
    public Date getLastModified(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        if(!f.exists()) throw new FileNotFoundException();
        return new Date(f.lastModified());
    }

    @Override
    public boolean deleteFolder(File folder) throws FileNotFoundException {
        File [] files = getFilesInFolder(folder);

        for(File f : files) {
            if(f.isDirectory()) {
                deleteFolder(f);
            }
            f.delete();
        }
        return folder.delete();
    }

    private boolean isImage(File inputFile) {
        return (inputFile.getName().endsWith(".png") ||
                inputFile.getName().endsWith(".jpg"));
    }

    @Override
    public File[] filterImagesFilesInDir(String dirPath) throws FileNotFoundException {
        File [] files = getFilesInFolder(new File(dirPath));
        return Arrays.stream(files)
                .filter(this::isImage)
                .toArray(File[]::new);
    }

    @Override
    public void filterImagesFilesInDirRec(File file, PrintStream out) throws FileNotFoundException {
        File [] files = getFilesInFolder(file);

        for(File f : files) {
            if(f.isDirectory()) {
                filterImagesFilesInDirRec(f,out);
            } else if(f.isFile() && isImage(f)) {
                out.println(f);
            }
        }
    }
}