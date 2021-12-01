package experiments;

import com.diogonunes.jcdp.color.api.Ansi;
import commons.TaskUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.GregorianCalendar;
import java.util.Scanner;

import com.diogonunes.jcdp.color.*;

public class Files {

    public static void main(String[] args) {
        System.out.println("fgjieowoarhfjkwl'ejf");
        pathsExperiments();
        colors();
    }

    static void colors() {
        ColoredPrinter coloredPrinter =
                new ColoredPrinter.Builder(1, false).build();

        coloredPrinter.println("Hello wrld!!!", Ansi.Attribute.CLEAR, Ansi.FColor.GREEN, Ansi.BColor.RED);


        ColoredPrinter printer = new ColoredPrinter
                .Builder(1, false).build();

        printer.print("Hello, colorful world!",
                Ansi.Attribute.BOLD, Ansi.FColor.BLUE, Ansi.BColor.WHITE);
    }

    static void pathsExperiments() {
        String path = "src/inputs/input1.txt";

        try (Scanner filesc = new Scanner(new File(path))) {
            TaskUtils.lines(filesc)
                    .limit(5)
                    .forEachOrdered(System.out::println);
        } catch (FileNotFoundException e) {}

    }

}
