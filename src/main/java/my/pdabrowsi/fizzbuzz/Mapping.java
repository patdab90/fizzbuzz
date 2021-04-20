package my.pdabrowsi.fizzbuzz;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class Mapping implements Comparable<Mapping> {
    int from;
    String to;

    static Mapping of(int from, String to) {
        if (from <= 1) {
            throw new IllegalArgumentException("'From' can not be smaller then 2.");
        }
        if (Objects.requireNonNull(to).isBlank()) {
            throw new IllegalArgumentException("'To' can not be empty.");
        }
        return new Mapping(from, to);
    }

    static Mapping of(List<Mapping> resultMappings) {
        return resultMappings.stream()
                .sorted(Comparator.comparingInt(a -> a.from))
                .reduce(empty(), Mapping::sum);
    }

    private static Mapping empty() {
        return new Mapping(1, "");
    }

    Stream<String> map(Integer number) {
        return number % this.from == 0
                ? Stream.of(this.to)
                : Stream.empty();
    }

    private Mapping sum(Mapping other) {
        return new Mapping(this.from * other.from, this.to + other.to);
    }

    @Override
    public String toString() {
        return "" + from + "->" + to;
    }

    @Override
    public int compareTo(Mapping o) {
        return compareDesc(this, o);
    }

    static int compareDesc(Mapping a, Mapping b) {
        return b.from - a.from;
    }
}
