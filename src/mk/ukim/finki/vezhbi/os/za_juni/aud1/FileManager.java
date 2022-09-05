package mk.ukim.finki.vezhbi.os.za_juni.aud1;



import java.io.*;
import java.util.Arrays;
import java.util.Date;

class FileExistsException extends Exception {
}

interface IFileManager{
        void createNewFile(String file) throws IOException, FileExistsException;

        File[] getFilesInFolder(File file) throws FileNotFoundException;

        void printFileNames(File file, PrintStream writer) throws FileNotFoundException;

        String getAbsolutePath(String relativePath) throws FileNotFoundException;

        long getFileSize(String file) throws FileNotFoundException;

        void printFilePermissions(File f, PrintStream writer) throws FileNotFoundException;

        void createFolder(String folder) throws FileExistsException;

        void renameFile(File src, File dest) throws FileExistsException, FileNotFoundException;

        Date getLastModified(String filePath) throws FileNotFoundException;

        boolean deleteFolder(File folder) throws FileNotFoundException;

        File[] filterImagesFilesInDir(String dirPath) throws FileNotFoundException;

        void filterImagesFilesInDirRec(File file, PrintStream out) throws FileNotFoundException;
}

public class FileManager implements IFileManager{

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

//                File [] files = file.listFiles(new FilenameFilter() {
//                        @Override
//                        public boolean accept(File dir, String name) {
//                                return dir.getName().startsWith("finki") && dir.getName().endsWith(".jpg");
//                        }
//                }); kako primer go koristev ova
//


                return file.listFiles();
        }

        @Override
        public void printFileNames(File file, PrintStream writer) throws FileNotFoundException {
                if(!file.exists()) throw new FileNotFoundException();
                Arrays.stream(this.getFilesInFolder(file))
                        .forEach(i -> writer.println(i.getName()));
                writer.flush();
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
                Arrays.stream(this.getFilesInFolder(f))
                        .forEach(i -> {
                                writer.printf("Read: %x, Write: %x, Execute: %x",
                                        f.canRead(),
                                        f.canWrite(),
                                        f.canExecute());
                        });
        }

        @Override
        public void createFolder(String folder) throws FileExistsException {
                File f = new File(folder);
                if(f.exists()) throw new FileExistsException();
                f.mkdir();
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
                if(!folder.exists()) throw new FileNotFoundException();

                File [] files = folder.listFiles();

                for(File f : files) {
                        if(f.isDirectory()) {
                                deleteFolder(f);
                        } else {
                                f.delete();
                        }
                }

                return folder.delete();
        }

        private boolean isImage(String fileName) {
                return fileName.endsWith(".jpg") ||
                        fileName.endsWith(".png");
        }

        @Override
        public File[] filterImagesFilesInDir(String dirPath) throws FileNotFoundException {
                File [] files = this.getFilesInFolder(new File(dirPath));
                return Arrays.stream(files)
                        .filter(i -> isImage(i.getName()))
                        .toArray(File[]::new);
        }

        @Override
        public void filterImagesFilesInDirRec(File file, PrintStream out) throws FileNotFoundException {
                File [] files = getFilesInFolder(file);

                for(File f : files) {
                        if(f.isDirectory()) {
                                this.filterImagesFilesInDirRec(f,out);
                        } else if(f.isFile() && this.isImage(f.getName())) {
                                out.println(f);
                        }
                }
        }
}
