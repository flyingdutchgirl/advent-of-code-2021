package tasks.day2;

import commons.AocTask;
import commons.TaskUtils;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SubmarineSteering extends AocTask {

    public SubmarineSteering() {
        super(2);
        setCorrectAnswer(1714950);
    }

    public static void main(String[] args) {
        TaskUtils.checkAssertions(new SubmarineSteering());
    }

    @Override
    public long getAnswer(Scanner scanner) {
        return parse(TaskUtils.lines(scanner).collect(Collectors.toList()));
    }


    protected long parse(List<String> directions) {
        int horizontal = 0;
        int depth = 0;

        for (String dir : directions) {
            var tokens = dir.split("\\s+");
            assert tokens.length == 2;
            String type = tokens[0].toLowerCase();
            int val = Integer.parseInt(tokens[1]);

            switch (type) {
                case "forward" -> horizontal += val;
                case "up" -> depth -= val;
                case "down" -> depth += val;
                default -> throw new IllegalArgumentException(String.format("Can't resolve command '%s'!", type));
            }

        }
        return (long) horizontal * depth;
    }


}
