package tasks.day4;

import commons.AocTask;
import commons.TaskUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BingoRunner extends AocTask {

    protected List<BingoBoard> boards;
    protected int[] numbers;

    public BingoRunner() {
        super(4);
        setCorrectAnswer(39984);
    }


    public static void main(String[] args) {
//        TaskUtils.fromConsole(new BingoRunner());
        TaskUtils.checkAssertions(new BingoRunner());
    }

    @Override
    public long getAnswer(Scanner scanner) {
        initialize(scanner);

        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            for (BingoBoard board : boards) {
                board.drawNumber(number);
                if (board.check()) {
                    return board.score();
                }
            }
        }

        return -1;
    }


    protected void initialize(Scanner scanner) {
        String numString = scanner.nextLine();
        scanner.nextLine();

        boards = scanner
                .useDelimiter("\n\n")
                .tokens()
                .map(BingoBoard::parse)
                .collect(Collectors.toList());

        numbers = Arrays.stream(numString.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }


}
