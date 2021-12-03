package tasks.day3;

import commons.TaskUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LifeSupportRating extends PowerConsumption {

    public LifeSupportRating() {
        setStage(2);
        setCorrectAnswer(3379326);
    }

    public static void main(String[] args) {
//        TaskUtils.fromConsole(new LifeSupportRating());
//        System.out.println(gammaRate(List.of("10110", "10111")));
        TaskUtils.checkAssertions(new LifeSupportRating());
    }

    @Override
    public long getAnswer(Scanner scanner) {
        var listLines = TaskUtils.lines(scanner).collect(Collectors.toList());

        Function<List<String>, String> oxygenFun = PowerConsumption::gammaRate;
        Function<List<String>, String> co2Fun = list -> PowerConsumption.epsilonRate(gammaRate(list));

        int oxygen = applyBitCriteria(listLines, oxygenFun);
        int co2 = applyBitCriteria(listLines, co2Fun);

        return oxygen * co2;
    }


    private static int applyBitCriteria(List<String> lines,
                                        Function<List<String>, String> rateGenerator) {
        String rate = rateGenerator.apply(lines);
        final int len = lines.get(0).length();
        List<String> tempList = new ArrayList<>(lines);

        for (int i = 0; i < len && tempList.size() > 1; i++) {
            char req = rate.charAt(i);
            Iterator<String> iter = tempList.iterator();
            while (iter.hasNext()) {
                String line = iter.next();
                if (line.charAt(i) != req) {
                    iter.remove();
                }

                rate = rateGenerator.apply(tempList);
            }
        }

        return Integer.parseInt(tempList.get(0), BINARY_RADIX);
    }


}
