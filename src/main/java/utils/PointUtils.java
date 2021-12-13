package utils;

import utils.containers.BiPair;

import java.util.List;
import java.util.stream.Collectors;

public class PointUtils {

    static final List<BiPair<Integer>> neighbourConsts = List.of(
            BiPair.of(-1, -1),
            BiPair.of(-1, 0),
            BiPair.of(-1, 1),
            BiPair.of(0, -1),
            BiPair.of(0, 1),
            BiPair.of(1, -1),
            BiPair.of(1, 0),
            BiPair.of(1, 1)
    );

    static final List<BiPair<Integer>> fourNeighboursConsts = List.of(
            BiPair.of(0, -1),
            BiPair.of(0, 1),
            BiPair.of(-1, 0),
            BiPair.of(1, 0)
    );


    public static List<BiPair<Integer>> neighbours(BiPair<Integer> point) {
        return neighbourConsts.stream()
                .map(elem -> elem.combineWith(point, Integer::sum))
                .collect(Collectors.toList());
    }


    public static List<BiPair<Integer>> fourDirectionsNeighbours(BiPair<Integer> point) {
        return fourNeighboursConsts.stream()
                .map(elem -> elem.combineWith(point, Integer::sum))
                .collect(Collectors.toList());
    }


}
