package mk.ukim.finki.np.vezhbi.za_juni.file_system;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


class File {
    char folder;
    String name;
    int size;
    LocalDateTime time;

    public File(char folder,String name, int size, LocalDateTime time) {
        this.name = name;
        this.size = size;
        this.time = time;
        this.folder = folder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public char getFolder() {
        return folder;
    }

    public void setFolder(char folder) {
        this.folder = folder;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getMonthDay() {
        return String.format("%s-%d",getTime().getMonth(), getTime().getDayOfMonth());
    }
}

class FileSystem {
    List<File> files = new ArrayList<>();
    final Comparator<File> dateNameSizeComparator = Comparator.comparing(File::getTime)
            .thenComparing(File::getName)
            .thenComparing(File::getSize);


    public void addFile(char charAt, String part, int parseInt, LocalDateTime minusDays) {
        files.add(new File(charAt,part,parseInt,minusDays));
    }


    public List<File> findAllHiddenFilesWithSizeLessThen(int size) {
        return files.stream()
                .filter(i -> i.getFolder() == '.')
                .filter(i -> i.getSize() < size)
                .sorted(dateNameSizeComparator)
                .collect(Collectors.toList());
    }

    public int totalSizeOfFilesFromFolders(List<Character> folders) {
        return files.stream()
                .filter(i -> folders.contains(i))
                .mapToInt(i -> i.getSize())
                .sum();
    }

    public Map<Integer, Set<File>> byYear() {
        return files.stream()
                .collect(Collectors.groupingBy(
                        i -> i.getTime().getYear(),
                        HashMap::new,
                        Collectors.toCollection(TreeSet::new)
                ));
    }

    public Map<String, Long> sizeByMonthAndDay() {
        return files.stream()
                .collect(Collectors.groupingBy(
                        i -> i.getMonthDay(),
                        Collectors.summingLong(i -> i.getSize())
                ));
    }
}

public class FileSystemTest {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            fileSystem.addFile(parts[0].charAt(0), parts[1],
                    Integer.parseInt(parts[2]),
                    LocalDateTime.of(2016, 12, 29, 0, 0, 0).minusDays(Integer.parseInt(parts[3]))
            );
        }
        int action = scanner.nextInt();
        if (action == 0) {
            scanner.nextLine();
            int size = scanner.nextInt();
            System.out.println("== Find all hidden files with size less then " + size);
            List<File> files = fileSystem.findAllHiddenFilesWithSizeLessThen(size);
            files.forEach(System.out::println);
        } else if (action == 1) {
            scanner.nextLine();
            String[] parts = scanner.nextLine().split(":");
            System.out.println("== Total size of files from folders: " + Arrays.toString(parts));
            int totalSize = fileSystem.totalSizeOfFilesFromFolders(Arrays.stream(parts)
                    .map(s -> s.charAt(0))
                    .collect(Collectors.toList()));
            System.out.println(totalSize);
        } else if (action == 2) {
            System.out.println("== Files by year");
            Map<Integer, Set<File>> byYear = fileSystem.byYear();
            byYear.keySet().stream().sorted()
                    .forEach(key -> {
                        System.out.printf("Year: %d\n", key);
                        Set<File> files = byYear.get(key);
                        files.stream()
                                .sorted()
                                .forEach(System.out::println);
                    });
        } else if (action == 3) {
            System.out.println("== Size by month and day");
            Map<String, Long> byMonthAndDay = fileSystem.sizeByMonthAndDay();
            byMonthAndDay.keySet().stream().sorted()
                    .forEach(key -> System.out.printf("%s -> %d\n", key, byMonthAndDay.get(key)));
        }
        scanner.close();
    }
}

// Your code here

