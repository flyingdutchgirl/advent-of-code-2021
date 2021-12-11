package tasks.day8;

import commons.TaskUtils;
import utils.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class DisplayReconstruction extends Displays {

    private final String PATTERN_STR = "abcdefg";
    private final List<String> digits =
            List.of("abcefg", "cf", "acdeg", "acdfg", "bcdf", "abdfg", "abdefg", "acf", "abcdefg", "abcdfg");
    private final Set<String> stringSet = Set.copyOf(digits);
    private final List<String> permutations = StringUtils.stringPermutations(PATTERN_STR);


    public DisplayReconstruction() {
        setStage(2);
        setCorrectAnswer(936117);
    }

    public static void main(String[] args) {
//        TaskUtils.fromConsole(new DisplayReconstruction());
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
                .map(digits::indexOf)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }


    private Map<Character, Character> findCorrectRemapping(List<String> puzzleInput) {
        var maps = permutations.stream()
                .map(p -> StringUtils.alterationsMap(PATTERN_STR, p))
                .collect(Collectors.toList());

        for (Map<Character, Character> map : maps) {
            var set = puzzleInput.stream()
                    .map(s -> StringUtils.remapString(s, map))
                    .map(StringUtils::sortChars)
                    .collect(Collectors.toSet());

            if (this.stringSet.equals(set)) {
                return map;
            }
        }

        throw new IllegalStateException("Cannot find the right remapping!");
//        return null;
    }

}
