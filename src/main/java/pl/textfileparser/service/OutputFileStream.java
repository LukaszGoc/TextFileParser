package pl.textfileparser.service;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public interface OutputFileStream {
    void openStream() throws XMLStreamException, IOException;
    void writeFile(List<String> sentenceBuffer) throws XMLStreamException, IOException;
    void closeFile() throws XMLStreamException, IOException;
}
