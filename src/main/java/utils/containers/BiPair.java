package utils.containers;

import java.util.function.BiFunction;
import java.util.function.Function;

public class BiPair<T> {

    private T first;
    private T second;

    public BiPair(T first, T second) {
        this.first = first;
        this.second = second;
    }


    public static <X> BiPair<X> of(X a, X b) {
        return new BiPair<>(a, b);
    }

    public <X, Y> BiPair<Y> combineWith(BiPair<X> anotherPair, BiFunction<T, X, Y> combiner) {
        return BiPair.of(combiner.apply(fst(), anotherPair.fst()), combiner.apply(snd(), anotherPair.snd()));
    }

    public T fst() {
        return first;
    }

    public T snd() {
        return second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return String.format("BiPair(%s, %s)",
                first.toString(), second.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BiPair<?> biPair = (BiPair<?>) o;

        if (getFirst() != null ? !getFirst().equals(biPair.getFirst()) : biPair.getFirst() != null) return false;
        return getSecond() != null ? getSecond().equals(biPair.getSecond()) : biPair.getSecond() == null;
    }

    @Override
    public int hashCode() {
        int result = getFirst() != null ? getFirst().hashCode() : 0;
        result = 31 * result + (getSecond() != null ? getSecond().hashCode() : 0);
        return result;
    }
}
