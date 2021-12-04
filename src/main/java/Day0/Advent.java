package Day0;

import java.util.List;

public class Advent {
    public int binary(String bin) {
        int dec = 0;
        for (int i = 1; i <= bin.length(); i++) {
            if (bin.charAt(i) - '0' == 1)
                dec += Math.pow(2, bin.length()-i);
        }
        return dec;
    }
    public int binary(int[] bin) {
        int dec = 0;
        for (int i = 1; i <= bin.length; i++) {
            if (bin[i] == 1)
                dec += Math.pow(2, bin.length-i);
        }
        return dec;
    }
    public int binary(List<Integer> bin) {
        int dec = 0;
        for (int i = 1; i <= bin.size(); i++) {
            if (bin.get(i) == 1)
                dec += Math.pow(2, bin.size()-i);
        }
        return dec;
    }
}
