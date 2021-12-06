package tasks.day6;

import commons.TaskUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HugeFishPopulation extends FishPopulation {

    private Map<Integer, Map<Integer, Long>> initMap;
    private final int STEP = 128;
    private final int LIMIT = 256; // !!!
    private final int INIT_DAY = 128;


    public HugeFishPopulation() {
        setStage(2);
        setCorrectAnswer(1754597645339L);
    }


    public static void main(String[] args) {
        TaskUtils.checkAssertions(new HugeFishPopulation());
    }


    @Override
    public long getAnswer(Scanner scanner) {
        var ints = Arrays.stream(scanner.nextLine().split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());


        initMap = new HashMap<>();

        for (int i = 0; i < 9; i++) { // because timers are 0 <= timer < 9
            initMap.put(i, mapAfter(i, INIT_DAY));
        }

        return ints.stream()
                .mapToLong(i -> recursiveMapping(i, INIT_DAY, initMap.get(i)))
                .sum();
    }


    private Long recursiveMapping(final int timer, final int day, Map<Integer, Long> map) {
        if (day >= LIMIT) {
            if (!initMap.containsKey(timer)) {
                throw new IllegalStateException("absent timer = " + timer);
            }

            return initMap.get(timer)
                    .values()
                    .stream().mapToLong(i -> i)
                    .sum();
        }

        long sum = 0;

        for (Map.Entry<Integer, Long> entry : map.entrySet()) {
            int key = entry.getKey();
            long val = entry.getValue();

            long temp = val * recursiveMapping(key, day + STEP, map);
            sum += temp;
        }

        return sum;
    }

    private Map<Integer, Long> mapAfter64Days(int timer) {
        return mapAfter(timer, 64);
    }


    private Map<Integer, Long> mapAfter(int timer, int days) {
        return listOfFishesAfter(List.of(timer), days)
                .stream()
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));
    }

}
