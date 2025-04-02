import java.util.*;

public class StudentExamResultsAnalyzer {
    public static void main(String[] args) {
        HashMap<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 85);
        scores.put("Bob", 92);
        scores.put("Charlie", 78);

        // Sorting by name
        TreeMap<String, Integer> sortedScores = new TreeMap<>(scores);
        System.out.println("Sorted Scores: " + sortedScores);

        // Finding max and min scores
        int maxScore = Collections.max(scores.values());
        int minScore = Collections.min(scores.values());
        System.out.println("Highest Score: " + maxScore);
        System.out.println("Lowest Score: " + minScore);
    }
}
