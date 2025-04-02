public class GameOfThrones {
    public String gameOfThrones(String s) {
        // Step 1: Count frequency of each character
        int[] freq = new int[26]; // Assuming lowercase English letters
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        
        // Step 2: Count how many characters have odd frequency
        int oddCount = 0;
        for (int f : freq) {
            if (f % 2 != 0) {
                oddCount++;
            }
        }
        
        // Step 3: If oddCount <= 1, the string can be rearranged into a palindrome
        return oddCount <= 1 ? "YES" : "NO";
    }

    public static void main(String[] args) {
        String s = "aaabbbb";
        GameOfThrones got = new GameOfThrones();
        System.out.println("Can form palindrome: " + got.gameOfThrones(s));
    }
}
