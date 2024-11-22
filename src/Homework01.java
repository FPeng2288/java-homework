import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName: Homework01
 * Package: PACKAGE_NAME
 * Description:
 *
 * @author Fan Peng
 * Create 2024/11/21 21:51
 * @version 1.0
 */

public class Homework01 {

    // Find the first non-repeated character in a string
    public int firstUniqChar(String s) {
        Map<Character, Long> frequencyMap = s.chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(
                        ch -> ch,
                        Collectors.counting()
                ));

        return s.chars()
                .mapToObj(ch -> (char) ch)
                .map(ch -> new AbstractMap.SimpleEntry<>(ch, frequencyMap.get(ch)))
                .filter(entry -> entry.getValue() == 1)
                .map(entry -> s.indexOf(entry.getKey()))
                .findFirst()
                .orElse(-1);
    }


    // Find the top k frequent words in a string array
    public List<String> topKFrequent(String[] words, int k) {
        return Arrays.stream(words)
                .collect(Collectors.groupingBy(
                        word -> word,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }


    // Find the sum of unique elements in an array
    public int sumOfUnique(int[] nums) {
        return Arrays.stream(nums)
                .boxed()
                .collect(Collectors.groupingBy(
                        num -> num,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .mapToInt(Map.Entry::getKey)
                .sum();
    }

}
