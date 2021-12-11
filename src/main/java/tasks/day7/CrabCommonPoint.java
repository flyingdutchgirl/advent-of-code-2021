package tasks.day7;

import commons.TaskUtils;
import utils.MathUtils;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Scanner;
import java.util.function.IntUnaryOperator;

public class CrabCommonPoint extends OptimalCommonPoint {

    public CrabCommonPoint() {
        setStage(2);
        setCorrectAnswer(95476244L);
    }

    public static void main(String[] args) {
        TaskUtils.checkAssertions(new CrabCommonPoint());
    }

    @Override
    public long getAnswer(Scanner scanner) {
        // filling protected int[] this.ints array
        super.getAnswer(scanner);

        var stats = new IntSummaryStatistics();
        Arrays.stream(this.positions).forEach(stats::accept);

        var mean = MathUtils.round(stats.getAverage());

        return Math.min(fuel(this.positions, mean), fuel(this.positions, mean - 1));
    }

    @Override
    protected int fuel(int[] arr, final int destination) {
        int sum = 0;
        IntUnaryOperator distance = start -> {
            int dist = Math.abs(start - destination);
            return ((dist + 1) * dist) / 2;
        };

        for (int n : arr) {
            sum += distance.applyAsInt(n);
        }

        return sum;
    }
}
