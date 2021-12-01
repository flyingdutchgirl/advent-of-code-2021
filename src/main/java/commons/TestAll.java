package commons;

import tasks.day1.Depths;
import tasks.day1.SumOfDepths;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestAll {

    private static final List<AocTask> allTasks =
            List.of(
                    // day 1
                    new Depths(),
                    new SumOfDepths()

                    // day 2
                    // ...
            );


    public static void main(String[] args) {
        testAll();
    }

    static void testAll() {
        allTasks.stream()
                .sorted(Comparator.comparingInt(AocTask::getNumberOfDay).thenComparing(AocTask::getStage))
                .forEachOrdered(TaskUtils::checkAssertions);
    }



}
