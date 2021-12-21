package Advent2021.Day2;
import Advent2021.Day0.Input;
import java.util.List;
public class Day2 {
    static List<String> input = Input.parseDocString("resources/2021/day2");
    public static void day2_1() {
        int depth = 0;
        int x = 0;
        for (String move : input) {
            if (move.contains("forward")) x += Integer.valueOf(move.substring(move.length()-1));
            if (move.contains("up")) depth -= Integer.valueOf(move.substring(move.length()-1));
            if (move.contains("down")) depth += Integer.valueOf(move.substring(move.length()-1));
        }
        System.out.println(depth*x);
    }
    public static void day2_2() {
        int depth = 0;
        int x = 0;
        int aim = 0;
        for (String move : input) {
            if (move.contains("forward")) {
                x += Integer.valueOf(move.substring(move.length() - 1));
                depth += Integer.valueOf(move.substring(move.length() - 1)) * aim;
            }
            if (move.contains("up")) aim -= Integer.valueOf(move.substring(move.length()-1));
            if (move.contains("down")) aim += Integer.valueOf(move.substring(move.length()-1));
        }
        System.out.println(depth*x);
    }
}
