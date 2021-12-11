package commons;

import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TaskUtils {

    private static final Pattern NEWLINE_PATTERN
            = Pattern.compile("\n");
    //    static final String FILEPATH_TEMPLATE = "src/inputs/input%d.txt";
//    static final String FILEPATH_TEMPLATE = "src/main/java/inputs/input%d.txt";
    static final String FILEPATH_TEMPLATE = "src/main/resources/inputs/input%d.txt";


    public static Stream<String> lines(Scanner scanner) {
        return scanner
                .useDelimiter(NEWLINE_PATTERN)
                .tokens();
    }

    public static IntStream ints(Scanner scanner) {
        return scanner
                .useDelimiter(NEWLINE_PATTERN)
                .tokens()
                .mapToInt(Integer::parseInt);
    }

    public static void fromConsole(AocTask taskObj) {
        var scanner = new Scanner(System.in);

        long ans = taskObj.getAnswer(scanner);

        System.out.println("ans = " + ans);
    }


    public static void checkAssertions(AocTask taskObj, int numberOfDay) {
        final String path = filepath(numberOfDay);
        try (Scanner fileScanner = FileUtils.getScannerFromFile(path)) {
            final long ans = taskObj.getAnswer(fileScanner);
            final int stage = taskObj.getStage();

            String className = taskObj.getClass().getName();

            final String template = "class = %s, day = %d, stage = %d, answer = %d";
            String msg = String.format(template, className, numberOfDay, stage, ans);

            msg += taskObj
                    .getExpected()
                    .map(longVal ->
                            String.format(", expected = %d, isCorrect = %b", longVal, longVal == ans)
                    ).orElse("");

            System.out.println(msg);

        } catch (FileNotFoundException e) {
            System.out.println("Failed due to file issues!");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Failed due to an exception during execution!");
            e.printStackTrace();
        }
    }

    public static void checkAssertions(AocTask aocTask) {
        checkAssertions(aocTask, aocTask.getNumberOfDay());
    }

    static String filepath(int numberOfDay) {
        return String.format(FILEPATH_TEMPLATE, numberOfDay);
    }

    public static long countTime(AocTask task) {
        long start = System.currentTimeMillis();
        checkAssertions(task);
        long end = System.currentTimeMillis();

        System.out.println("Execution took " + (end - start) + " ms");

        return end - start;
    }

}
