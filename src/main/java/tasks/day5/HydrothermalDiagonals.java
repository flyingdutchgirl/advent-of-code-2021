package tasks.day5;

import commons.TaskUtils;
import utils.containers.BiPair;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HydrothermalDiagonals extends HydrothermalRoute {

    public HydrothermalDiagonals() {
        setStage(2);
        setCorrectAnswer(20299);
    }

    public static void main(String[] args) {
        TaskUtils.checkAssertions(new HydrothermalDiagonals());
    }

    @Override
    public long getAnswer(Scanner scanner) {
        super.getAnswer(scanner);

        var filtered = lines.stream()
                .filter(line -> {
                    var start = line.fst();
                    var end = line.snd();
                    boolean check1 = Math.abs(start.fst() - end.fst()) == Math.abs(start.snd() - end.snd());
                    boolean check2 = Math.abs(start.fst() - end.fst()) > 0;
                    return check1 && check2;
                }).collect(Collectors.toList());

        updateMap(filtered);

        long answer = map.values().stream()
                .filter(i -> i > 1)
                .count();

        return answer;
    }

    void updateMap(List<BiPair<BiPair<Integer>>> filtered) {
        for (BiPair<BiPair<Integer>> line : filtered) {
            var start = line.fst();
            var end = line.snd();
            final int deltaX = end.fst() - start.fst() > 0 ? 1 : -1;
            final int deltaY = end.snd() - start.snd() > 0 ? 1 : -1;
            Function<BiPair<Integer>, BiPair<Integer>> fun =
                    point -> BiPair.of(point.fst() + deltaX, point.snd() + deltaY);

            while (!start.equals(end)) {
                map.merge(start, 1, Integer::sum);
                start = fun.apply(start);
            }
            map.merge(start, 1, Integer::sum);
        }
    }

}
