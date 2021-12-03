package tasks.day1;

import commons.AocTask;
import commons.TaskUtils;

import java.util.Scanner;

import static commons.TaskUtils.ints;


public class Depths extends AocTask {

    public Depths() {
        super(1);
        setCorrectAnswer(1532);
    }

    protected Depths(int numberOfDay) {
        super(numberOfDay);
    }

    public static void main(String[] args) {
//        TaskUtils.fromConsole(new Depths());
        TaskUtils.checkAssertions(new Depths());
    }

    @Override
    public long getAnswer(Scanner scanner) {
        int[] depths = ints(scanner).toArray();

        return ascendingCounter(depths);
    }


    protected static int ascendingCounter(int[] arr) {
        int counter = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] > 0) {
                counter++;
            }
        }

        return counter;
    }

}


