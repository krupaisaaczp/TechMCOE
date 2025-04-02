import java.util.*;

class MultiLanguageDictionary<T extends String> {
    private TreeMap<T, T> dictionary = new TreeMap<>();

    public void addWord(T word, T meaning) {
        dictionary.put(word, meaning);
    }

    public void displayDictionary() {
        dictionary.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}

public class MultiLanguageDictionaryApp {
    public static void main(String[] args) {
        MultiLanguageDictionary<String> dict = new MultiLanguageDictionary<>();
        dict.addWord("Hello", "A greeting");
        dict.addWord("World", "The earth");

        System.out.println("Dictionary:");
        dict.displayDictionary();
    }
}
