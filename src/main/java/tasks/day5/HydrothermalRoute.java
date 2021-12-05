package tasks.day5;

import commons.AocTask;
import commons.TaskUtils;
import utils.containers.BiPair;
import utils.containers.Pair;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HydrothermalRoute extends AocTask {

    private int[][] grid;
    Map<BiPair<Integer>, Integer> map;
    List<BiPair<BiPair<Integer>>> lines;

    public HydrothermalRoute() {
        super(5);
        setCorrectAnswer(5608);
    }

    public static void main(String[] args) {
        TaskUtils.checkAssertions(new HydrothermalRoute());
    }

    @Override
    public long getAnswer(Scanner scanner) {
        lines = TaskUtils.lines(scanner)
                .map(this::parseLine)
                .collect(Collectors.toList());

        prepareGrid(lines);

        long answer = map.values().stream()
                .filter(i -> i > 1)
                .count();

        return answer;
    }

    BiPair<BiPair<Integer>> parseLine(String line) {
        int[] coordinates = Arrays.stream(line.split("\\D+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        var start = BiPair.of(coordinates[0], coordinates[1]);
        var finish = BiPair.of(coordinates[2], coordinates[3]);

        return BiPair.of(start, finish);
    }


    void prepareGrid(List<BiPair<BiPair<Integer>>> pairs) {
        map = new HashMap<>();

        var filteredList = pairs.stream()
                .filter(line -> {
                    var p1 = line.fst();
                    var p2 = line.snd();
                    return Objects.equals(p1.fst(), p2.fst()) || Objects.equals(p1.snd(), p2.snd());
                }).collect(Collectors.toList());

        for (BiPair<BiPair<Integer>> line : filteredList) {
            var start = line.fst();
            var end = line.snd();

            Function<BiPair<Integer>, BiPair<Integer>> fun = null;

            if (start.fst() - end.fst() > 0) {
                fun = point -> BiPair.of(point.fst() - 1, point.snd());
            } else if (end.fst() - start.fst() > 0) {
                fun = point -> BiPair.of(point.fst() + 1, point.snd());
            } else if (start.snd() - end.snd() > 0) {
                fun = point -> BiPair.of(point.fst(), point.snd() - 1);
            } else if (end.snd() - start.snd() > 0) {
                fun = point -> BiPair.of(point.fst(), point.snd() + 1);
            }

            while (!start.equals(end)) {
                map.merge(start, 1, Integer::sum);
                assert fun != null;
                start = fun.apply(start);
            }
            map.merge(start, 1, Integer::sum);

        }
    }


}
