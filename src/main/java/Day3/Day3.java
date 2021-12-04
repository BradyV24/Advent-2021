package Day3;
import Day0.Input;
import java.util.ArrayList;
import java.util.List;
public class Day3 {
    static List<String> input = Input.parseDocString("resources/2021/day3");
    public static void day3_1() {
        List<Integer> mostCommon = new ArrayList<>(mostCommon(input));
        int gamma = 0;
        int epsilon = 0;
        for (int i = 0; i < 12; i++) {
            if (mostCommon.get(i) == 1)
                gamma += Math.pow(2, 11-i);
            else
                epsilon += Math.pow(2, 11-i);
        }
        System.out.println(gamma*epsilon);
    }
    public static void day3_2() {
        List<String> inputMod = new ArrayList<>(input);

        for (int i = 0; i < 12 && inputMod.size() > 1; i++) {
            List<String> next = new ArrayList<>(inputMod);
            List<Integer> arr = new ArrayList<>(mostCommon(inputMod));
            for (String string : inputMod) {
                if (string.charAt(i) - '0' != arr.get(i))
                    next.remove(string);
                if (next.size() == 1) break;
            }
            inputMod = new ArrayList<>(next);
        }
        String O2 = inputMod.get(0);

        inputMod = new ArrayList<>(input);
        for (int i = 0; i < 12 && inputMod.size() > 1; i++) {
            List<String> next = new ArrayList<>(inputMod);
            List<Integer> arr = new ArrayList<>(leastCommon(inputMod));
            for (String string : inputMod) {
                if (string.charAt(i) - '0' != arr.get(i))
                    next.remove(string);
                if (next.size() == 1) break;
            }
            inputMod = new ArrayList<>(next);
        }
        String CO2 = inputMod.get(0);

        int CO2int = 0;
        int O2int = 0;
        for (int i = 0; i < 12; i++) {
            if (O2.charAt(i) - 48 == 1)
                O2int += Math.pow(2, 11-i);
            if (CO2.charAt(i) - 48 == 1)
                CO2int += Math.pow(2, 11-i);
        }

        System.out.println(CO2int*O2int);
    }

    private static List<Integer> mostCommon(List<String> input) {
        int[] arr = new int[12];
        List<Integer> mC = new ArrayList<>();
        for (String string : input) {
            for (int i = 0; i < 12; i++)
                arr[i] += string.charAt(i) - '0';
        }
        for (int i = 0; i < 12; i++) {
            if (arr[i] >= input.size() - arr[i]) mC.add(1);
            else mC.add(0);
        }
        return mC;
    }

    private static List<Integer> leastCommon(List<String> input) {
        return new ArrayList<>(mostCommon(input)).stream().map(x -> Math.abs(x-1)).toList();
    }
}
