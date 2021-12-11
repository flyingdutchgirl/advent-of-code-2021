package tasks.day10;

import commons.TaskUtils;
import utils.MathUtils;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Scanner;

public class SyntaxCompletion extends SyntaxCheck {

    final Map<Character, Integer> scoringMap =
            Map.of('(', 1,
                    '[', 2,
                    '{', 3,
                    '<', 4);


    public SyntaxCompletion() {
        setStage(2);
        setCorrectAnswer(2182912364L);
    }

    public static void main(String[] args) {
//        TaskUtils.fromConsole(new SyntaxCompletion());
        TaskUtils.checkAssertions(new SyntaxCompletion());
    }

    @Override
    public long getAnswer(Scanner scanner) {
        var ans = TaskUtils.lines(scanner)
                .filter(line -> checkSyntax(line).isEmpty())
                .mapToLong(this::repairIncomplete)
                .toArray();


        return MathUtils.roundToLong(MathUtils.median(ans));
    }

    protected long repairIncomplete(String line) {
        var currentsStack = new ArrayDeque<Character>();

        var opening = closingToOpeningMap.values();
        var closing = closingToOpeningMap.keySet();

        for (char ch : line.toCharArray()) {
            if (opening.contains(ch)) {
                currentsStack.addFirst(ch);
            } else if (closing.contains(ch)) {
                currentsStack.pollFirst();
            } else {
                throw new RuntimeException("Unknown char: " + ch);
            }
        }


        long score = 0;
        while (!currentsStack.isEmpty()) {
            score *= 5;
            char ch = currentsStack.pollFirst();
            score += scoringMap.getOrDefault(ch, 0);
        }


        return score;
    }

}
