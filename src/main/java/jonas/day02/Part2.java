package jonas.day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Part2 {

    public static void main(String[] args) throws IOException {
        String input = Files.readString(Path.of("./src/main/resources/input_day02"));

        List<List<Integer>> reports = parseInput(input);

        long safeCount = reports.stream()
                .filter(Part2::isSafeWithDampener)
                .count();

        System.out.println("Safe reports (Part Two): " + safeCount);
    }

    private static List<List<Integer>> parseInput(String input) {
        List<List<Integer>> reports = new ArrayList<>();
        for (String line : input.split("\n")) {
            if (!line.trim().isEmpty()) {
                List<Integer> report = new ArrayList<>();
                for (String value : line.trim().split("\\s+")) {
                    report.add(Integer.parseInt(value));
                }
                reports.add(report);
            }
        }
        return reports;
    }

    private static boolean isSafeWithDampener(List<Integer> report) {
        if (isSafe(report)) {
            return true;
        }

        for (int i = 0; i < report.size(); i++) {
            List<Integer> modifiedReport = new ArrayList<>(report);
            modifiedReport.remove(i);
            if (isSafe(modifiedReport)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isSafe(List<Integer> report) {
        boolean isIncreasing = true;
        boolean isDecreasing = true;

        for (int i = 1; i < report.size(); i++) {
            int diff = report.get(i) - report.get(i - 1);

            if (diff < -3 || diff > 3 || diff == 0) {
                return false;
            }

            if (diff < 0) {
                isIncreasing = false;
            }
            if (diff > 0) {
                isDecreasing = false;
            }
        }


        return isIncreasing || isDecreasing;
    }
}
