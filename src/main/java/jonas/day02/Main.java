package jonas.day02;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String filePath = "./src/main/resources/input_day02";

        try {
            List<List<Integer>> reports = parseInputFile(filePath);

            int safeReportsCount = countSafeReports(reports);
            System.out.println("Number of Safe Reports: " + safeReportsCount);

        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
        }
    }

    public static List<List<Integer>> parseInputFile(String filePath) throws IOException {
        List<List<Integer>> reports = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Integer> report = new ArrayList<>();
                for (String num : line.trim().split("\\s+")) {
                    report.add(Integer.parseInt(num));
                }
                reports.add(report);
            }
        }
        return reports;
    }

    public static int countSafeReports(List<List<Integer>> reports) {
        int safeCount = 0;

        for (List<Integer> report : reports) {
            if (isSafeReport(report)) {
                safeCount++;
            }
        }

        return safeCount;
    }

    public static boolean isSafeReport(List<Integer> report) {
        if (report.size() < 2) {
            return true;
        }

        boolean isIncreasing = report.get(1) > report.get(0);
        for (int i = 1; i < report.size(); i++) {
            int diff = report.get(i) - report.get(i - 1);

            if (diff < -3 || diff > 3 || diff == 0) {
                return false;
            }

            if ((isIncreasing && diff < 0) || (!isIncreasing && diff > 0)) {
                return false;
            }
        }

        return true;
    }
}
