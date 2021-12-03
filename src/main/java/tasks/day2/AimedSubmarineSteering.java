package tasks.day2;

import commons.TaskUtils;

import java.util.List;

public class AimedSubmarineSteering extends SubmarineSteering {

    public AimedSubmarineSteering() {
        setStage(2);
        setCorrectAnswer(1281977850);
    }

    public static void main(String[] args) {
        TaskUtils.checkAssertions(new AimedSubmarineSteering());
    }

    @Override
    protected long parse(List<String> directions) {
            int horizontal = 0;
            int depth = 0;
            int aim = 0;

            for (String dir : directions) {
                var tokens = dir.split("\\s+");
                assert tokens.length == 2;
                String type = tokens[0].toLowerCase();
                int x = Integer.parseInt(tokens[1]);

                switch (type) {
                    case "forward" -> {
                        horizontal += x;
                        depth += (aim * x);
                    }
                    case "up" -> aim -= x;
                    case "down" -> aim += x;
                    default -> throw new IllegalArgumentException(String.format("Can't resolve command '%s'!", type));
                }

            }
            return (long) horizontal * depth;
    }
}
