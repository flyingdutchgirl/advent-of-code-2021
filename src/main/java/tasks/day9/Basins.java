package tasks.day9;

import commons.TaskUtils;
import utils.containers.BiPair;

import java.util.Collection;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Basins extends RiskPoints {

    public Basins() {
        setStage(2);
        setCorrectAnswer(882942);
    }

    public static void main(String[] args) {
        TaskUtils.checkAssertions(new Basins());
    }

    @Override
    public long getAnswer(Scanner scanner) {

        fillMap(scanner);

        var basinToPoints = this.pointValMap
                .keySet()
                .stream()
                .filter(p -> pointValMap.get(p) != 9)
                .collect(Collectors.groupingBy(this::findBasin, Collectors.counting()));

        var sizes = basinToPoints.values()
                .stream()
                .sorted(Comparator.comparingLong(i -> -i))
                .toArray(Long[]::new);

        return sizes[0] * sizes[1] * sizes[2];
    }

    BiPair<Integer> findBasin(BiPair<Integer> point) {
        BiPair<Integer> current = point;

        while (!isLowPoint(current)) {
            current = neighbours(current)
                    .filter(p -> pointValMap.containsKey(p))
                    .sorted(Comparator.comparingInt(pointValMap::get))
                    .findFirst()
                    .orElseThrow();
        }

        return current;
    }


}
