package commons;

import tasks.day1.Depths;
import tasks.day1.SumOfDepths;
import tasks.day10.SyntaxCheck;
import tasks.day10.SyntaxCompletion;
import tasks.day13.DotsManual;
import tasks.day13.DotsToLetters;
import tasks.day2.AimedSubmarineSteering;
import tasks.day2.SubmarineSteering;
import tasks.day3.LifeSupportRating;
import tasks.day3.PowerConsumption;
import tasks.day4.BingoLosing;
import tasks.day4.BingoRunner;
import tasks.day5.HydrothermalDiagonals;
import tasks.day5.HydrothermalRoute;
import tasks.day6.FishPopulation;
import tasks.day6.HugeFishPopulation;
import tasks.day7.CrabCommonPoint;
import tasks.day7.OptimalCommonPoint;
import tasks.day8.DisplayReconstruction;
import tasks.day8.Displays;
import tasks.day9.Basins;
import tasks.day9.RiskPoints;

import java.util.Comparator;
import java.util.List;

public class TestAll {

    public static void main(String[] args) {
        new TestAll().testAll();
    }


    private final List<AocTask> allTasks =
            List.of(
                    // day 1
                    new Depths(),
                    new SumOfDepths(),

                    // day 2
                    new SubmarineSteering(),
                    new AimedSubmarineSteering(),

                    // day 3
                    new PowerConsumption(),
                    new LifeSupportRating(),

                    // day 4
                    new BingoRunner(),
                    new BingoLosing(),

                    // day 5
                    new HydrothermalRoute(),
                    new HydrothermalDiagonals(),

                    //day 6
                    new FishPopulation(),
                    new HugeFishPopulation(),

                    // day 7
                    new OptimalCommonPoint(),
                    new CrabCommonPoint(),

                    //day 8
                    new Displays(),
                    new DisplayReconstruction(),

                    // day 9
                    new RiskPoints(),
                    new Basins(),

                    // day 10
                    new SyntaxCheck(),
                    new SyntaxCompletion(),

                    //day 11

                    // day 12

                    // day 13
                    new DotsManual(),
                    new DotsToLetters()
            );


    void testAll() {
        allTasks.stream()
                .sorted(Comparator.comparingInt(AocTask::getNumberOfDay).thenComparing(AocTask::getStage))
                .forEachOrdered(TaskUtils::checkAssertions);
    }


}
