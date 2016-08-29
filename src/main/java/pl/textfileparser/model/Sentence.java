package pl.textfileparser.model;

import java.util.List;

public class Sentence {

    private List<String> words;

    public Sentence(List<String> words) {
        words.sort((a, b) -> a.toLowerCase().compareTo(b.toLowerCase()));
        this.words = words;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        words.sort((a, b) -> a.toLowerCase().compareTo(b.toLowerCase()));
        this.words = words;
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "words=" + words +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sentence)) return false;

        Sentence sentence = (Sentence) o;

        return words != null ? words.equals(sentence.words) : sentence.words == null;

    }

    @Override
    public int hashCode() {
        return words != null ? words.hashCode() : 0;
    }
}
