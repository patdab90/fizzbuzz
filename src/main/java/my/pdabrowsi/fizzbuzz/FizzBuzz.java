package my.pdabrowsi.fizzbuzz;

import lombok.Value;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Value
public class FizzBuzz {

    List<Function<Integer, Stream<String>>> mappings;

    public static FizzBuzz of(List<Mapping> mappings) {
        return new FizzBuzz(Combination.toCombinations(mappings).stream()
                .peek(System.out::println)
                .map(FizzBuzz::asMapFunction)
                .collect(Collectors.toList()));
    }

    public static FizzBuzz of(Mapping... mappings) {
        return of(List.of(mappings));
    }

    public static FizzBuzz classic() {
        return FizzBuzz.of(Mapping.of(3, "Fizz"), Mapping.of(5, "Buzz"));
    }

    private static Function<Integer, Stream<String>> asMapFunction(Mapping it) {
        return it::map;
    }

    public List<String> calculate(int startInclusive, int endExclusive) {
        return IntStream.range(startInclusive, endExclusive)
                .mapToObj(this::mapIt)
                .collect(toList());
    }

    private String mapIt(int number) {
        return mappings.stream()
                .flatMap(it -> it.apply(number))
                .findFirst()
                .orElse("" + number);
    }

}
