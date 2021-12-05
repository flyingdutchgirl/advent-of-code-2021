package utils.containers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;

public class IterationUtils {

    public static <T> List<Pair<T, T>> findPairsThat(BiPredicate<T, T> pred,
                                                     List<T> list) {
        Set<Pair<T,T>> pairs = new HashSet<>();

        for (int i = 0; i < list.size(); i++) {
            for (int k = 0; k < list.size(); k++) {
                if (i == k) {
                    continue;
                } else if (pred.test(list.get(i), list.get(k))) {
                    pairs.add(Pair.of(list.get(i), list.get(k)));
                }
            }
        }

        return new ArrayList<>(pairs);
    }


}
