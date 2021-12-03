package tasks.day3;

import commons.AocTask;
import commons.TaskUtils;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PowerConsumption extends AocTask {

    protected final static int BINARY_RADIX = 2;


    public PowerConsumption() {
        super(3);
        setCorrectAnswer(693486);
    }

    public static void main(String[] args) {
        TaskUtils.checkAssertions(new PowerConsumption());
    }

    @Override
    public long getAnswer(Scanner scanner) {
        var lineList = TaskUtils.lines(scanner).collect(Collectors.toList());

        String gammaStr = gammaRate(lineList);
        String epsilonStr = epsilonRate(gammaStr);

        int gamma = Integer.parseInt(gammaStr, BINARY_RADIX);
        int epsilon = Integer.parseInt(epsilonStr, BINARY_RADIX);

        return gamma * epsilon;
    }


    protected static String gammaRate(List<String> lines) {
        StringBuilder gamma = new StringBuilder();
        int len = lines.get(0).length();

        for (int i = 0; i < len; i++) {
            int finalI = i;
            var isZeroMap = lines.stream()
                    .map(line -> line.charAt(finalI))
                    .collect(Collectors.partitioningBy(ch -> ch == '0', Collectors.counting()));

            char digit = isZeroMap.get(true) > isZeroMap.get(false) ? '0' : '1';
            gamma.append(digit);
        }

        return gamma.toString();
    }

    protected static String epsilonRate(String gamma) {
        StringBuilder epsilon = new StringBuilder();

        for (char ch : gamma.toCharArray()) {
            switch (ch) {
                case '0' -> epsilon.append('1');
                case '1' -> epsilon.append('0');
                default -> throw new IllegalStateException("Can't resolve char: " + ch);
            }
        }

        return epsilon.toString();
    }

    private static int[] lineToPseudoBinsArr(String line) {
        return line.chars()
                .map(i -> {
                            return switch (i) {
                                case '0' -> 0;
                                case '1' -> 1;
                                default -> throw new IllegalStateException("Unexpected value: " + i);
                            };
                        }
                ).toArray();
    }


}
