package utils;

import java.util.List;
import java.util.Objects;

public class ListUtils {

    public static <T> T lastElement(List<T> list) {
        return list.size() > 0
                ? list.get(list.size() - 1)
                : null;
    }

    public static <T> boolean lastEquals(List<T> list, Object obj) {
        return Objects.equals(lastElement(list), obj);
    }

}
