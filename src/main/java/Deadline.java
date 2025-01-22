public class Deadline extends Task {
    private String dueDate;

    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }
    @Override
    public String report() {
        Boolean done = super.getDone();
        if (done) {
            return "[D]"  + "[X] " + super.getName() + "(" + dueDate + ")";
        }
        return "[D][ ] " + super.getName() + "(" + dueDate + ")";
    }
}

