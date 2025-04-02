import java.util.*;

// Generic class for dictionary entry with bounded type parameter
class DictionaryEntry<T extends Comparable<? super T>> implements Comparable<DictionaryEntry<T>> {
    private T word;
    private Map<String, T> translations;
    private String partOfSpeech;
    private List<String> examples;
    
    public DictionaryEntry(T word, String partOfSpeech) {
        this.word = word;
        this.partOfSpeech = partOfSpeech;
        this.translations = new HashMap<>();
        this.examples = new ArrayList<>();
    }
    
    public T getWord() { return word; }
    public String getPartOfSpeech() { return partOfSpeech; }
    public Map<String, T> getTranslations() { return translations; }
    public List<String> getExamples() { return examples; }
    
    public void addTranslation(String language, T translation) {
        translations.put(language, translation);
    }
    
    public T getTranslation(String language) {
        return translations.get(language);
    }
    
    public void addExample(String example) {
        examples.add(example);
    }
    
    @Override
    public int compareTo(DictionaryEntry<T> other) {
        return this.word.compareTo(other.word);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DictionaryEntry<?> that = (DictionaryEntry<?>) o;
        return Objects.equals(word, that.word) && 
               Objects.equals(partOfSpeech, that.partOfSpeech);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(word, partOfSpeech);
    }
    
    @Override
    public String toString() {
        return word + " (" + partOfSpeech + ")";
    }
}

public class MultiLanguageDictionary<T extends Comparable<? super T>> {
    // Maps from language to a TreeMap of words to entries
    private Map<String, TreeMap<T, DictionaryEntry<T>>> languageDictionaries;
    private Set<String> supportedLanguages;
    
    public MultiLanguageDictionary() {
        languageDictionaries = new HashMap<>();
        supportedLanguages = new HashSet<>();
    }
    
    public void addLanguage(String language) {
        if (!languageDictionaries.containsKey(language)) {
            languageDictionaries.put(language, new TreeMap<>());
            supportedLanguages.add(language);
        }
    }
    
    public Set<String> getSupportedLanguages() {
        return Collections.unmodifiableSet(supportedLanguages);
    }
    
    public void addWord(String sourceLanguage, DictionaryEntry<T> entry) {
        // Ensure language exists
        if (!supportedLanguages.contains(sourceLanguage)) {
            addLanguage(sourceLanguage);
        }
        
        // Add the word to the source language dictionary
        TreeMap<T, DictionaryEntry<T>> dictionary = languageDictionaries.get(sourceLanguage);
        dictionary.put(entry.getWord(), entry);
        
        // Add the translations to their respective dictionaries
        for (Map.Entry<String, T> translation : entry.getTranslations().entrySet()) {
            String targetLanguage = translation.getKey();
            T translatedWord = translation.getValue();
            
            if (!supportedLanguages.contains(targetLanguage)) {
                addLanguage(targetLanguage);
            }
            
            // Create a new entry for the translated word
            DictionaryEntry<T> translatedEntry = new DictionaryEntry<>(translatedWord, entry.getPartOfSpeech());
            translatedEntry.addTranslation(sourceLanguage, entry.getWord());
            
            // Add translated examples
            for (String example : entry.getExamples()) {
                translatedEntry.addExample("Translation of: " + example);
            }
            
            TreeMap<T, DictionaryEntry<T>> targetDictionary = languageDictionaries.get(targetLanguage);
            
            // Check if entry already exists
            DictionaryEntry<T> existingEntry = targetDictionary.get(translatedWord);
            if (existingEntry != null) {
                // Merge translations and examples
                existingEntry.getTranslations().put(sourceLanguage, entry.getWord());
                for (String example : entry.getExamples()) {
                    existingEntry.addExample("Translation of: " + example);
                }
            } else {
                // Add the new translated entry
                targetDictionary.put(translatedWord, translatedEntry);
            }
        }
    }
    
    public DictionaryEntry<T> getWordEntry(String language, T word) {
        if (!supportedLanguages.contains(language) || !languageDictionaries.get(language).containsKey(word)) {
            return null;
        }
        return languageDictionaries.get(language).get(word);
    }
    
    public void displayDictionary(String language) {
        if (!supportedLanguages.contains(language)) {
            System.out.println("Language not supported: " + language);
            return;
        }
        System.out.println("\n=== Dictionary for " + language + " ===");
        for (DictionaryEntry<T> entry : languageDictionaries.get(language).values()) {
            System.out.println(entry + " - Translations: " + entry.getTranslations());
            System.out.println("Examples: " + entry.getExamples());
            System.out.println("------------------------------------");
        }
    }

    public static void main(String[] args) {
        MultiLanguageDictionary<String> dictionary = new MultiLanguageDictionary<>();
        dictionary.addLanguage("English");
        dictionary.addLanguage("Spanish");
        dictionary.addLanguage("French");

        // Adding English word
        DictionaryEntry<String> word1 = new DictionaryEntry<>("Hello", "Interjection");
        word1.addTranslation("Spanish", "Hola");
        word1.addTranslation("French", "Bonjour");
        word1.addExample("Hello, how are you?");
        
        dictionary.addWord("English", word1);

        // Adding Spanish word
        DictionaryEntry<String> word2 = new DictionaryEntry<>("Adiós", "Interjection");
        word2.addTranslation("English", "Goodbye");
        word2.addTranslation("French", "Au revoir");
        word2.addExample("Adiós, nos vemos mañana.");
        
        dictionary.addWord("Spanish", word2);

        // Display dictionaries
        dictionary.displayDictionary("English");
        dictionary.displayDictionary("Spanish");
        dictionary.displayDictionary("French");
    }
}
