package mavis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mavis.task.ToDo;

/**
 * This class contains unit tests for the TaskList class.
 */
public class TaskListTest {

    private ArrayList<ToDo> taskList;
    private ToDo sampleToDo;

    @BeforeEach
    public void setUp() {
        // Initializing toDoList and a sample ToDo before each test
        taskList = new ArrayList<>();
        sampleToDo = new ToDo("Sample ToDo");
    }

    /**
     * Tests that a ToDo can be added to the task list.
     */
    @Test
    public void testAddToDo() {
        taskList.add(sampleToDo);
        assertEquals(1, taskList.size(), "ToDo should be added.");
        assertEquals(sampleToDo, taskList.get(0), "The added ToDo should match the sample ToDo.");
    }

    /**
     * Tests that a ToDo can be deleted from the task list using a valid index.
     */
    @Test
    public void testDeleteToDoValidIndex() {
        taskList.add(sampleToDo);
        ToDo deletedToDo = taskList.remove(0);
        assertEquals(0, taskList.size(), "ToDo should be removed.");
        assertEquals(sampleToDo, deletedToDo, "The deleted ToDo should match the added ToDo.");
    }

    /**
     * Tests that deleting a ToDo using an invalid index throws an IndexOutOfBoundsException.
     */
    @Test
    public void testDeleteToDoInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.remove(0),
            "Deleting an invalid ToDo should throw IndexOutOfBoundsException.");
    }

    /**
     * Tests that a ToDo can be marked as done.
     */
    @Test
    public void testMarkDoneValidToDo() {
        taskList.add(sampleToDo);
        sampleToDo.setDone(true);
        assertTrue(sampleToDo.getDone(), "The ToDo should be marked as done.");
    }

    /**
     * Tests that marking a non-existing ToDo as done throws an IndexOutOfBoundsException.
     */
    @Test
    public void testMarkDoneInvalidToDo() {
        // Trying to mark a non-existing ToDo, should not be possible in this context
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.get(1).setDone(true),
            "Marking an invalid ToDo should throw IndexOutOfBoundsException.");
    }

    /**
     * Tests that a ToDo can be marked as not done.
     */
    @Test
    public void testUnmarkDoneValidToDo() {
        taskList.add(sampleToDo);
        sampleToDo.setDone(true);
        sampleToDo.setDone(false);
        assertFalse(sampleToDo.getDone(), "The ToDo should be marked as not done.");
    }

    /**
     * Tests that unmarking a non-existing ToDo as not done throws an IndexOutOfBoundsException.
     */
    @Test
    public void testUnmarkDoneInvalidToDo() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.get(1).setDone(false),
            "Unmarking an invalid ToDo should throw IndexOutOfBoundsException.");
    }
    /**
     * Tests that the initial ToDo list should be empty.
     */
    @Test
    public void testInitialToDoList() {
        assertTrue(taskList.isEmpty(), "The ToDo list should initially be empty.");
    }

    /**
     * Tests that the ToDo count is correct after adding a ToDo.
     */
    @Test
    public void testToDoCountAfterAdding() {
        taskList.add(sampleToDo);
        assertEquals(1, taskList.size(), "The ToDo count should reflect the number of ToDos in the list.");
    }

    /**
     * Tests that the ToDo count is correct after deleting a ToDo.
     */
    @Test
    public void testToDoCountAfterDeleting() {
        taskList.add(sampleToDo);
        taskList.remove(0);
        assertEquals(0, taskList.size(), "The ToDo count should reflect the number of ToDos after deletion.");
    }
}
