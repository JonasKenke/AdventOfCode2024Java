package jonas.day01;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String filePath = "./src/main/resources/input_day01";

        try {
            List<Integer> leftList = new ArrayList<>();
            List<Integer> rightList = new ArrayList<>();
            parseInputFile(filePath, leftList, rightList);

            int totalDistance = calculateTotalDistance(leftList, rightList);
            System.out.println("Total Distance: " + totalDistance);

        } catch (IOException e) {
            System.err.println("Error reading the input_day01 file: " + e.getMessage());
        }
    }

    public static void parseInputFile(String filePath, List<Integer> leftList, List<Integer> rightList) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 2) {
                    leftList.add(Integer.parseInt(parts[0]));
                    rightList.add(Integer.parseInt(parts[1]));
                }
            }
        }
    }

    public static int calculateTotalDistance(List<Integer> leftList, List<Integer> rightList) {
        int[] leftArray = leftList.stream().mapToInt(Integer::intValue).toArray();
        int[] rightArray = rightList.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(leftArray);
        Arrays.sort(rightArray);

        int totalDistance = 0;
        for (int i = 0; i < leftArray.length; i++) {
            totalDistance += Math.abs(leftArray[i] - rightArray[i]);
        }

        return totalDistance;
    }
}
