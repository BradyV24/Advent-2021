package Day1;
import Day0.Input;
import java.util.List;
public class Day1 {
    static List<Integer> input = Input.parseDocInt("resources/2021/day1");
    public static void day1_1() {
        int increases = 0;
        for (int i = 1; i < input.size(); i++) if (input.get(i) > input.get(i-1)) increases++;
        System.out.println(increases);
    }
    public static void day1_2() {
        int increases = 0;
        for (int i = 1; i < input.size()-2; i++) if (input.get(i+2) > input.get(i-1)) increases++;
        System.out.println(increases);
    }
}
