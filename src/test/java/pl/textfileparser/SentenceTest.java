package pl.textfileparser;

import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.textfileparser.model.Sentence;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class SentenceTest{

    private static List<Sentence> Sentences;
    final static Logger LOGGER = Logger.getLogger(SentenceTest.class);


@BeforeClass
public static void Initialize(){

        List<String> trueWordsList1 = new ArrayList<>();
        List<String> falseWordsList = new ArrayList<>();
        List<String> trueWordsList2 = new ArrayList<>();

        trueWordsList1.add("cat");
        trueWordsList1.add("has");
        trueWordsList1.add("Ala");
        trueWordsList1.add("a");
        falseWordsList.add("ala");
        falseWordsList.add("a");
        falseWordsList.add("has");
        falseWordsList.add("cat");
        trueWordsList2.add("has");
        trueWordsList2.add("cat");
        trueWordsList2.add("a");
        trueWordsList2.add("Ala");

        Sentences = new ArrayList<Sentence>();
        Sentences.add(0, new Sentence(trueWordsList1));
        Sentences.add(1, new Sentence(falseWordsList));
        Sentences.add(2, new Sentence(trueWordsList2));
    }

@Test
public void sentencesShouldBeCaseSensitive() {
    LOGGER.info("First sentence: " + Sentences.get(0));
    LOGGER.info("Second sentence: " + Sentences.get(0));
    assertFalse("Are sentences not the same", Sentences.get(0).equals(Sentences.get(1)));
    }

@Test
public void sortingWordsInSentencesWithSameWordsShouldBeEqual() {
    LOGGER.info("First sentence: " + Sentences.get(0));
    LOGGER.info("Second sentence: " + Sentences.get(2));
    assertThat(Sentences.get(0), Matchers.equalTo(Sentences.get(2)));
    }
}
