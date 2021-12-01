package commons;

import java.util.Optional;
import java.util.Scanner;

public abstract class AocTask {

    private int numberOfDay;
    private int stage = 1;
    private Optional<Long> expected = Optional.empty();

    public AocTask(int numberOfDay) {
        this.numberOfDay = numberOfDay;
    }

    public AocTask(int numberOfDay, int stage, Optional<Long> expected) {
        this.numberOfDay = numberOfDay;
        this.stage = stage;
        this.expected = expected;
    }

    public abstract long getAnswer(Scanner scanner);

    public int getNumberOfDay() {
        return numberOfDay;
    }

    public int getStage() {
        return stage;
    }

    public Optional<Long> getExpected() {
        return expected;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public void setCorrectAnswer(long val) {
        expected = Optional.of(val);
    }
}
