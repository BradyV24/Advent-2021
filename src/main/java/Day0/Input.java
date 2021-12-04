package Day0;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {

    public static List<String> parseDocString(String fileName) {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            for (String fileLine = reader.readLine();
                 fileLine != null;
                 fileLine = reader.readLine()) {
                lines.add(fileLine);
            }
            reader.close();
        }
        catch (IOException ioe) {
            System.out.println("Problem reading file!");
        }
        return lines;
    }

    public static List<Integer> parseDocInt(String fileName) {
        List<Integer> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            for (String fileLine = reader.readLine();
                 fileLine != null;
                 fileLine = reader.readLine()) {
                lines.add(Integer.valueOf(fileLine));
            }
            reader.close();
        }
        catch (IOException ioe) {
            System.out.println("Problem reading file!");
        }
        return lines;
    }

    public static List<String> parseURL(URL url) {
        List<String> lines = new ArrayList<>();
        try {
            Scanner urlScanner = new Scanner(url.openStream());
            while (urlScanner.hasNext()) {
                System.out.println(urlScanner.nextLine());
            }
        } catch (IOException ioe) {
            System.out.println("Problem reading from URL!");
        }
    return lines;
    }
}
