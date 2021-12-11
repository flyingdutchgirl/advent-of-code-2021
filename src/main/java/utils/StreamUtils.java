package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class StreamUtils {

    public static Stream<Character> toCharacterStream(char[] arr) {
        List<Character> characters = new ArrayList<>(arr.length);
        for (char c : arr) {
            characters.add(c);
        }
        return characters.stream();
    }

    public static Stream<Character> toCharacterStream(String str) {
        return toCharacterStream(str.toCharArray());
    }


    public static <T> Predicate<T> not(Predicate<T> predicate) {
        return predicate.negate();
    }

    // prototype - may be unstable when array content changes!
    private static Stream<Character> characterStream(final char[] arr) {
        Supplier<Character> supplier = new Supplier<>() {
            private int i = 0;
            @Override
            public Character get() {
                return arr[i++];
            }
        };

        return Stream.generate(supplier)
                .limit(arr.length);
    }



    }
