package utils.containers;

import java.util.function.BiFunction;

public class Pair<A, B> {
    private final A first;
    private final B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public static <X, Y> Pair<X, Y> of(X first, Y second) {
        return new Pair<>(first, second);
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    public A fst() {
        return first;
    }

    public B snd() {
        return second;
    }

    public Pair<A, B> combineWith(Pair<A, B> anotherPair,
                                  BiFunction<A,A,A> combinerA,
                                  BiFunction<B,B,B> combinerB) {
        return Pair.of(
                combinerA.apply(fst(), anotherPair.fst()),
                combinerB.apply(snd(), anotherPair.snd())
        );
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Pair<>(fst(), snd());
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]", fst().toString(), snd().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (getFirst() != null ? !getFirst().equals(pair.getFirst()) : pair.getFirst() != null) return false;
        return getSecond() != null ? getSecond().equals(pair.getSecond()) : pair.getSecond() == null;
    }

    public boolean equalsIgnoreOrder(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;

        // Pair(X, Y) == Pair(Y, X)
        if (pair.fst().equals(snd()) && pair.snd().equals(fst())) {
            return true;
        }

        return equals(o);
    }

//    @Override
//    public int hashCode() {
//        return getFirst().hashCode() + getSecond().hashCode();
//    }


    @Override
    public int hashCode() {
        int result = getFirst() != null ? getFirst().hashCode() : 0;
        result = 31 * result + (getSecond() != null ? getSecond().hashCode() : 0);
        return result;
    }
}
