package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Permutations {

    public static <T> List<List<T>> withoutInitial(List<T> elements) {
        var list = permutation(elements);
        list.remove(elements);
        return list;
    }


    public static <T> List<List<T>> permutation(List<T> elements) {
        if (elements.size() == 1) {
            var levelOne = new ArrayList<T>();
            levelOne.add(elements.get(0));
            var levelTwo = new ArrayList<List<T>>();
            levelTwo.add(levelOne);
            return levelTwo;
//            return List.of(List.of(elements.get(0)));
        }

//        final int initLen = elements.size() - 1;

        var listList = elements.stream()
                .map(elem -> {
                    List<T> newList = new ArrayList<>(elements);
                    newList.remove(elem);
                    var recursive = permutation(newList);
                    var perms = recursive.stream()
                            .map(list -> {
                                list.add(0, elem);
                                return list;
                            })
                            .collect(Collectors.toList());
                    return new ArrayList<>(perms);
                }).flatMap(Collection::stream)
                .collect(Collectors.toList());

        return new ArrayList<>(listList);
    }


}
