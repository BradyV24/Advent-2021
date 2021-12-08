package Day8;

import Day0.Input;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day8 {
    static List<String> input = Input.parseDocString("resources/2021/day8");
    public static void day8_1() {
        List<List<String[]>> patterns = input.stream().map(x -> Arrays.stream(x.split(" \\| ")).map(y -> y.split(" ")).toList()).toList();
        int count = 0;
        for (List<String[]> pattern : patterns) {
            for (String digit : pattern.get(1))
                if (digit.length() == 2 || digit.length() == 4 || digit.length() == 3 || digit.length() == 7) count++;
        }
        System.out.println(count);
    }
    public static void day8_2() {
        List<List<List<HashSet<Character>>>> patterns = input.stream().map(
            x -> Arrays.stream(x.split(" \\| ")).map(y -> Arrays.stream(y.split(" ")).map(
                z -> new HashSet<>(z.chars().mapToObj(e -> (char) e).collect(Collectors.toSet())))
                .toList()).toList()).toList();
        Map<Integer, Set<Character>> unique = new HashMap<>();

        int total = 0;
        for (List<List<HashSet<Character>>> pattern : patterns) {
            unique.put(1, new HashSet<>());
            unique.put(4, new HashSet<>());
            unique.put(7, new HashSet<>());
            unique.put(8, new HashSet<>());
            for (List<HashSet<Character>> part : pattern) {
                for (HashSet<Character> digit : part) {
                    if (digit.size() == 2) unique.get(1).addAll(digit);
                    else if (digit.size() == 4) unique.get(4).addAll(digit);
                    else if (digit.size() == 3) unique.get(7).addAll(digit);
                    else if (digit.size() == 7) unique.get(8).addAll(digit);
                }
            }

            String number = "";
            for (HashSet<Character> digit : pattern.get(1)) {
                if (digit.size() == 7) number = number + "8";
                else if (digit.containsAll(unique.get(4))) {
                    if (digit.size() == 6) number = number + "9";
                    else number = number + "4";
                } else if (digit.containsAll(unique.get(7))) {
                    if (digit.size() == 6) number = number + "0";
                    else if (digit.size() == 5) number = number + "3";
                    else number = number + "7";
                } else if (digit.containsAll(unique.get(1))) number = number + "1";
                else if (digit.size() == 6) number = number + "6";
                else {
                    Set<Character> subset1 = new HashSet<>(unique.get(4));
                    Set<Character> subset2 = new HashSet<>(unique.get(4));
                    subset1.remove(unique.get(1).stream().toList().get(0));
                    subset2.remove(unique.get(1).stream().toList().get(1));
                    if (digit.containsAll(subset1) || digit.containsAll(subset2)) {
                        number = number + "5";
                    } else number = number + "2";
                }
            }
            total += Integer.parseInt(number);
        }
        System.out.println(total);
    }
}
