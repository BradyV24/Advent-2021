package Advent2021.Day0;

import java.util.List;

public class Advent {
    public static long binary(String bin) {
        long dec = 0;
        for (int i = 0; i < bin.length(); i++) {
            if (bin.charAt(i) == '1')
                dec += Math.pow(2, bin.length()-1-i);
        }
        return dec;
    }
    public static int binary(int[] bin) {
        int dec = 0;
        for (int i = 1; i <= bin.length; i++) {
            if (bin[i] == 1)
                dec += Math.pow(2, bin.length-i);
        }
        return dec;
    }
    public static int binary(List<Integer> bin) {
        int dec = 0;
        for (int i = 1; i <= bin.size(); i++) {
            if (bin.get(i) == 1)
                dec += Math.pow(2, bin.size()-i);
        }
        return dec;
    }
    public static int binary(boolean[] bin) {
        int dec = 0;
        for (int i = 1; i <= bin.length; i++) {
            if (bin[i-1])
                dec += Math.pow(2, bin.length-i);
        }
        return dec;
    }
}
