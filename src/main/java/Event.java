public class Event extends Task {
    private String startDate;
    private String endDate;

    public Event(String name, String startDate, String endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String report() {
        Boolean done = super.getDone();
        if (done) {
            return "[E]"  + "[X] " + super.getName() + " (" + startDate + " " + endDate + ")";
        }
        return "[E][ ] " + super.getName() + " (" + startDate + " " + endDate + ")";
    }
}

