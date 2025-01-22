public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String report() {
        Boolean done = super.getDone();
        if (done) {
            return "[T]"  + "[X] " + super.getName();
        }
        return "[T][ ] " + super.getName();
    }
}

