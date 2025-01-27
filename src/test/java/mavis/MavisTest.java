package mavis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MavisTest {
    private final String testFilePath = "src/test/data/TestMavis.txt"; // Test data file path
    private ByteArrayOutputStream outputStream; // To capture console output

    @BeforeEach
    void setUp() {
        // Set up to capture console output for verification
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Ensure the test file is empty or reset before tests
        TaskList taskList = new TaskList(); // Create an empty TaskList
        Storage storage = new Storage(testFilePath);
        storage.saveTasks(taskList); // Pass TaskList object to saveTasks()
    }

    @Test
    void testRun_withValidCommand() {
        // Simulate user input for adding a ToDo task and exiting
        String simulatedInput = "todo Buy milk\nbye\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        // Create and run Mavis
        Mavis mavis = new Mavis(testFilePath);
        mavis.run();

        // Verify output contains the added task
        String output = outputStream.toString();
        assertTrue(output.contains("Got it. I've added this task:"));
        assertTrue(output.contains("[T][ ] Buy milk"));
    }

    @Test
    void testRun_withInvalidCommand() {
        // Simulate invalid user input
        String simulatedInput = "invalid_command\nbye\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        // Create and run Mavis
        Mavis mavis = new Mavis(testFilePath);
        mavis.run();

        // Verify output contains error message
        String output = outputStream.toString();
        assertTrue(output.contains("I'm sorry, but I don't know what that means."));
    }

    @Test
    void testRun_withEmptyInput() {
        // Simulate empty input and then exit
        String simulatedInput = "\nbye\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        // Create and run Mavis
        Mavis mavis = new Mavis(testFilePath);
        mavis.run();

        // Verify output contains error for empty command
        String output = outputStream.toString();
        assertTrue(output.contains("I'm sorry, but I don't know what that means."));
    }
}
