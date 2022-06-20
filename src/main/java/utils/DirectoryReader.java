package utils;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DirectoryReader {

    private static final List<String> textFormats = List.of("txt");

    private static boolean checkIfCorrectFile(File f) {
        if (f.isFile()) {
            var path = f.getAbsolutePath();
            return textFormats.contains(FilenameUtils.getExtension(path));
        } else return false;
    }

    public static List<File> getFiles(File f) {
        var listFiles = f.listFiles() == null ? new ArrayList<File>() : Arrays.stream(f.listFiles()).toList();
        if (f.isFile() && checkIfCorrectFile(f)) {
            return List.of(f);
        } else {
            var files = new ArrayList<File>();
            for (var file : listFiles) {
                files.addAll(getFiles(file).stream().filter(Objects::nonNull).toList());
            }
            return files;
        }
    }
}
