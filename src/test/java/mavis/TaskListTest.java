package mavis;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mavis.task.ToDo;
import java.util.ArrayList;

public class TaskListTest {

    private ArrayList<ToDo> taskList;
    private ToDo sampleToDo;

    @BeforeEach
    public void setUp() {
        // Initializing toDoList and a sample ToDo before each test
        taskList = new ArrayList<>();
        sampleToDo = new ToDo("Sample ToDo");
    }

    @Test
    public void testAddToDo() {
        taskList.add(sampleToDo);
        assertEquals(1, taskList.size(), "ToDo should be added.");
        assertEquals(sampleToDo, taskList.get(0), "The added ToDo should match the sample ToDo.");
    }

    @Test
    public void testDeleteToDo_ValidIndex() {
        taskList.add(sampleToDo);
        ToDo deletedToDo = taskList.remove(0);
        assertEquals(0, taskList.size(), "ToDo should be removed.");
        assertEquals(sampleToDo, deletedToDo, "The deleted ToDo should match the added ToDo.");
    }

    @Test
    public void testDeleteToDo_InvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.remove(0), "Deleting an invalid ToDo should throw IndexOutOfBoundsException.");
    }

    @Test
    public void testMarkDone_ValidToDo() {
        taskList.add(sampleToDo);
        sampleToDo.setDone(true);
        assertTrue(sampleToDo.getDone(), "The ToDo should be marked as done.");
    }

    @Test
    public void testMarkDone_InvalidToDo() {
        // Trying to mark a non-existing ToDo, should not be possible in this context
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.get(1).setDone(true), "Marking an invalid ToDo should throw IndexOutOfBoundsException.");
    }

    @Test
    public void testUnmarkDone_ValidToDo() {
        taskList.add(sampleToDo);
        sampleToDo.setDone(true);
        sampleToDo.setDone(false);
        assertFalse(sampleToDo.getDone(), "The ToDo should be marked as not done.");
    }

    @Test
    public void testUnmarkDone_InvalidToDo() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.get(1).setDone(false), "Unmarking an invalid ToDo should throw IndexOutOfBoundsException.");
    }

    @Test
    public void testInitialToDoList() {
        assertTrue(taskList.isEmpty(), "The ToDo list should initially be empty.");
    }

    @Test
    public void testToDoCountAfterAdding() {
        taskList.add(sampleToDo);
        assertEquals(1, taskList.size(), "The ToDo count should reflect the number of ToDos in the list.");
    }

    @Test
    public void testToDoCountAfterDeleting() {
        taskList.add(sampleToDo);
        taskList.remove(0);
        assertEquals(0, taskList.size(), "The ToDo count should reflect the number of ToDos after deletion.");
    }
}
