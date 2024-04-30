package InterviewQuestions.preperations.np_mid_term_1.file_system;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class FileNameExistsException extends Exception {
    public FileNameExistsException(String fileName, String folderName) {
        super(String.format("There is already a file %s test in the folder %s", fileName, folderName));
    }
}

class IndentPrinting{
    public static String makeIndentation(int level) {
        return IntStream.range(0, level)
                .mapToObj(i -> "\t")
                .collect(Collectors.joining());
    }
}

interface IFile extends Comparable<IFile>{
    String getFileName();
    long getFileSize();
    String getFileInfo(int indent);
    void sortBySize();
    long findLargestFile();
}

class File implements IFile{

    protected String name;
    protected long size;

    public File() {
    }

    public File(String name) {
        this.name = name;
        this.size = 0L;
    }

    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getFileName() {
        return this.name;
    }

    @Override
    public long getFileSize() {
        return this.size;
    }

    @Override
    public String getFileInfo(int indent) {
        return String.format("%sFile name: %10s File size: %10d\n",
                IndentPrinting.makeIndentation(indent),
                this.getFileName(),
                this.getFileSize());
    }

    @Override
    public void sortBySize() {
    }

    @Override
    public long findLargestFile() {
        return this.size;//bidejkji samiot file i najgolem
    }

    @Override
    public int compareTo(@NotNull IFile other) {
        return Long.compare(this.size, other.getFileSize());
    }
}

class Folder extends File implements IFile{
    //mozhe i Folder da nasleduva od File
    //i da se povika super(name)
    private List<IFile> files;

    public Folder(String name) {
        super(name);
        this.files = new ArrayList<>();
    }

    void addFile(IFile newFile) throws FileNameExistsException {
        boolean alreadyExists = this.files.stream()
                .anyMatch(file -> file.getFileName().equals(newFile.getFileName()));
        if(alreadyExists) {
            throw new FileNameExistsException(newFile.getFileName(), this.name);
        }
        this.files.add(newFile);
    }

    public List<IFile> getFiles() {
        return files;
    }

    @Override
    public String getFileName() {
        return this.name;
    }

    @Override
    public long getFileSize() {
        return this.files.stream().mapToLong(IFile::getFileSize).sum();
    }

    @Override
    public String getFileInfo(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%sFolder name: %10s Folder size: %10d\n",
                IndentPrinting.makeIndentation(indent),
                this.name,
                this.getFileSize()));
        this.files.forEach(file -> sb.append(file.getFileInfo(indent + 1)));

        return sb.toString();
    }

    @Override
    public void sortBySize() {
        Comparator<IFile> comparator = Comparator.comparingLong(IFile::getFileSize);
        this.files.sort(comparator);
        this.files.forEach(IFile::sortBySize);
    }

    @Override
    public long findLargestFile() {
//        IFile largestFile = this.files.stream()
//                .max(Comparator.naturalOrder())
//                .orElseGet(File::new);
        OptionalLong largest = files.stream().mapToLong(IFile::findLargestFile).max();
        if (largest.isPresent())
            return largest.getAsLong();
        return 0L;
    }

    @Override
    public int compareTo(@NotNull IFile other) {
        return Long.compare(this.size, other.getFileSize());
    }
}

class FileSystem {
    private Folder rootDirectory;

    public FileSystem() {
        this.rootDirectory = new Folder("root");
    }

    public void addFile(IFile file) throws FileNameExistsException {
        this.rootDirectory.addFile(file);
    }

    public long findLargestFile() {
//        IFile file = this.rootDirectory.getFiles().stream()
//                .max(Comparator.naturalOrder())
//                .orElseGet(File::new);
//        return file.getFileSize();
        return this.rootDirectory.findLargestFile();
    }

    public void sortBySize() {
//        this.rootDirectory.getFiles().stream().sorted(Comparator.naturalOrder());
        this.rootDirectory.sortBySize();
    }

    @Override
    public String toString() {
        return this.rootDirectory.getFileInfo(0);
    }
}

public class FileSystemTest {

    public static Folder readFolder (Scanner sc)  {

        Folder folder = new Folder(sc.nextLine());
        int totalFiles = Integer.parseInt(sc.nextLine());

        for (int i=0;i<totalFiles;i++) {
            String line = sc.nextLine();

            if (line.startsWith("0")) {
                String fileInfo = sc.nextLine();
                String [] parts = fileInfo.split("\\s+");
                try {
                    folder.addFile(new File(parts[0], Long.parseLong(parts[1])));
                } catch (FileNameExistsException e) {
                    System.out.println(e.getMessage());
                }
            }
            else {
                try {
                    folder.addFile(readFolder(sc));
                } catch (FileNameExistsException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return folder;
    }

    public static void main(String[] args)  {

        //file reading from input

        Scanner sc = new Scanner (System.in);

        System.out.println("===READING FILES FROM INPUT===");
        FileSystem fileSystem = new FileSystem();
        try {
            fileSystem.addFile(readFolder(sc));
        } catch (FileNameExistsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("===PRINTING FILE SYSTEM INFO===");
        System.out.println(fileSystem.toString());

        System.out.println("===PRINTING FILE SYSTEM INFO AFTER SORTING===");
        fileSystem.sortBySize();
        System.out.println(fileSystem.toString());

        System.out.println("===PRINTING THE SIZE OF THE LARGEST FILE IN THE FILE SYSTEM===");
        System.out.println(fileSystem.findLargestFile());
    }
}
