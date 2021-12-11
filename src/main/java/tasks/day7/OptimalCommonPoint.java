package tasks.day7;

import commons.AocTask;
import commons.TaskUtils;

import static utils.MathUtils.*;

import java.util.Arrays;
import java.util.Scanner;

public class OptimalCommonPoint extends AocTask {

    protected int[] positions;

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
        this.positions = Arrays.stream(scanner.nextLine().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        int median = round(median(positions));

        return fuel(positions, median);
    }


    protected int fuel(int[] arr, int destination) {
        return Arrays.stream(arr)
                .map(i -> Math.abs(i - destination))
                .sum();
    }


}
