import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class WordCounter {

    private Map<String, Integer> wordMap = new HashMap<>();
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void calculate() {
        String[] words = text.split("\\s+");  
        for (String word : words) {
            
            String cleanedWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();  

            if (!cleanedWord.isEmpty()) { 
                if (wordMap.containsKey(cleanedWord)) {
                    wordMap.put(cleanedWord, wordMap.get(cleanedWord) + 1);
                } else {
                    wordMap.put(cleanedWord, 1);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Entry<String, Integer> entry : wordMap.entrySet()) {
            result.append("word: ").append(entry.getKey()).append("; count: ").append(entry.getValue()).append("\n");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        WordCounter counter = new WordCounter();
        counter.setText("This is a test string! This is a test. Test string.");
        counter.calculate();
        System.out.println(counter);
    }
}