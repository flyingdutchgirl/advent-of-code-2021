package tasks.day1;

import commons.TaskUtils;

import java.util.Scanner;

import static commons.TaskUtils.ints;

public class SumOfDepths extends Depths {

    public SumOfDepths() {
        setCorrectAnswer(1571);
        setStage(2);
    }

    public static void main(String[] args) {
//        TaskUtils.fromConsole(new SumOfDepths());
        TaskUtils.checkAssertions(new SumOfDepths());
    }

    @Override
    public long getAnswer(Scanner scanner) {
        int[] depths = ints(scanner).toArray();
        int[] sums = new int[depths.length - 2];

        for (int i = 0; i < sums.length; i++) {
            sums[i] = depths[i] + depths[i + 1] + depths[i + 2];
        }

        return ascendingCounter(sums);
    }

}
