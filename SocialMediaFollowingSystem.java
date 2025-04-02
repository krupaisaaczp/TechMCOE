import java.util.*;

public class SocialMediaFollowingSystem {
    public static void main(String[] args) {
        HashSet<String> followersA = new HashSet<>(List.of("John", "Alice", "Mike"));
        HashSet<String> followersB = new HashSet<>(List.of("Alice", "Sam", "David"));

        // Display followers alphabetically
        TreeSet<String> sortedFollowers = new TreeSet<>(followersA);
        System.out.println("Sorted Followers (User A): " + sortedFollowers);

        // Suggest new friends (difference)
        Set<String> suggestions = new HashSet<>(followersB);
        suggestions.removeAll(followersA);
        System.out.println("Friend Suggestions: " + suggestions);
    }
}
