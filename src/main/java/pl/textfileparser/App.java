package pl.textfileparser;

import org.apache.log4j.Logger;
import pl.textfileparser.service.CSVGenerator;
import pl.textfileparser.service.OutputFileStream;
import pl.textfileparser.service.XMLGenerator;

import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    final static Logger LOGGER = Logger.getLogger(App.class);

    public static void main(String args[]) throws IOException, XMLStreamException {
        OutputFileStream outputFileStream = null;

        try {
            switch (args[1]) {
                case "CSV":
                    outputFileStream = new CSVGenerator();
                    break;
                case "XML":
                    outputFileStream = new XMLGenerator();
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.warn("No second parameter.");
            System.out.println("'XML' or 'CSV' as parameter expected.");
            e.printStackTrace();
        }

        final List<String> ABBREVATIONS = Arrays.asList("Mrs", "Mr");
        List<String> sentenceBuffer = new ArrayList<>();
        String lastWordInBuffer = null;

        FileReader fileReader = null;

        try {
            fileReader = new FileReader(args[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StreamTokenizer streamTokenizer = new StreamTokenizer(bufferedReader);
        streamTokenizer.ordinaryChar('.');
        streamTokenizer.wordChars('\'', '\'');

        outputFileStream.openStream();

        while (streamTokenizer.nextToken() != StreamTokenizer.TT_EOF) {
            switch (streamTokenizer.ttype) {
                case '"':
                    LOGGER.info("String = " + streamTokenizer.sval);
                    break;

                case StreamTokenizer.TT_EOL:
                    System.out.println("End-of-line");
                    break;

                case StreamTokenizer.TT_NUMBER:
                    sentenceBuffer.add(streamTokenizer.sval);
                    LOGGER.info("Number = " + streamTokenizer.nval);
                    break;

                case StreamTokenizer.TT_WORD:
                    sentenceBuffer.add(streamTokenizer.sval);
                    LOGGER.info("Word = " + streamTokenizer.sval);
                    break;

                default:
                    lastWordInBuffer = sentenceBuffer.get(sentenceBuffer.size()-1);
                    if (streamTokenizer.ttype == 46 && ABBREVATIONS.contains(lastWordInBuffer)) {
                        LOGGER.debug("ABBREVATION: " + (char) streamTokenizer.ttype);
                        sentenceBuffer.set(sentenceBuffer.size()-1, lastWordInBuffer+".");
                        sentenceBuffer
                                .sort((a, b) -> a.toLowerCase()
                                .compareTo(b.toLowerCase()));
                    } else if (streamTokenizer.ttype == 46) {
                        LOGGER.info("End of sentence by: " + (char) streamTokenizer.ttype);
                        sentenceBuffer
                                .sort((a, b) -> a.toLowerCase()
                                .compareTo(b.toLowerCase()));
                        LOGGER.warn("New sentence found: " + sentenceBuffer);
                        outputFileStream.writeFile(sentenceBuffer);
                        sentenceBuffer.clear();
                    }
                        LOGGER.info("Delimeter = " + (char) streamTokenizer.ttype);

                    }
            }
            outputFileStream.closeFile();
        fileReader.close();
        }
    }
