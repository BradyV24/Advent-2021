package Advent2021.Day19;

import Advent2021.Day0.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day19 {
    static List<String> input = Input.parseDocString("resources/2021/day19");
    public static void part1() {
        List<List<List<Beacon>>> scanners = new ArrayList<>();
        Set<Beacon> beacons = new HashSet<>();
        for (int i = 0, line = 1; i < 40; i++, line+=2) {
            scanners.add(new ArrayList<>());
            List<List<Integer>> scanner = new ArrayList<>();
            while (line < input.size() && !input.get(line).equals("")){
                scanner.add(Arrays.stream(input.get(line).split(",")).map(x->Integer.parseInt(x)).toList());
                line++;
            }
            for (int j = 0; j < 24; j++) scanners.get(i).add(new ArrayList<>());
            if (i == 0) {
                for (List<Integer> point : scanner) {
                    beacons.add(new Beacon(point.get(0), point.get(1), point.get(2)));
                }
            }
            else for (List<Integer> point : scanner) {
                scanners.get(i).get(0).add(new Beacon(point.get(0),   point.get(1), point.get(2)));
                scanners.get(i).get(1).add(new Beacon(-point.get(1),  point.get(0), point.get(2)));
                scanners.get(i).get(2).add(new Beacon(-point.get(0),  -point.get(1), point.get(2)));
                scanners.get(i).get(3).add(new Beacon(point.get(1),   -point.get(0), point.get(2)));
                scanners.get(i).get(4).add(new Beacon(point.get(2),   point.get(0), point.get(1)));
                scanners.get(i).get(5).add(new Beacon(-point.get(0),  point.get(2), point.get(1)));
                scanners.get(i).get(6).add(new Beacon(-point.get(2),  -point.get(0), point.get(1)));
                scanners.get(i).get(7).add(new Beacon(point.get(0),   -point.get(2), point.get(1)));
                scanners.get(i).get(8).add(new Beacon(point.get(2),   -point.get(1), point.get(0)));
                scanners.get(i).get(9).add(new Beacon(point.get(1),   point.get(2), point.get(0)));
                scanners.get(i).get(10).add(new Beacon(-point.get(2), point.get(1), point.get(0)));
                scanners.get(i).get(11).add(new Beacon(-point.get(1), -point.get(2), point.get(0)));
                scanners.get(i).get(12).add(new Beacon(point.get(2),  point.get(1), -point.get(0)));
                scanners.get(i).get(13).add(new Beacon(-point.get(1), point.get(2), -point.get(0)));
                scanners.get(i).get(14).add(new Beacon(-point.get(2), -point.get(1), -point.get(0)));
                scanners.get(i).get(15).add(new Beacon(point.get(1),  -point.get(2), -point.get(0)));
                scanners.get(i).get(16).add(new Beacon(point.get(2),  -point.get(0), -point.get(1)));
                scanners.get(i).get(17).add(new Beacon(point.get(0),  point.get(2), -point.get(1)));
                scanners.get(i).get(18).add(new Beacon(-point.get(2), point.get(0), -point.get(1)));
                scanners.get(i).get(19).add(new Beacon(-point.get(0), -point.get(2), -point.get(1)));
                scanners.get(i).get(20).add(new Beacon(point.get(1),  point.get(0), -point.get(2)));
                scanners.get(i).get(21).add(new Beacon(-point.get(0), point.get(1), -point.get(2)));
                scanners.get(i).get(22).add(new Beacon(-point.get(1), -point.get(0), -point.get(2)));
                scanners.get(i).get(23).add(new Beacon(point.get(0),  -point.get(1), -point.get(2)));
            }
        }

        List<List<List<Beacon>>> unmatched = new ArrayList<>(scanners.subList(1, scanners.size()));
        List<Beacon> scannerLoc = new ArrayList<>();
        while (!unmatched.isEmpty()) {
            List<List<List<Beacon>>> nextUnmatched = new ArrayList<>(unmatched);
            for (List<List<Beacon>> scanner : unmatched) {
                boolean matched = false;
                List<Beacon> matchedOrientation = new ArrayList<>();
                Beacon loc = new Beacon(0,0,0);
                for (List<Beacon> orientation : scanner) {
                    for (Beacon beacon : beacons) {
                        for (Beacon point : orientation) {
                            int count = 0;
                            loc = new Beacon(beacon.x-point.x, beacon.y-point.y, beacon.z-point.z);
                            for (Beacon rpoint : orientation) {
                                Beacon apoint = new Beacon(loc.x+rpoint.x,loc.y+rpoint.y,loc.z+rpoint.z);
                                if (beacons.contains(apoint)) count++;
                                if (count >= 12) {
                                    matched = true;
                                    break;
                                }
                            }
                            if (matched) {
                                matchedOrientation = orientation;
                                break;
                            }
                        }
                        if (matched) break;
                    }
                    if (matched) break;
                }
                if (matched) {
                    for (Beacon rBeacon : matchedOrientation) {
                        beacons.add(new Beacon(loc.x+rBeacon.x,loc.y+rBeacon.y,loc.z+rBeacon.z));
                    }
                    scannerLoc.add(loc);
                    nextUnmatched.remove(scanner);
                }
            }
            unmatched=nextUnmatched;
        }
        System.out.println(beacons.size());
        int maxD = 0;
        for (Beacon scanner1 : scannerLoc) {
            for (Beacon scanner2 : scannerLoc) {
                maxD = Math.max(Math.abs(scanner1.x-scanner2.x) + Math.abs(scanner1.y-scanner2.y) + Math.abs(scanner1.z-scanner2.z), maxD);
            }
        }
        System.out.println(maxD);
    }
    public static void part2() {

    }
}
