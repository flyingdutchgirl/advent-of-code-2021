package tasks.day13;

import commons.TaskUtils;
import utils.containers.Pair;

import java.util.Scanner;
import java.util.function.Predicate;

public class DotsToLetters extends DotsManual {

    public DotsToLetters() {
        setStage(2);

        // the answer is a word, but hashcode is checked as a test
        // word answer is EFJKZLBL
        setCorrectAnswer(-458528783);
    }

    public static void main(String[] args) {
        TaskUtils.checkAssertions(new DotsToLetters());
    }

    @Override
    public long getAnswer(Scanner scanner) {
        init(scanner);

        stageTwo();

        var text = super.pointGrid(super.foldedPoints);

        boolean print = false;

        if (print) {
            text.lines()
                    .takeWhile(Predicate.not(String::isBlank))
                    .forEachOrdered(System.out::println);

        }
        return text.hashCode();
    }

    private void stageTwo() {
        this.foldedPoints = this.points;

        for (Pair<Character, Integer> cmd : this.commands) {

            foldedPoints = cmd.fst() == axisX
                    ? calculateX(cmd.snd(), foldedPoints)
                    : calculateY(cmd.snd(), foldedPoints);
        }

    }


}
