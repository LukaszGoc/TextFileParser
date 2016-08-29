package pl.textfileparser.service;

import org.apache.log4j.Logger;
import pl.textfileparser.App;
import pl.textfileparser.service.OutputFileStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class XMLGenerator implements OutputFileStream {

    final static Logger LOGGER = Logger.getLogger(XMLGenerator.class);

    XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
//    xmlOutputFactory.setProperty("escapeCharacters", true);
    XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileWriter("output.xml"));

    public XMLGenerator() throws IOException, XMLStreamException {
    }

    @Override
    public void openStream() throws XMLStreamException {
        LOGGER.trace("Stream opened.");
        xmlOutputFactory.setProperty("escapeCharacters", true);
        xmlStreamWriter.writeStartDocument("utf-8", "1,0");
        xmlStreamWriter.writeCharacters("\n");
        xmlStreamWriter.writeStartElement("text");
        xmlStreamWriter.writeCharacters("\n");}

    @Override
    public void writeFile(List<String> sentenceBuffer) throws XMLStreamException {
        xmlStreamWriter.writeStartElement("sentence");
        sentenceBuffer.stream()
                .forEach(a -> {
                    try {
                        xmlStreamWriter.writeStartElement("word");
                        xmlStreamWriter.writeCharacters(a);
                        xmlStreamWriter.writeEndElement();
                    } catch (XMLStreamException e) {
                        e.printStackTrace();
                    }
                });
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeCharacters("\n");
        LOGGER.info("Sentence sent to writer.");

    }

    @Override
    public void closeFile() throws XMLStreamException {
        xmlStreamWriter.writeEndDocument();
        xmlStreamWriter.writeEndDocument();
        xmlStreamWriter.close();
        LOGGER.trace("Writer closed.");

    }
}
