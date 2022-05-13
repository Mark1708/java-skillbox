package mark.java;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.*;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) {
        Scanner scc = new Scanner(System.in);
        System.out.println("Введите путь к папке для копирования: ");
        Path source = Paths.get(scc.nextLine());
        System.out.println("Введите путь к источнику: ");
        Path destination = Paths.get(scc.nextLine());

        try {
            List<Path> sources = null;
            sources = Files.walk(source).collect(toList());
            List<Path> destinations = sources.stream()
                    .map(source::relativize)
                    .map(destination::resolve)
                    .collect(toList());

            for (int i = 0; i < sources.size(); i++) {
                Files.copy(sources.get(i), destinations.get(i), REPLACE_EXISTING);
            }
        } catch (IllegalArgumentException | IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
