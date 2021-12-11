package tasks.day8;

import commons.TaskUtils;
import utils.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class DisplayReconstruction extends Displays {

    private final String ALL_DIGITS = "abcdefg";
    private final List<String> DIGITS =
            List.of("abcefg", // 0
                    "cf", // 1
                    "acdeg", // 2
                    "acdfg", // 3
                    "bcdf", // 4
                    "abdfg", // 5
                    "abdefg", // 6
                    "acf", // 7
                    "abcdefg", // 8
                    "abcdfg"); // 9
    private final Set<String> DIGIT_SET = Set.copyOf(DIGITS);
    private final List<String> ALL_PERMUTATIONS = StringUtils.stringPermutations(ALL_DIGITS);


    public DisplayReconstruction() {
        setStage(2);
        setCorrectAnswer(936117);
    }

    public static void main(String[] args) {
        TaskUtils.checkAssertions(new DisplayReconstruction());
    }

    @Override
    public long getAnswer(Scanner scanner) {
        return TaskUtils.lines(scanner)
                .map(this::parseLine)
                .mapToLong(Long::parseLong)
                .sum();
    }

    private String parseLine(String line) {
        var parts = line.split("\\|");

        var changedDigits = Arrays.stream(parts[0].split("\\s+"))
                .map(String::trim)
                .collect(Collectors.toList());

        var letterAlterations = findCorrectRemapping(changedDigits);

        return Arrays.stream(parts[1].trim().split("\\s+"))
                .map(s -> StringUtils.remapString(s, letterAlterations))
                .map(StringUtils::sortChars)
                .map(DIGITS::indexOf)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }


    private Map<Character, Character> findCorrectRemapping(List<String> puzzleInput) {
        var maps = ALL_PERMUTATIONS.stream()
                .map(p -> StringUtils.alterationsMap(ALL_DIGITS, p))
                .collect(Collectors.toList());

        for (Map<Character, Character> map : maps) {
            var set = puzzleInput.stream()
                    .map(s -> StringUtils.remapString(s, map))
                    .map(StringUtils::sortChars)
                    .collect(Collectors.toSet());

            if (this.DIGIT_SET.equals(set)) {
                return map;
            }
        }

        throw new IllegalStateException("Cannot find the right remapping!");
//        return null;
    }

}
