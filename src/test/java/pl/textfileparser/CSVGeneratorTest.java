package pl.textfileparser;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.IOException;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class CSVGeneratorTest {
    final BufferedWriter writer = mock(BufferedWriter.class);
    final IOException exception = new IOException();

    @Test
    public void shouldThrowExceptionWhenAppendingNull() throws IOException {

        doThrow(exception).when(writer).append(null);

    }
}
