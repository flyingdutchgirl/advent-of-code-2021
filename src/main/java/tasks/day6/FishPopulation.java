package tasks.day6;

import commons.AocTask;
import commons.TaskUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FishPopulation extends AocTask {

    public FishPopulation() {
        super(6);
        setCorrectAnswer(391888);
    }

    public static void main(String[] args) {
        TaskUtils.checkAssertions(new FishPopulation());
    }

    @Override
    public long getAnswer(Scanner scanner) {
        var intList = Arrays.stream(scanner.nextLine().split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        return calculate(intList, 80);
    }


    private int calculate(List<Integer> init, final int days) {
        return listOfFishesAfter(init, days).size();
    }


    protected List<Integer> listOfFishesAfter(List<Integer> init, int days) {
        List<Integer> fishes = new ArrayList<>(init);

        for (int i = 0; i < days; i++) {
            int len = fishes.size();
            for (int k = 0; k < len; k++) {
                int n = fishes.get(k);
                if (n == 0) {
                    fishes.set(k, 6);
                    fishes.add(8);
                } else if (n > 0) {
                    fishes.set(k, n - 1);
                }
            }
        }

        return fishes;
    }

}
