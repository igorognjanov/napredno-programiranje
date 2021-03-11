package Exercises.VtorKolovium.FileSystem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Partial exam II 2016/2017
 */

class File implements Comparable<File> {
    String name;
    Integer size;
    LocalDateTime createdAt;

    public File(String name, Integer size, LocalDateTime createdAt) {
        this.name = name;
        this.size = size;
        this.createdAt = createdAt;
    }

    public boolean isHiddenWithSizeSmallerThan(int size) {
        return name.charAt (0) == '.' && this.size < size;
    }

    public int compareTo(File that){
        if(this.createdAt.equals (that.createdAt)){
            if(this.name.equals (that.name)){
                return Integer.compare (this.size, that.size);
            }
            return this.name.compareTo (that.name);
        }
        return this.createdAt.compareTo (that.createdAt);
    }

    public String toString(){
        return String.format ("%-11s%5dB %s", name, size, createdAt);
    }
}

class FileSystem{

    Map<Character, Set<File>> folderWithFiles;
    Map<Integer, Set<File>> byYear;
    Map<String, Long> sizeByMonthAndDay;

    public FileSystem() {
        this.byYear = new HashMap<> ();
        this.folderWithFiles = new HashMap<> ();
        this.sizeByMonthAndDay = new HashMap<> ();
    }

    public void addFile(char folder, String name, int size, LocalDateTime createdAt){
        folderWithFiles.putIfAbsent (folder, new TreeSet<File> (File::compareTo));
        folderWithFiles.get (folder).add (new File (name, size, createdAt));

        byYear.putIfAbsent (createdAt.getYear (), new TreeSet<File> (File::compareTo));
        byYear.get (createdAt.getYear ()).add (new File (name, size, createdAt));

        String date = createdAt.getMonth ().toString () + "-"+createdAt.getDayOfMonth ();
        sizeByMonthAndDay.putIfAbsent (date, (long)0);
        sizeByMonthAndDay.put (date,
                sizeByMonthAndDay.get (date) + (long) size);

    }


    public List<File> findAllHiddenFilesWithSizeLessThen(int size){
        return folderWithFiles.values ().stream ()
                .flatMap (Collection::stream)
                .filter (set -> set.isHiddenWithSizeSmallerThan (size))
                .collect (Collectors.toList ());
    }

    public int totalSizeOfFilesFromFolders(List<Character> folders){
        return (int)folders.stream ()
                .map (folder -> folderWithFiles.get (folder))
                .flatMap (set -> set.stream ())
                .mapToInt (file -> file.size)
                .sum ();
    }

    public Map<Integer, Set<File>> byYear(){
        return byYear;
    }


    public Map<String, Long> sizeByMonthAndDay(){
        return sizeByMonthAndDay;
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

