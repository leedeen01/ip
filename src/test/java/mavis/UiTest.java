package mavis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mavis.task.Task;
import mavis.task.ToDo;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class UiTest {
    private Ui ui;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        ui = new Ui();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));  // Redirect System.out to capture the output
    }

    @Test
    void testShowWelcome() {
        ui.showWelcome();

        // Check if the welcome message is printed
        String output = outputStream.toString();
        assertTrue(output.contains("Hello! I'm Mavis"));
        assertTrue(output.contains("How can i help you?"));
    }

    @Test
    void testShowLine() {
        ui.showLine();

        // Check if the line separator is printed
        String output = outputStream.toString();
        assertTrue(output.contains("____________________________________________________________"));
    }

    @Test
    void testShowTaskAdded() {
        Task task = new ToDo("Buy groceries");  // Example task
        ui.showTaskAdded(task);

        // Check if the task added message is printed
        String output = outputStream.toString();
        assertTrue(output.contains("Got it. I've added this task:"));
        assertTrue(output.contains("Buy groceries"));
    }

    @Test
    void testShowDeleteTask() {
        Task task = new ToDo("Read book");
        ui.showDeleteTask(task);

        // Check if the task removal message is printed
        String output = outputStream.toString();
        assertTrue(output.contains("Noted. I've removed this task:"));
        assertTrue(output.contains("Read book"));
    }

    @Test
    void testShowMarkTask() {
        Task task = new ToDo("Complete homework");
        ui.showMarkTask(task);

        // Check if the task marked message is printed
        String output = outputStream.toString();
        assertTrue(output.contains("Nice! I've marked this task as done:"));
        assertTrue(output.contains("Complete homework"));
    }

    @Test
    void testShowUnmarkTask() {
        Task task = new ToDo("Do laundry");
        ui.showUnmarkTask(task);

        // Check if the task unmarked message is printed
        String output = outputStream.toString();
        assertTrue(output.contains("OK, I've marked this task as not done yet:"));
        assertTrue(output.contains("Do laundry"));
    }

    @Test
    void testPrintTasks() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("Task 1");
        Task task2 = new ToDo("Task 2");
        taskList.addTask(task1);
        taskList.addTask(task2);

        ui.printTasks(taskList);

        // Check if the task list is printed correctly
        String output = outputStream.toString();
        assertTrue(output.contains("Here are the tasks in your list:"));
        assertTrue(output.contains("1. [T][ ] Task 1"));
        assertTrue(output.contains("2. [T][ ] Task 2"));
    }

    @Test
    void testShowGoodbyeMessage() {
        ui.showGoodbyeMessage();

        // Check if the goodbye message is printed
        String output = outputStream.toString();
        assertTrue(output.contains("Bye. Hope to see you again soon!"));
    }

    @Test
    void testShowError() {
        ui.showError("This is an error message");

        // Check if the error message is printed
        String output = outputStream.toString();
        assertTrue(output.contains("This is an error message"));
        assertTrue(output.contains("____________________________________________________________"));
    }
}
