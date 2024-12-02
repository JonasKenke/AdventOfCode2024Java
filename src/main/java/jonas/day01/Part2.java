package jonas.day01;

import java.io.*;
import java.util.*;

public class Part2 {
    public static void main(String[] args) {
        String filePath = "./src/main/resources/input_day01";

        try {
            List<Integer> leftList = new ArrayList<>();
            List<Integer> rightList = new ArrayList<>();
            parseInputFile(filePath, leftList, rightList);
            
            int similarityScore = calculateSimilarityScore(leftList, rightList);
            System.out.println("Similarity Score: " + similarityScore);

        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
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

    public static int calculateSimilarityScore(List<Integer> leftList, List<Integer> rightList) {
        Map<Integer, Integer> rightFrequencyMap = new HashMap<>();
        for (int num : rightList) {
            rightFrequencyMap.put(num, rightFrequencyMap.getOrDefault(num, 0) + 1);
        }

        int similarityScore = 0;
        for (int num : leftList) {
            int frequency = rightFrequencyMap.getOrDefault(num, 0);
            similarityScore += num * frequency;
        }

        return similarityScore;
    }
}
