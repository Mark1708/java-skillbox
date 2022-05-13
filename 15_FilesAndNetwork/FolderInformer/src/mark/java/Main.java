package mark.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    private static String getReadableSize(long size){
        String[]units = new String[]{ "B", "KB", "MB", "GB", "TB" };
        int unitIndex = (int) (Math.log10(size) / 3);
        double unitValue = 1 << (unitIndex *  10);

        return new DecimalFormat("#,##0.#")
                .format(size / unitValue) + " "
                + units[unitIndex];
    }

    private static long getFolderSize(String path){
        Path folder = Paths.get(path);
        long size = 0;
        try {
            size = Files.walk(folder)
                    .filter(p -> p.toFile().isFile())
                    .mapToLong(p -> p.toFile().length())
                    .sum();
        } catch (IllegalArgumentException | IOException | NullPointerException e) {
            throw new IllegalArgumentException("Путь указан неверно или прервана операция чтения папки");
        }
        return size;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите путь до папки: ");
        String path = scanner.nextLine();

        File folder = new File(path);
        if (folder.isDirectory()){
            System.out.println("Размер папки " + path +" cоставляет " + getReadableSize(getFolderSize(path)));
        }

    }
}
