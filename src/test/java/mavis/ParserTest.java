package mavis;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import mavis.command.*;

public class ParserTest {
    @Test
    public void testParseBye() throws MavisException {
        String input = "bye";
        Command command = Parser.parse(input);
        assertTrue(command instanceof ExitCommand, "The command should be of type ExitCommand.");
    }

    @Test
    public void testParseList() throws MavisException {
        String input = "list";
        Command command = Parser.parse(input);
        assertTrue(command instanceof ListCommand, "The command should be of type ListCommand.");
    }

    @Test
    public void testParseUnknown() {
        String input = "unknown command";
        assertThrows(MavisException.class, () -> Parser.parse(input), "Parsing an unknown command should throw a MavisException.");
    }

    @Test
    public void testParseTodoEmptyDescription() {
        String input = "todo";
        assertThrows(MavisException.class, () -> Parser.parse(input), "Parsing a ToDo with empty description should throw a MavisException.");
    }

    @Test
    public void testParseDeadlineMissingBy() {
        String input = "deadline Test Deadline";
        assertThrows(MavisException.class, () -> Parser.parse(input), "Parsing a Deadline with missing '/by' should throw a MavisException.");
    }

    @Test
    public void testParseEventInvalidFormat() {
        String input = "event Test Event /start 2025-01-28 0900";
        assertThrows(MavisException.class, () -> Parser.parse(input), "Parsing an Event with missing '/end' should throw a MavisException.");
    }
}
