package tasks.day4;

import commons.TaskUtils;

import java.util.Scanner;
import java.util.stream.Collectors;

public class BingoLosing extends BingoRunner {

    public BingoLosing() {
        setStage(2);
        setCorrectAnswer(8468);
    }

    public static void main(String[] args) {
//        TaskUtils.fromConsole(new BingoLosing());
        TaskUtils.checkAssertions(new BingoLosing());
    }

    @Override
    public long getAnswer(Scanner scanner) {
        initialize(scanner);

        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];

            for (BingoBoard board : boards) {
                board.drawNumber(number);
            }

            var stillInGame = boards.stream()
                    .filter(b -> !b.check())
                    .collect(Collectors.toList());

            if (stillInGame.size() < 2) {
                stillInGame.get(0).drawNumber(numbers[i+1]); // ?
                return stillInGame.get(0).score();
            }

        }
        return -2;
    }
}
