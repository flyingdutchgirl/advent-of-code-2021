package tasks.day8;

import commons.AocTask;
import commons.TaskUtils;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

import static commons.TaskUtils.lines;

public class Displays extends AocTask {

    public Displays() {
        super(8);
        setCorrectAnswer(352);
    }

    public static void main(String[] args) {
        TaskUtils.fromConsole(new Displays());
    }

    @Override
    public long getAnswer(Scanner scanner) {
        final Set<Integer> acceptedLengths = Set.of(2, 3, 4, 7);

        var ans = lines(scanner)
                .map(line -> line.split("\\|")[1].trim())
                .flatMap(text -> Arrays.stream(text.split("\\s+")))
                .map(String::length)
                .filter(acceptedLengths::contains)
                .count();


        return ans;
    }
}
