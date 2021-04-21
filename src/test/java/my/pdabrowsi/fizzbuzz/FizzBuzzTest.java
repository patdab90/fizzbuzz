package my.pdabrowsi.fizzbuzz;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class FizzBuzzTest {

    @Test
    public void classicFizzBuzz() {
        //when
        FizzBuzz subject = FizzBuzz.classic();
        List<String> result = subject.calculate(1, 21);

        //then
        assertThat(result)
                .hasSize(20)
                .isEqualTo(List.of("1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz", "16", "17", "Fizz", "19", "Buzz"));
    }

    @Test
    public void fizzBuzzBizz() {
        //when
        FizzBuzz subject = FizzBuzz.of(Mapping.of(3, "Fizz"), Mapping.of(5, "Buzz"), Mapping.of(7, "Bizz"));
        List<String> result = subject.calculate(1, 22);

        //then
        assertThat(result)
                .hasSize(21)
                .isEqualTo(List.of("1", "2", "Fizz", "4", "Buzz", "Fizz", "Bizz", "8", "Fizz", "Buzz", "11", "Fizz", "13", "Bizz", "FizzBuzz", "16", "17", "Fizz", "19", "Buzz", "FizzBizz"));
    }

    @Test
    public void classicFizzBuzzWithNotSortedMappings() {
        //when
        FizzBuzz subject = FizzBuzz.of(Mapping.of(5, "Buzz"), Mapping.of(3, "Fizz"));
        List<String> result = subject.calculate(1, 16);

        //then
        assertThat(result)
                .hasSize(15)
                .isEqualTo(List.of("1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz"));
    }

    @Test
    public void withManyMappings() {
        //when
        FizzBuzz subject = FizzBuzz.of(Mapping.of(5, "Buzz"), Mapping.of(3, "Fizz"), Mapping.of(7, "Bizz"), Mapping.of(11, "Gizz"));
        List<String> result = subject.calculate(1, 21);

        //then
        assertThat(result)
                .hasSize(20)
                .isEqualTo(List.of("1", "2", "Fizz", "4", "Buzz", "Fizz", "Bizz", "8", "Fizz", "Buzz", "Gizz", "Fizz", "13", "Bizz", "FizzBuzz", "16", "17", "Fizz", "19", "Buzz"));
    }

}
