package tasks.day10;

import commons.AocTask;
import commons.TaskUtils;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class SyntaxCheck extends AocTask {

    protected final Map<Character, Character> closingToOpeningMap = Map.of(
            ')', '(',
            '>', '<',
            '}', '{',
            ']', '[');

    protected final Map<Character, Integer> charToIntMap = Map.of(
            ')', 3,
            ']', 57,
            '}', 1197,
            '>', 25137);


    public SyntaxCheck() {
        super(10);
        setCorrectAnswer(316851);
    }

    public static void main(String[] args) {
        TaskUtils.checkAssertions(new SyntaxCheck());
    }

    @Override
    public long getAnswer(Scanner scanner) {

        var ans = TaskUtils.lines(scanner)
                .map(this::checkSyntax)
                .map(opt -> opt.map(ch -> charToIntMap.getOrDefault(ch, 0)))
                .mapToInt(opt -> opt.orElse(0))
                .sum();


        return ans;
    }


    protected Optional<Character> checkSyntax(String line) {
//        var closingToOpeningMap = Map.of('(', ')', '[', ']', '{', '}', '<', '>');

        var currentsStack = new ArrayDeque<Character>();

        var opening = closingToOpeningMap.values();
        var closing = closingToOpeningMap.keySet();

        for (char ch : line.toCharArray()) {
            if (opening.contains(ch)) {
                currentsStack.addFirst(ch);
            } else if (closing.contains(ch)) {
                if (currentsStack.pollFirst() != closingToOpeningMap.get(ch)) {
                    return Optional.of(ch);
                }
            } else {
                throw new RuntimeException("Unknown char: " + ch);
            }
        }

        return Optional.empty();
    }


}
