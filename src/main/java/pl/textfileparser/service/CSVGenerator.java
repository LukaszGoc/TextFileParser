package pl.textfileparser.service;

import org.apache.log4j.Logger;
import javax.xml.stream.XMLStreamException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVGenerator implements OutputFileStream {
    
    private BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.csv"));
    private int countSentences = 1;
    final static Logger LOGGER = Logger.getLogger(CSVGenerator.class);

    public CSVGenerator() throws IOException {
    }

    @Override
    public void openStream() throws XMLStreamException, IOException {
        bufferedWriter.newLine();
        LOGGER.trace("Stream opened.");
    }

    @Override
    public void writeFile(List<String> sentenceBuffer) throws XMLStreamException, IOException {
        bufferedWriter.write("Sentence "+countSentences);
        sentenceBuffer.stream()
                .forEach(a -> {
                    try {
                        bufferedWriter.write(", " + a);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        bufferedWriter.newLine();
        countSentences++;
        LOGGER.info("Sentence no. : " + countSentences);
    }

    @Override
    public void closeFile() throws XMLStreamException, IOException {
        bufferedWriter.flush();
        bufferedWriter.close();
        LOGGER.info("Write buffer closed.");
    }
}
