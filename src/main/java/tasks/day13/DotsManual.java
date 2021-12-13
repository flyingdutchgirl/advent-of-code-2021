package tasks.day13;

import commons.AocTask;
import commons.TaskUtils;
import utils.containers.BiPair;
import utils.containers.Pair;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DotsManual extends AocTask {
    protected List<BiPair<Integer>> points;
    protected Collection<BiPair<Integer>> foldedPoints;
    protected List<Pair<Character, Integer>> commands;
    protected final char axisX = 'x';
    protected final char axisY = 'y';


    public DotsManual() {
        super(13);
        setCorrectAnswer(664);
    }

    public static void main(String[] args) {
        TaskUtils.checkAssertions(new DotsManual());
    }

    @Override
    public long getAnswer(Scanner scanner) {
        init(scanner);

        return stageOne();
    }

    protected void init(Scanner scanner) {
        this.points = TaskUtils.lines(scanner)
                .takeWhile(Predicate.not(String::isBlank))
                .map(str -> str.split(","))
//                .map(arr -> BiPair.of(Integer.parseInt(arr[0]), Integer.parseInt(arr[1])))
                .map(arr -> BiPair.of(Integer.parseInt(arr[1]), Integer.parseInt(arr[0])))
                .collect(Collectors.toList());

        scanner.nextLine(); // skip line

        this.commands = TaskUtils.lines(scanner)
                .takeWhile(Predicate.not(String::isBlank))
                .map(this::parseCommand)
                .collect(Collectors.toList());
    }


    private int stageOne() {
        this.foldedPoints = this.points;
        var cmd = this.commands.get(0);

        foldedPoints = cmd.fst() == axisX
                ? calculateX(cmd.snd(), foldedPoints)
                : calculateY(cmd.snd(), foldedPoints);

        return foldedPoints.size();
    }

    private Pair<Character, Integer> parseCommand(String line) {
        char axis = line.contains("x") ? axisX : axisY;
        int val = Integer.parseInt(line.replaceAll("\\D+", ""));
        return Pair.of(axis, val);
    }

    protected Collection<BiPair<Integer>> calculateX(int val, Collection<BiPair<Integer>> collection) {
        var set1 = collection
                .stream()
                .filter(p -> p.snd() > val)
                .filter(p -> p.snd() <= 2 * val)
                .map(p -> BiPair.of(
                                p.fst(),
                                val - Math.abs(p.snd() - val)
                        )
                ).collect(Collectors.toSet());

        var set2 = collection
                .stream()
                .filter(p -> p.snd() < val)
                .collect(Collectors.toSet());

        set1.addAll(set2);
        return set1;
    }


    protected Collection<BiPair<Integer>> calculateY(int val, Collection<BiPair<Integer>> collection) {
        var set1 = collection
                .stream()
                .filter(p -> p.fst() > val)
                .filter(p -> p.fst() <= 2 * val)
                .map(p -> BiPair.of(
                                val - Math.abs(p.fst() - val),
                                p.snd()
                        )
                ).collect(Collectors.toSet());

        var set2 = collection
                .stream()
                .filter(p -> p.fst() < val)
                .collect(Collectors.toSet());

        set1.addAll(set2);
        return set1;
    }

    protected String pointGrid(Collection<BiPair<Integer>> pointList) {
        var statsX = pointList.stream()
                .mapToInt(BiPair::fst)
                .summaryStatistics();

        final int minX = statsX.getMin();
        final int maxX = statsX.getMax();

        var statsY = pointList.stream()
                .mapToInt(BiPair::snd)
                .summaryStatistics();

        final int minY = statsY.getMin();
        final int maxY = statsY.getMax();

        StringBuilder sb = new StringBuilder();
        final char SPACE = ' ';
        final char HASH = '#';
        final char NEWLINE = '\n';


        for (int k = minX; k <= maxY; k++) {

            for (int i = minY; i <= maxY; i++) {
                char ch = pointList.contains(BiPair.of(k, i))
                        ? HASH
                        : SPACE;
                sb.append(ch);
            }
            sb.append(NEWLINE);
        }
        sb.append(NEWLINE);


        return sb.toString();
    }


}
