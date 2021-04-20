package my.pdabrowsi.fizzbuzz;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Combination {
    static List<Mapping> toCombinations(List<Mapping> input) {
        List<Mapping> sortedInput = sort(input).collect(toList());
        int inputSize = sortedInput.size();
        List<Mapping> result = new ArrayList<>(sortedInput);
        for (int combinationSize = 2; combinationSize <= inputSize; combinationSize++) {
            for (int[] index : generateOfSize(combinationSize, inputSize)) {
                List<Mapping> resultMappings = mappingsToCombine(sortedInput, combinationSize, index);
                result.add(Mapping.of(resultMappings));
            }

        }
        return sort(result).collect(Collectors.toUnmodifiableList());
    }

    private static Stream<Mapping> sort(List<Mapping> mappings) {
        return mappings.stream()
                .sorted(Mapping::compareTo);
    }

    private static List<Mapping> mappingsToCombine(List<Mapping> input, int combinationSize, int[] index) {
        return IntStream.range(0, combinationSize)
                .mapToObj(j -> input.get(index[j]))
                .collect(Collectors.toList());
    }

    private static List<int[]> generateOfSize(int combinationSize, int inputSize) {
        int[] cur = new int[combinationSize];
        List<int[]> all = new ArrayList<>();

        // 1-st step - init
        for (int i = 0; i < combinationSize; i++) {
            cur[i] = i;
        }

        // 2-nd step - do shifts
        do {
            int[] copy = new int[combinationSize];
            System.arraycopy(cur, 0, copy, 0, cur.length);
            all.add(copy);
        } while (doShift(cur, combinationSize, inputSize));

        return all;
    }

    private static boolean doShift(int[] result, int combinationSize, int inputSize) {
        if (result[combinationSize - 1] < inputSize - 1) {
            result[combinationSize - 1] = result[combinationSize - 1] + 1;
            return true;
        }
        for (int i = combinationSize - 1; i > 0; i--) {
            if (result[i] - result[i - 1] > 1) {
                result[i - 1] = result[i - 1] + 1;
                for (int j = i; j < combinationSize; j++) {
                    result[j] = result[j - 1] + 1;
                }
                return true;
            }
        }
        return false;
    }

}

