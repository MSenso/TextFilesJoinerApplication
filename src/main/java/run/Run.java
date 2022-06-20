package run;

import utils.Joiner;

public class Run {

    private static boolean isCorrectArgs(String[] args) {
        return args.length == 2;
    }

    public static void main(String[] args) {
        if (isCorrectArgs(args)) {
            Joiner.readFilesFromDirectory(args[0], args[1]);
        } else
            System.out.println("Args should have a path to root directory of txt files and path to a result txt file." + System.lineSeparator() +
                    "Also, please, check if there are any spaces in the path and try to move file/directory to another place");
    }
}
