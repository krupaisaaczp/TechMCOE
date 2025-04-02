import java.util.HashMap;

public class LongestSubstringWithoutRepeating {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = 0, start = 0;
        
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            // If character is already in the window, update the start position
            if (map.containsKey(c)) {
                start = Math.max(start, map.get(c) + 1);
            }
            // Update the last position of the character
            map.put(c, end);
            // Update the maximum length
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        LongestSubstringWithoutRepeating lswr = new LongestSubstringWithoutRepeating();
        System.out.println("Length of longest substring: " + lswr.lengthOfLongestSubstring(s));
    }
}
