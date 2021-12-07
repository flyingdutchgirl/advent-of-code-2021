package tasks.day7;

import commons.AocTask;
import commons.TaskUtils;
import utils.MathUtils;

import java.util.Arrays;
import java.util.Scanner;

public class OptimalCommonPoint extends AocTask {

    protected int[] ints;

    public OptimalCommonPoint() {
        super(7);
        setCorrectAnswer(339321);
    }

    public static void main(String[] args) {
//        TaskUtils.fromConsole(new OptimalCommonPoint());
        TaskUtils.checkAssertions(new OptimalCommonPoint());
    }

    @Override
    public long getAnswer(Scanner scanner) {
        this.ints = Arrays.stream(scanner.nextLine().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        int median = MathUtils.median(ints);

        return fuel(ints, median);
    }


    protected int fuel(int[] arr, int destination) {
        return Arrays.stream(arr)
                .map(i -> Math.abs(i - destination))
                .sum();
    }


}
