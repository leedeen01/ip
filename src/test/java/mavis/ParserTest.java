package mavis;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import mavis.command.Command;
import mavis.command.ExitCommand;
import mavis.command.ListCommand;



/**
 * Test class for the Parser.
 */
public class ParserTest {
    /**
     * Tests the parsing of the "bye" command.
     * @throws MavisException if there is an error during parsing.
     */
    @Test
    public void testParseBye() throws MavisException {
        String input = "bye";
        Command command = Parser.parse(input);
        assertTrue(command instanceof ExitCommand, "The command should be of type ExitCommand.");
    }

    /**
     * Tests the parsing of the "list" command.
     * @throws MavisException if there is an error during parsing.
     */
    @Test
    public void testParseList() throws MavisException {
        String input = "list";
        Command command = Parser.parse(input);
        assertTrue(command instanceof ListCommand, "The command should be of type ListCommand.");
    }

    /**
     * Tests the parsing of an unknown command.
     */
    @Test
    public void testParseUnknown() {
        String input = "unknown command";
        assertThrows(MavisException.class, () -> Parser.parse(input),
            "Parsing an unknown command should throw a MavisException.");
    }

    /**
     * Tests the parsing of a ToDo command with an empty description.
     */
    @Test
    public void testParseTodoEmptyDescription() {
        String input = "todo";
        assertThrows(MavisException.class, () -> Parser.parse(input),
            "Parsing a ToDo with empty description should throw a MavisException.");
    }

    /**
     * Tests the parsing of a Deadline command with a missing '/by'.
     */
    @Test
    public void testParseDeadlineMissingBy() {
        String input = "deadline Test Deadline";
        assertThrows(MavisException.class, () -> Parser.parse(input),
            "Parsing a Deadline with missing '/by' should throw a MavisException.");
    }

    /**
     * Tests the parsing of an Event command with an invalid format.
     */
    @Test
    public void testParseEventInvalidFormat() {
        String input = "event Test Event /start 2025-01-28 0900";
        assertThrows(MavisException.class, () -> Parser.parse(input),
            "Parsing an Event with missing '/end' should throw a MavisException.");
    }
}
