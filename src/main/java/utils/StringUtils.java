package utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StringUtils {

    public static String sortChars(String str) {
        var arr = str.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    public static String remapString(String str, Map<Character, Character> map) {
        var o = str.toCharArray();
        var n = new char[o.length];

        for (int i = 0; i < o.length; i++) {
            n[i] = map.get(o[i]);
        }

        return new String(n);
    }

    public static List<String> stringPermutations(String str) {
        var chars = StreamUtils.toCharacterStream(str)
                .collect(Collectors.toList());

        var permutated = Permutations.withoutInitial(chars);

        return permutated.stream()
                .map(
                        charList -> charList.stream()
                                .map(ch -> Character.toString(ch))
                                .collect(Collectors.joining())
                )
                .collect(Collectors.toList());
    }

    public static Map<Character, Character> alterationsMap(String original, String permutated) {
//        assert original.length() == permutated.length();
        if (original.length() != permutated.length()) {
            throw new IllegalStateException("Strings must have equal lengths to produce a map!");
        }

        var o = original.toCharArray();
        var p = permutated.toCharArray();
        Map<Character, Character> map = new HashMap<>();

        for (int i = 0; i < o.length; i++) {
            map.put(p[i], o[i]);
        }

        return map;
    }
}
