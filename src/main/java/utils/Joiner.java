package utils;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Joiner {

    public static void readFilesFromDirectory(String inputPath, String outputPath) {
        if (inputPath != null) {
            var directory = new File(inputPath);
            if (directory.exists()) {
                if (directory.isDirectory()) {
                    var files = sortedFiles(DirectoryReader.getFiles(directory));
                    joinFile(files, outputPath);
                } else System.out.println("First path should lead to directory but file is found");
            } else System.out.println("First path should lead to existing directory");
        }
    }

    private static List<File> sortedFiles(List<File> files) {
        return files.stream().sorted(Comparator.comparing(file -> FilenameUtils.getName(file.getAbsolutePath()))).toList();
    }

    private static void joinFile(List<File> files, String path) {
        var resultContent = new StringBuilder();
        for (var file : files) {
            var content = new StringBuilder();
            try (var myReader = new Scanner(file)) {
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                    content.append(data).append(System.lineSeparator());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            resultContent.append(content);
        }
        try (var myWriter = new FileWriter(path)) {
            myWriter.write(resultContent.toString());
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to result file. Please, check if there is access to result file.");
        }
    }
}
