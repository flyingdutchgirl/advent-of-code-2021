package tasks.day9;

import commons.AocTask;
import commons.TaskUtils;
import utils.containers.BiPair;

import java.util.*;
import java.util.stream.Stream;

public class RiskPoints extends AocTask {
    protected final List<BiPair<Integer>> shiftConstants = List.of(
            BiPair.of(-1, 0),
            BiPair.of(1, 0),
            BiPair.of(0, -1),
            BiPair.of(0, 1)
    );
    protected Map<BiPair<Integer>, Integer> pointValMap = new HashMap<>();

    public RiskPoints() {
        super(9);
        setCorrectAnswer(558);
    }

    public static void main(String[] args) {
        TaskUtils.checkAssertions(new RiskPoints());
    }

    @Override
    public long getAnswer(Scanner scanner) {

        fillMap(scanner);

        var ans = pointValMap.keySet()
                .stream()
                .mapToInt(this::toRiskScore)
                .sum();


        return ans;
    }

    protected void fillMap(Scanner scanner) {
        for (int row = 0; scanner.hasNextLine(); row++) {
            String line = scanner.nextLine();
            char[] chars = line.toCharArray();

            for (int column = 0; column < chars.length; column++) {
                pointValMap.put(BiPair.of(row, column), Integer.parseInt("" + chars[column]));
            }
        }
    }

    int toRiskScore(BiPair<Integer> point) {
        return isLowPoint(point)
                ? this.pointValMap.get(point) + 1
                : 0;
    }

    boolean isLowPoint(BiPair<Integer> point) {

//        System.out.println();
        return neighbours(point)
                .map(p -> pointValMap.get(p))
                .filter(Objects::nonNull)
//                .peek(System.out::print)
                .mapToInt(i -> i)
//                .mapToInt(this.pointValMap::get)
                .min().orElse(10) > this.pointValMap.get(point);

    }

    protected Stream<BiPair<Integer>> neighbours(BiPair<Integer> point) {
        return shiftConstants.stream()
                .map(bipair -> bipair.combineWith(point, Integer::sum));
    }

}
