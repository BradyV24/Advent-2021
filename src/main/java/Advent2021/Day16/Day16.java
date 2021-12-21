package Advent2021.Day16;

import Advent2021.Day0.Advent;
import Advent2021.Day0.Input;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Day16 {
    static String input = Input.parseDocString("resources/2021/day16").get(0);
    static int versionSum = 0;
    public static void day16() {
        Map<Character, String> hex2bin = new HashMap<>();
        hex2bin.put('0', "0000");
        hex2bin.put('1', "0001");
        hex2bin.put('2', "0010");
        hex2bin.put('3', "0011");
        hex2bin.put('4', "0100");
        hex2bin.put('5', "0101");
        hex2bin.put('6', "0110");
        hex2bin.put('7', "0111");
        hex2bin.put('8', "1000");
        hex2bin.put('9', "1001");
        hex2bin.put('A', "1010");
        hex2bin.put('B', "1011");
        hex2bin.put('C', "1100");
        hex2bin.put('D', "1101");
        hex2bin.put('E', "1110");
        hex2bin.put('F', "1111");

        String bin = new String();
        for (Character c : input.toCharArray())
            bin = bin + hex2bin.get(c);

        Stack<Long> values = new Stack<>();
        read(bin, values);
        System.out.println(versionSum);
        System.out.println(values.pop());
        Integer one = 1200;
        Integer two = 1200;
        System.out.println((one == two));
    }

    private static int read(String packet, Stack<Long> values) {
        versionSum += (int) Advent.binary(packet.substring(0,3));
        int type = (int) Advent.binary(packet.substring(3,6));
        int read = 6;
        if (type == 4) {
            String bin = new String();
            while (read < packet.length()) {
                bin = bin + packet.substring(read+1,read+5);
                read+=5;
                if (packet.charAt(read-5) == '0') break;
            }
            values.push(Advent.binary(bin));
            return read;
        }

        int subpackets = 0;
        if (packet.charAt(6) == '0') {
            int length = 22 + (int) Advent.binary(packet.substring(7,22));
            for (read = 22; read < length; read += read(packet.substring(read, length), values), subpackets++);
        } else {
            subpackets = (int) Advent.binary(packet.substring(7,18));
            read = 18;
            for (int i = 0; i < subpackets; i++, read += read(packet.substring(read), values));
        }

        switch(type) {
            case 0:
                long sum = 0;
                for (int i = 0; i < subpackets; i++)
                    sum += values.pop();
                values.push(sum);
                break;
            case 1:
                long prod = 1;
                for (int i = 0; i < subpackets; i++)
                    prod *= values.pop();
                values.push(prod);
                break;
            case 2:
                long min = 999999999999999999L;
                for (int i = 0; i < subpackets; i++)
                    min = Math.min(values.pop(), min);
                values.push(min);
                break;
            case 3:
                long max = 0;
                for (int i = 0; i < subpackets; i++)
                    max = Math.max(values.pop(), max);
                values.push(max);
                break;
            case 5:
                values.push((values.pop() < values.pop()) ? 1L:0L);
                break;
            case 6:
                values.push((values.pop() > values.pop()) ? 1L:0L);
                break;
            case 7:
                values.push((values.pop().equals(values.pop())) ? 1L:0L);
        }
        return read;
    }
}
